package javainterfaz;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//clase
public class VentanaPestanas extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTabbedPane tabbedPane;
    private ArrayList<ArrayList<String[]>> resultados; // ArrayList para almacenar los resultados de cada jornada
    private boolean temporadaFinalizada;
    private JComboBox<String> comboBoxTemporadas;
    private List<Equipo> equipos;
    private List<List<Partido>> calendarioGenerado;  // Nueva variable para almacenar el calendario fijo

   
   
//ventana
    public VentanaPestanas(JComboBox<String> comboBoxTemporadas,  List<Equipo> equipos) {
    	
    	this.comboBoxTemporadas = comboBoxTemporadas;
    	 this.equipos = (equipos != null) ? equipos : new ArrayList<>(); // Asegurar que no sea null
    	
        // Configuración básica de la ventana
        setTitle("Jornadas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar el ArrayList de resultados 
        resultados = new ArrayList<>();

        // Configurar el layout principal con BorderLayout
        setLayout(new BorderLayout());

        // Crear el JTabbedPane
        tabbedPane = new JTabbedPane();
        
        

        // Crear las 11 pestañass
        crearPestanas();

        // Agregar un ChangeListener para actualizar contenido al cambiar pestaña
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = tabbedPane.getSelectedIndex();
                actualizarContenidoPestana(selectedIndex);
            }
        });

        // Añadir el JTabbedPane al centro de la ventana
        add(tabbedPane, BorderLayout.CENTER);
        
        actualizarContenidoPestana(0); // 0 corresponde a la primera pestaña

        // Crear el panel inferior con el botón "Actualizar Resultados"
        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER));  // Alineación central
        
        JButton actualizarButton = new JButton("Actualizar Resultados");
        actualizarButton.addActionListener(e -> actualizarResultados()); // Asocia el evento del botón
        panelBoton.add(actualizarButton);
        
        // Botón para finalizar la temporada
        JButton finalizarButton = new JButton("Finalizar Temporada");
        finalizarButton.addActionListener(e -> finalizarTemporada()); // Asocia el evento del botón
        panelBoton.add(finalizarButton);
        
        // Añadir el panel del botón al borde inferior de la ventana
        add(panelBoton, BorderLayout.SOUTH);
    }
    
    

    private void crearPestanas() {
        // Al generar el calendario solo una vez
        if (calendarioGenerado == null) {
            calendarioGenerado = generarCalendarioRoundRobin(equipos);
        }
        
        for (int i = 1; i <= 10; i++) {
            // Crear un panel para cada pestaña
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Inicializar la lista de resultados de la jornada actual
            resultados.add(new ArrayList<>());

            // Añadir el panel a la pestaña
            JScrollPane scrollPanel = new JScrollPane(panel);
            tabbedPane.addTab("Jornada " + i, scrollPanel);

            //Actualizar la pestaña para inicializar los componentes
            actualizarContenidoPestana(i - 1);
        }
    }
    
    private List<List<Partido>> generarCalendarioRoundRobin(List<Equipo> equipos) {
        List<List<Partido>> jornadas = new ArrayList<>();
        int numEquipos = equipos.size();

        // Si el número de equipos es impar, añadimos un equipo "Descansa"
        

        int numJornadas = numEquipos - 1; // Número de jornadas para la ida
        int numPartidosPorJornada = numEquipos / 2;

        // Lista rotativa de equipos (sin el primero, que es fijo)
        List<Equipo> equiposRotables = new ArrayList<>(equipos);
        equiposRotables.remove(0); // El primer equipo será fijo

        // Generar jornadas de ida
        for (int jornada = 0; jornada < numJornadas; jornada++) {
            List<Partido> partidos = new ArrayList<>();

            // Enfrentamiento del equipo fijo contra el último de la lista rotativa
            partidos.add(new Partido(equipos.get(0), equiposRotables.get(equiposRotables.size() - 1)));

            // Generar los demás partidos siguiendo la lógica de extremos (2º vs penúltimo, etc.)
            for (int i = 0; i < numPartidosPorJornada - 1; i++) {
                Equipo local = equiposRotables.get(i);
                Equipo visitante = equiposRotables.get(equiposRotables.size() - 2 - i);
                partidos.add(new Partido(local, visitante));
            }

            jornadas.add(partidos);

            // Rotar los equipos rotables para la siguiente jornada
            Equipo ultimo = equiposRotables.remove(equiposRotables.size() - 1);
            equiposRotables.add(0, ultimo);
        }

        // Generar jornadas de vuelta (invirtiendo local y visitante)
        List<List<Partido>> jornadasVuelta = new ArrayList<>();
        for (List<Partido> jornadaIda : jornadas) {
            List<Partido> jornadaVuelta = new ArrayList<>();
            for (Partido partido : jornadaIda) {
                jornadaVuelta.add(new Partido(partido.getvisitante(), partido.getlocal()));
            }
            jornadasVuelta.add(jornadaVuelta);
        }

        // Añadir las jornadas de vuelta al calendario
        jornadas.addAll(jornadasVuelta);

        return jornadas;
    }

    
   
    private void actualizarContenidoPestana(int index) {
        // Obtener el panel correspondiente a la pestaña seleccionada
        JScrollPane scrollPanel = (JScrollPane) tabbedPane.getComponentAt(index);
        JPanel panel = (JPanel) scrollPanel.getViewport().getView();

        // Limpiar el contenido del panel
        panel.removeAll();

        List<Partido> partidos = calendarioGenerado.get(index);
        
        ArrayList<String[]> jornadaResultados = resultados.get(index);

        int idx = 0;
        // Crear los campos de texto para los resultados
        for (Partido partido : partidos) {
            JPanel partidoPanel = new JPanel();
            partidoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            // Etiqueta y campo para el primer equipo
            JLabel equipo1Label = new JLabel(partido.getlocal().getNombre());
            JTextField equipo1txt = new JTextField("", 10);
            if (jornadaResultados.size() > 0 && jornadaResultados.get(idx) != null) {
                equipo1txt.setText(jornadaResultados.get(idx)[0]); // Cargar el resultado
            }
            equipo1txt.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                    if (equipo1txt.getText().length() >= 2) {
                        e.consume();
                    }
                }
            });

            // Incrementar idx
            idx++;
            if (idx == partidos.size()) {
                idx = 0; // Reiniciar el índice si se sobrepasa el número de partidos
            }

            // Etiqueta "vs"
            JLabel vsLabel = new JLabel("vs");

            // Etiqueta y campo para el segundo equipo
            Equipo equipo2 = partidos.get(idx).getvisitante();
            JLabel equipo2Label = new JLabel(partido.getvisitante().getNombre());
            JTextField equipo2txt = new JTextField("", 10);

            // Cargar el resultado si existe
            if (jornadaResultados.size() > idx && jornadaResultados.get(idx) != null) {
                equipo2txt.setText(jornadaResultados.get(idx)[1]);
            }

            equipo2txt.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                    if (equipo2txt.getText().length() >= 2) {
                        e.consume();
                    }
                }
            });

            // Agregar los componentes en el orden especificado
            partidoPanel.add(equipo1Label);
            partidoPanel.add(equipo1txt);
            partidoPanel.add(vsLabel);
            partidoPanel.add(equipo2txt);
            partidoPanel.add(equipo2Label);

            // Agregar el panel del partido al panel principal
            panel.add(partidoPanel);
        }

        // Volver a renderizar el panel
        panel.revalidate();
        panel.repaint();
    }


    
    
    private void guardarTemporada(String temporada) {
        // Obtener la temporada seleccionada del comboBoxTemporadas
        String temporadaSeleccionada = (String) comboBoxTemporadas.getSelectedItem();

        // Extraer solo el año de la temporada seleccionada (por ejemplo "Temporada 2024" -> "2024")
        String año = temporadaSeleccionada.replaceAll("[^0-9]", "");

        String archivo = "temporada_" + año + ".txt"; // Nombre del archivo basado en el año

        // Crea un archivo si no existe
        File f = new File(archivo);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            // Escribe el encabezado de la temporada
            writer.write("Temporada: " + temporadaSeleccionada);
            writer.newLine();
            writer.write("Estado: Finalizada");
            writer.newLine();
            writer.newLine();

            // Escribir los nombres de los equipos participantes
            writer.write("Equipos Participantes:");
            writer.newLine();
            for (Equipo equipo : equipos) {
                writer.write("- " + equipo.getNombre());
                writer.newLine();
            }
            writer.newLine();

            // Escribir los resultados de cada jornada
            writer.write("Resultados por Jornada:");
            writer.newLine();
            for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                writer.write("Jornada " + (i + 1) + ":");
                writer.newLine();

                // Obtener los resultados de la jornada
                List<Partido> partidos = calendarioGenerado.get(i);
                ArrayList<String[]> jornadaResultados = resultados.get(i);

                for (int j = 0; j < partidos.size(); j++) {
                    Partido partido = partidos.get(j);
                    String[] resultado = jornadaResultados.get(j);

                    // Formato: Equipo1 (resultado1) vs Equipo2 (resultado2)
                    String lineaResultado = partido.getlocal().getNombre() + " " + resultado[0] + " vs " +
                                            partido.getvisitante().getNombre() + " " + resultado[1] + "";
                    writer.write(lineaResultado);
                    writer.newLine();
                }
                writer.newLine();
            }

            // Mensaje de confirmación
            JOptionPane.showMessageDialog(this, "Temporada " + temporadaSeleccionada + " guardada correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar la temporada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void finalizarTemporada() {
        boolean todosLlenos = true; // Flag para verificar si todos los campos están llenos

        // Recorre todas las pestañas (jornadas)
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            JScrollPane scrollPanel = (JScrollPane) tabbedPane.getComponentAt(i);
            JPanel panel = (JPanel) scrollPanel.getViewport().getView();

            // Recorre todos los componentes dentro del panel de la jornada actual (paneles de partidos)
            for (Component comp : panel.getComponents()) {
                if (comp instanceof JPanel) {
                    JPanel partidoPanel = (JPanel) comp;

                    // Recorre todos los componentes dentro del panel del partido (campos de texto)
                    for (Component subComp : partidoPanel.getComponents()) {
                        if (subComp instanceof JTextField) {
                            JTextField campoTexto = (JTextField) subComp;

                            // Verifica si el campo de texto está vacío
                            if (campoTexto.getText().trim().isEmpty()) {
                                todosLlenos = false; // Si algún campo está vacío, cambiamos el flag
                                break;
                            }
                        }
                    }
                }

                // Si encontramos un campo vacío, no seguimos revisando el resto
                if (!todosLlenos) {
                    break;
                }
            }

            // Si encontramos un campo vacío en cualquier jornada, no seguimos revisando el resto
            if (!todosLlenos) {
                break;
            }
        }

        // Si todos los campos están llenos, finalizar la temporada
        if (todosLlenos) {
        	
        	 String añoSeleccionado = (String) comboBoxTemporadas.getSelectedItem();
        	 
            // Aquí podemos mostrar el mensaje y cerrar la ventana actual
            JOptionPane.showMessageDialog(this, "Temporada Finalizada");

            // Llamada al método para guardar la temporada
            guardarTemporada(añoSeleccionado);  // Asegúrate de pasar el año adecuado

            // Cerrar la ventana actual (VentanaPestanas)
            this.dispose();

            // Abrir la ventana TemporadaFrame
            TemporadasFrame temporadaFrame = new TemporadasFrame();
            temporadaFrame.setVisible(true);
        } else {
            // Si hay algún campo vacío, mostrar un mensaje de advertencia
            JOptionPane.showMessageDialog(this, "Por favor, rellene todos los campos de todas las jornadas antes de finalizar la temporada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }


    
    private void actualizarResultados() {
        // Obtener los resultados de todos los campos de texto
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            JPanel panel = (JPanel) ((JScrollPane) tabbedPane.getComponentAt(i)).getViewport().getView();
            ArrayList<String[]> jornadaResultados = resultados.get(i); // Obtener la lista de resultados de la jornada

            // Limpiar los resultados de la jornada actual (si es necesario)
            jornadaResultados.clear();

            // Recorre todos los componentes del panel principal de la jornada actual
            for (Component comp : panel.getComponents()) {
                if (comp instanceof JPanel) {
                    JPanel partidoPanel = (JPanel) comp;

                    JTextField equipo1txt = null;
                    JTextField equipo2txt = null;

                    // Recorre todos los componentes dentro del panel del partido (campos de texto)
                    for (Component subComp : partidoPanel.getComponents()) {
                        if (subComp instanceof JTextField) {
                            if (equipo1txt == null) {
                                equipo1txt = (JTextField) subComp;
                            } else {
                                equipo2txt = (JTextField) subComp;
                            }
                        }
                    }

                    // Guardar los resultados en el ArrayList correspondiente para la jornada actual
                    if (equipo1txt != null && equipo2txt != null) {
                        String[] resultado = {equipo1txt.getText(), equipo2txt.getText()};
                        jornadaResultados.add(resultado); // Agregar resultado al ArrayList de la jornada
                    }
                }
            }
        }
    }

}
