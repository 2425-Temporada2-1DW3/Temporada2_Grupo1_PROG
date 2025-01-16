package javainterfaz;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class VentanaPestanas extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTabbedPane tabbedPane;
    private ArrayList<ArrayList<String[]>> resultados; // ArrayList para almacenar los resultados de cada jornada
   

    public VentanaPestanas() {
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
        
        

        // Crear las 11 pestañas
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
    	
        for (int i = 1; i <= 10; i++) {
            // Crear un panel para cada pestaña
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            

            // Inicializar la lista de resultados de la jornada actual
            resultados.add(new ArrayList<>());

            // Añadir el panel a la pestaña
            JScrollPane scrollPanel = new JScrollPane(panel);
            tabbedPane.addTab("Jornada " + i, scrollPanel);
        }
    }
    
   
    private void actualizarContenidoPestana(int index) {
        // Obtener el panel correspondiente a la pestaña seleccionada
        JScrollPane scrollPanel = (JScrollPane) tabbedPane.getComponentAt(index);
        JPanel panel = (JPanel) scrollPanel.getViewport().getView();

        // Limpiar el contenido del panel
        panel.removeAll();

        // Agregar tres partidos con campos de texto para insertar los nombres de los equipos
        
        for (int i = 1; i <= 3; i++) {
            JPanel partidoPanel = new JPanel();
            partidoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            // Etiqueta y campo para el primer equipo
            JLabel equipo1 = new JLabel("EQUIPO SELECCIONADO");
            JTextField equipo1txt = new JTextField("", 10);
            
            //Para que no se pueda poner mas de 2 digitos en el campo de texto
            equipo1txt.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    // Verificar si el caracter ingresado es un número
                    if (!Character.isDigit(c)) {
                        e.consume(); // Si no es un número, se descarta la tecla
                    }
                    // Limitar a 2 dígitos
                    if (equipo1txt.getText().length() >= 2) {
                        e.consume(); // No permitir más de 2 dígitos
                    }
                }
            });

            // Etiqueta "vs"
            JLabel vsLabel = new JLabel("vs");

            // Etiqueta y campo para el segundo equipo
            JLabel equipo2 = new JLabel("EQUIPO SELECCIONADO");
            JTextField equipo2txt = new JTextField("", 10);
            
            //Para que no se pueda poner mas de 2 digitos en el campo de texto
            equipo2txt.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    // Verificar si el caracter ingresado es un número
                    if (!Character.isDigit(c)) {
                        e.consume(); // Si no es un número, se descarta la tecla
                    }
                    // Limitar a 2 dígitos
                    if (equipo2txt.getText().length() >= 2) {
                        e.consume(); // No permitir más de 2 dígitos
                    }
                }
            });

            // Si ya hay resultados guardados, mostrarlos en los campos de texto
            if (resultados.get(index).size() > i - 1) {
                String[] partidoResultado = resultados.get(index).get(i - 1);
                equipo1txt.setText(partidoResultado[0]);
                equipo2txt.setText(partidoResultado[1]);
            }
            
            

            // Agregar los componentes en el orden especificado: etiqueta - campo - "vs" - campo - etiqueta
            partidoPanel.add(equipo1);
            partidoPanel.add(equipo1txt);
            partidoPanel.add(vsLabel);
            partidoPanel.add(equipo2txt);
            partidoPanel.add(equipo2);
            

            // Agregar el panel del partido al panel principal
            panel.add(partidoPanel);
        }

        // Volver a renderizar el panel
        panel.revalidate();
        panel.repaint();
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

            // Si encontramos un campo vacío, no seguimos revisando el resto de las jornadas
            if (!todosLlenos) {
                break;
            }
        }

        // Si todos los campos están llenos, finalizar la temporada
        if (todosLlenos) {
            // Aquí podemos mostrar el mensaje y cerrar la ventana actual
            JOptionPane.showMessageDialog(this, "Temporada Finalizada");

            // Cerrar la ventana actual (VentanaPestanas)
            this.dispose();

            // Abrir la ventana TemporadaFrame
            TemporadasFrame temporadaFrame = new TemporadasFrame();
            temporadaFrame.setVisible(true);
        } else {
            // Si hay algún campo vacío, mostrar un mensaje de advertencia
            JOptionPane.showMessageDialog(this, "Por favor, rellene todos los campos antes de finalizar la temporada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
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
            	
            	// Verifica si el componente es un panel (el panel de cada partido)
            	
                if (comp instanceof JPanel) {
                	
                	// Convierte el componente a un JPanel (representa un partido)
                    JPanel partidoPanel = (JPanel) comp;
                    
                    // Inicializa los campos de texto para los equipos (no están asignados inicialmente)
                    JTextField equipo1txt = null; 
                    JTextField equipo2txt = null;
                    
                    // Recorre todos los componentes dentro del panel del partido (campos de texto)
                    for (Component subComp : partidoPanel.getComponents()) {
                    	
                    	 // Verifica si el subcomponente es un JTextField (campo de texto)
                        if (subComp instanceof JTextField) {
                        	
                        	  // Si el primer campo de texto no ha sido asignado, asigna el primero
                            if (equipo1txt == null) {
                                equipo1txt = (JTextField) subComp;
                            } else {
                            	 // Si el primer campo de texto ya fue asignado, asigna el segundo
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
