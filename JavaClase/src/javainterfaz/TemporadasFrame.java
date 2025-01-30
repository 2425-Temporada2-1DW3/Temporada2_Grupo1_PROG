package javainterfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;




import javax.swing.*;
import java.awt.*;
import java.util.List;


// Clase principal para la interfaz gráfica
public class TemporadasFrame extends JFrame implements ActionListener {
	
    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboBoxTemporadas;
    private JPanel panelEquipos;
    private JPanel panelJugadores;
    private List<Temporada> temporadas;
    private List<JCheckBox> checkboxesEquipos;
    private JButton btnConfirmar;
    private JButton btnAcceder;
    private JSeparator separator;
    private JSeparator separator_1;
    private JButton btnCrearTemporada;
    private JTabbedPane tabbedPane;
    private List<String> equiposSeleccionadosList = new ArrayList<>();
    private List<Equipo> equipos2023;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TemporadasFrame frame = new TemporadasFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TemporadasFrame() {
    	
        // Configuración básica del frame
        setTitle("Gestión de Temporadas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout(10, 10));

        // Inicializar datos de ejemplo-
        inicializarDatos();
        
     // Agregar el manejador de eventos para el cierre de la ventana

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Mostrar confirmación de si realmente desea salir
                int opcion = JOptionPane.showConfirmDialog(
                        TemporadasFrame.this,
                        "¿Estás seguro de que quieres salir?",
                        "Confirmar salida",
                        JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    // Preguntar si desea guardar los datos antes de salir
                    int guardarOpcion = JOptionPane.showConfirmDialog(
                            TemporadasFrame.this,
                            "¿Quieres guardar los datos antes de salir?",
                            "Guardar datos",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (guardarOpcion == JOptionPane.YES_OPTION) {
                        guardarTemporadas(); // Aquí llamas a la función para guardar las temporadas
                    }

                    System.exit(0); // Cerrar la aplicación
                } else {
                    // Si no quiere salir, no hacer nada y mantener la ventana abierta
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        // Panel superior con ComboBox de temporadas
        JPanel panelNorte = new JPanel();
        comboBoxTemporadas = new JComboBox<>();
        for (Temporada t : temporadas) {
            comboBoxTemporadas.addItem(String.valueOf(t.getAnio()));
        }
        comboBoxTemporadas.addActionListener(e -> actualizarEquipos());
        panelNorte.add(new JLabel("Temporada:"));
        panelNorte.add(comboBoxTemporadas);
        getContentPane().add(panelNorte, BorderLayout.NORTH);
        
        // Cargar las temporadas
        cargarTemporada("2024");
        
        separator_1 = new JSeparator();
        panelNorte.add(separator_1);
      
        separator = new JSeparator();
        panelNorte.add(separator);
        
        btnAcceder = new JButton("Acceder");
        btnAcceder.addActionListener(comboBoxTemporadas);
        btnAcceder.addActionListener(e -> accederALaPaginaPrincipal());
        panelNorte.add(btnAcceder);

        // Panel central con equipos y jugadores
        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 10, 10));

        // Panel izquierdo con los equipos (para 2023 sin checkbox)
        panelEquipos = new JPanel();
        panelEquipos.setLayout(new BoxLayout(panelEquipos, BoxLayout.Y_AXIS));
        JScrollPane scrollEquipos = new JScrollPane(panelEquipos);
        panelCentro.add(scrollEquipos);

        // Panel derecho con jugadores organizados en un GridLayout
        panelJugadores = new JPanel();
        panelJugadores.setLayout(new GridLayout(0, 2, 10, 10)); // 2 columnas, espacio entre jugadores
        JScrollPane scrollJugadores = new JScrollPane(panelJugadores);
        panelCentro.add(scrollJugadores);

        getContentPane().add(panelCentro, BorderLayout.CENTER);
        
        // Botón para crear temporada
        btnCrearTemporada = new JButton("Crear Temporada");
        btnCrearTemporada.addActionListener(e -> crearNuevaTemporada());
        panelNorte.add(btnCrearTemporada);

        getContentPane().add(panelNorte, BorderLayout.NORTH);

        // Botón para confirmar selección de equipos
        btnConfirmar = new JButton("Confirmar Equipos");
        btnConfirmar.addActionListener(e -> confirmarSeleccionEquipos());
        btnConfirmar.addActionListener(e -> {
        	
        	 long equiposSeleccionados = checkboxesEquipos.stream().filter(JCheckBox::isSelected).count();
        	    
        	    // Si no se han seleccionado exactamente 6 equipos, mostrar un mensaje y no crear el TabbedPane
        	    if (equiposSeleccionados != 6) {
        	        JOptionPane.showMessageDialog(this, "Debes seleccionar exactamente 6 equipos para continuar.", "Selección incorrecta", JOptionPane.WARNING_MESSAGE);
        	        return; // Detener el flujo y no crear la ventana con las pestañas
        	    }
        	    
        	    
        	    List<Equipo> equiposSeleccionados1 = checkboxesEquipos.stream()
        	            .filter(JCheckBox::isSelected)
        	            .map(checkbox -> obtenerEquipoPorNombre(checkbox.getText()))
        	            .collect(Collectors.toList());

        	        // Crear la ventana con los equipos seleccionados
        	        VentanaPestanas ventanaPestanas = new VentanaPestanas(comboBoxTemporadas, equiposSeleccionados1);
        	        ventanaPestanas.setVisible(true);
        	        
        	        System.out.println("Equipos seleccionados: " + equiposSeleccionados1);
        	        System.out.println("Creando ventana con los equipos seleccionados...");
        	    });
        
        getContentPane().add(btnConfirmar, BorderLayout.SOUTH);

        // Actualizar lista inicial de equipos
        actualizarEquipos();
    }
    
    private Equipo obtenerEquipoPorNombre(String nombre) {
        for (Temporada temporada : temporadas) {
            for (Equipo equipo : temporada.getEquipos()) {
                if (equipo.getNombre().equals(nombre)) {
                    return equipo;
                }
            }
        }
        return null; // En caso de que no se encuentre el equipo
    }
    
    //inicializar datos
    private void inicializarDatos() {
    	
        temporadas = new ArrayList<>();

        // Temporada 2023
        List<Equipo> equipos2023 = Arrays.asList(
            new Equipo("Cisneros", Arrays.asList("Pepe Viyuela", "Grillo Martínez", "Nico Jr", "Juanillo Juan", "Benito Antonio")),
            new Equipo("Barca", Arrays.asList("Facundo Alonso", "Oscar Mato", "Iñigo Errejón", "Santiago Abascal", "Xavier Cebrián")),
            new Equipo("Labiana", Arrays.asList("Koi Hoggs", "Joaquín", "Mariano Rajoy", "Imanol Gullón", "Jaime Cmarero")),
            new Equipo("Pozuelo", Arrays.asList("Ander Gil", "Jose María", "Arnaitz", "Son-Gijun", "Julian Álvarez")),
            new Equipo("Unio Esportiva", Arrays.asList("Alberto Millán", "Fran Leonori", "Franceso Virgolini", "Ramón Pérez", "Christantus Uche")),
            new Equipo("Las Abelles", Arrays.asList("José Bordalás", "Haritz Pacheco", "Andrés Fernández", "Juanjo Jiménez", "Erik Prieto"))
        );

		// Agregar Temporada
        temporadas.add(new Temporada(2023, equipos2023));
        
        // Ordenar los equipos seleccionados alfabéticamente (A-Z) COMPARANDO EL NOMBRE
        Collections.sort(equipos2023, Comparator.comparing(Equipo::getNombre));

        // Temporada 2024
        List<Equipo> equipos2024 = Arrays.asList(
            new Equipo("Pozuelo", Arrays.asList("Roberto López", "Jose María", "Arnaitz", "Son-Gijun", "Juanillo Juan")),
            new Equipo("Las Abelles", Arrays.asList("José Bordalás", "Sancho Panza", "Andrés Fernández", "Juanjo Jiménez", "Erik Prieto")),
            new Equipo("Labiana", Arrays.asList("Takhasi Inui", "Justin Quiles", "Mariano Rajoy", "Imanol Gullón", "Jaime Cmarero")),
            new Equipo("Unio Esportiva", Arrays.asList("Manuel Turizo", "Alberto Millán", "Ramón Pérez", "Christantus Uche", "Franceso Virgolini")),
            new Equipo("Barca", Arrays.asList("Albert Rivera", "Oscar Mato", "Iñigo Errejón", "Echenique", "Xavier Cebrián")),
            new Equipo("Cisneros", Arrays.asList("Pepe Viyuela", "Antonio Luque", "Nico Jr", "Julian Álvarez", "Benito Antonio")),
            new Equipo("Eibar", Arrays.asList("Martin Villalón", "Urko Ruiz", "Danel Santiago", "Asier Carabantes", "Markel Larreina")),
            new Equipo("Hernani Club", Arrays.asList("Gorka Guruzeta", "Oihan Sancet", "Dudu Gutiérrez", "Juanlu", "Unai Simón"))
        );

		// Agregar Temporada
        temporadas.add(new Temporada(2024, equipos2024));
        // Ordenar los equipos seleccionados alfabéticamente (A-Z) COMPARANDO EL NOMBRE
        Collections.sort(equipos2024, Comparator.comparing(Equipo::getNombre));
    }

    private void actualizarEquipos() {
    	
        panelEquipos.removeAll();
        panelJugadores.removeAll();
        checkboxesEquipos = new ArrayList<>();

        // Limpiar la lista de equipos seleccionados
        equiposSeleccionadosList.clear();
        
        int temporadaSeleccionada = comboBoxTemporadas.getSelectedIndex();

        // Obtener la temporada seleccionada de la lista
        Temporada temporada = temporadas.get(temporadaSeleccionada);

        if (temporadaSeleccionada == 0) { // Temporada 2023: mostrar equipos y jugadores directamente
            for (Equipo equipo : temporada.getEquipos()) {
                // Crear un panel para el equipo
                JPanel panelEquipo = new JPanel();
                panelEquipo.setLayout(new BoxLayout(panelEquipo, BoxLayout.Y_AXIS));
                panelEquipo.setBorder(BorderFactory.createTitledBorder(equipo.getNombre()));

                // Añadir jugadores al panel del equipo
                for (String jugador : equipo.getJugadores()) {
                    JLabel lblJugador = new JLabel(jugador);
                    lblJugador.setHorizontalAlignment(SwingConstants.CENTER);
                    lblJugador.setFont(new Font("Arial", Font.PLAIN, 14));
                    panelEquipo.add(lblJugador);
                }

                // Añadir el panel del equipo al panel de jugadores
                panelJugadores.add(panelEquipo);
            }

            // Ocultar el botón "Confirmar Equipos" para la temporada 2023
            btnConfirmar.setVisible(false);
            
        } else { // Otras temporadas: mostrar equipos con checkboxes
        	
            for (Equipo equipo : temporada.getEquipos()) {
                JCheckBox checkBox = new JCheckBox(equipo.getNombre());
                checkBox.addActionListener(e -> actualizarJugadores());
                checkBox.addActionListener(e -> limitarSeleccionEquipos(checkBox));
                checkboxesEquipos.add(checkBox);
                panelEquipos.add(checkBox);
                
                // Iterar sobre los equipos seleccionados
               
            }

            // Mostrar el botón "Confirmar Equipos" para temporadas diferentes a 2023
            btnConfirmar.setVisible(true);
        }

        // Actualizar la interfaz
        panelEquipos.revalidate();
        panelEquipos.repaint();
        panelJugadores.revalidate();
        panelJugadores.repaint();
    }

    private void limitarSeleccionEquipos(JCheckBox checkBox) {
    	
        // Contar cuántos equipos están seleccionados
        long equiposSeleccionados = checkboxesEquipos.stream().filter(JCheckBox::isSelected).count();

        if (equiposSeleccionados > 6) {
        	
            // Desmarcar el checkbox si se seleccionan más de 6
            checkBox.setSelected(false);
            JOptionPane.showMessageDialog(this, "No puedes seleccionar más de 6 equipos.", "Límite alcanzado", JOptionPane.WARNING_MESSAGE);
            
        } else if (equiposSeleccionados == 6) {
        	
            // Deshabilitar los checkboxes si ya se han seleccionado 6 equipos
            for (JCheckBox cb : checkboxesEquipos) {
            	
                if (!cb.isSelected()) {
                    cb.setEnabled(false);  // Deshabilitar checkbox no seleccionado
                }
            }
            
        } else {
        	
            // Habilitar todos los checkboxes si se han desmarcado algunos
            for (JCheckBox cb : checkboxesEquipos) {
                cb.setEnabled(true);  // Habilitar checkbox
                
            }
        }
    }
    
    private void crearNuevaTemporada() {
    	
    	// Crear campos de texto para ingresar el año y los nombres de los equipos
        JTextField campoAnio = new JTextField();
        JTextField campoEquipos = new JTextField();

        int opcion = JOptionPane.showConfirmDialog(this,new Object[]{
        		"Año de la Temporada:", campoAnio,
                "Nombres de los Equipos (separados por comas):", campoEquipos
            },
            "Crear Nueva Temporada",
            JOptionPane.OK_CANCEL_OPTION
        );

        // Si el usuario presiona OK, procesar la creación de la temporada
        if (opcion == JOptionPane.OK_OPTION) {
            try {
            	// Obtener el año y los nombres de los equipos ingresados
                int anio = Integer.parseInt(campoAnio.getText().trim());
                
                // Validar que el año sea 2025 o mayor
                if (anio < 2025) {
                    JOptionPane.showMessageDialog(this, "El año de la temporada debe ser 2025 o posterior.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Detener el proceso si el año no cumple con la condición
                }
                
                String[] nombresEquipos = campoEquipos.getText().trim().split(",");
                
                // Crear una lista para almacenar los equipos
                List<Equipo> nuevosEquipos = new ArrayList<>();
                
                // Verificar que haya al menos 6 equipos ingresados
                if (nombresEquipos.length < 6) {
                    JOptionPane.showMessageDialog(this, "Debes ingresar al menos 6 equipos para crear la temporada.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Detener la creación si no hay suficientes equipos
                }

                // Crear los objetos Equipo para cada nombre de equipo ingresado
                for (String nombre : nombresEquipos) {
                    nuevosEquipos.add(new Equipo(nombre.trim(), generarJugadoresAleatorios()));
                }

                // Crear la nueva temporada con el año y los equipos generados
                Temporada nuevaTemporada = new Temporada(anio, nuevosEquipos);
                temporadas.add(nuevaTemporada);
                
                // Actualizar el combo box para mostrar la nueva temporada
                comboBoxTemporadas.addItem(String.valueOf(anio));
                comboBoxTemporadas.setSelectedIndex(comboBoxTemporadas.getItemCount() - 1);
                comboBoxTemporadas.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String temporadaSeleccionada = (String) comboBoxTemporadas.getSelectedItem();
                        cargarTemporada(temporadaSeleccionada);
                    }
                });
                
                actualizarEquipos();

                //SI FUNCIONA TODO BIEN SALE ESTE MENSAJE
                JOptionPane.showMessageDialog(this, "Nueva temporada creada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                //Error de que ha puesto letras en vez de numeros al ingresar el año
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
            	// Capturar cualquier otro error y mostrar mensaje de error
                JOptionPane.showMessageDialog(this, "Ocurrió un error.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void guardarTemporadas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("temporadas.txt"))) {
            for (Temporada temporada : temporadas) {
                // Guardar el año de la temporada
                writer.write(temporada.getAnio() + "\n");
                
                // Guardar los equipos de esa temporada
                for (Equipo equipo : temporada.getEquipos()) {
                    writer.write(equipo.getNombre() + "\n");  // Escribe el nombre de los equipos
                }
                
                // Marca el final de una temporada
                writer.write("----\n");  
            }
            JOptionPane.showMessageDialog(this, "Datos guardados con éxito.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void cargarTemporada(String temporada) {
    	
    	 boolean esFinalizada = false;
    	 
        String archivo = "temporada_" + temporada + ".txt";  // El archivo correspondiente

        // Verifica si el archivo existe
        File file = new File(archivo);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                // Aquí puedes leer los datos del archivo y cargarlos en tu aplicación
                String linea;
                while ((linea = reader.readLine()) != null) {
                    // Procesa cada línea leída del archivo
                    System.out.println(linea);  // Ejemplo: mostrar contenido
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Si el archivo no existe, muestra un mensaje o procede sin hacer nada
            System.out.println("El archivo de la temporada no existe.");
        }
    }


    
    private void actualizarJugadores() {
        // Limpiar el panel de jugadores antes de añadir nuevos jugadores
        panelJugadores.removeAll();

        // Iterar sobre los equipos seleccionados
        for (JCheckBox checkBox : checkboxesEquipos) {
            if (checkBox.isSelected()) {
                // Obtener el nombre del equipo seleccionado
                String equipoSeleccionado = checkBox.getText();

                // Encontrar el equipo en la temporada seleccionada
                int temporadaSeleccionada = comboBoxTemporadas.getSelectedIndex();
                if (temporadaSeleccionada >= 0) {
                    List<Equipo> equipos = temporadas.get(temporadaSeleccionada).getEquipos();

                    for (Equipo equipo : equipos) {
                        // Verificar si el equipo seleccionado coincide con el nombre
                        if (equipo.getNombre().equals(equipoSeleccionado)) {
                            // Crear un panel para el equipo seleccionado
                            JPanel panelEquipo = new JPanel();
                            panelEquipo.setBorder(BorderFactory.createTitledBorder(equipo.getNombre()));
                            panelEquipo.setLayout(new BoxLayout(panelEquipo, BoxLayout.Y_AXIS));

                            // Añadir los jugadores al panel del equipo
                            for (String jugador : equipo.getJugadores()) {
                                JLabel lblJugador = new JLabel(jugador);
                                lblJugador.setHorizontalAlignment(SwingConstants.CENTER);
                                lblJugador.setFont(new Font("Arial", Font.PLAIN, 14));
                                panelEquipo.add(lblJugador);
                                panelEquipo.add(lblJugador);
                            }

                            // Añadir el panel del equipo al panel de jugadores
                            panelJugadores.add(panelEquipo);
                        }
                    }
                }
            }
        }
        panelJugadores.revalidate();
        panelJugadores.repaint();
    }
    
    private List<String> generarJugadoresAleatorios() {
        String[] nombres = {"Pepe", "Juan", "Carlos", "Luis", "Manuel", "Andrés", "Marcos", "Javier", "David", "Miguel", "Andrés","Jorge"};
        String[] apellidos = {"García", "López", "Pérez", "Martínez", "Sánchez", "Ramírez", "Gómez", "Díaz", "Hernández", "Jiménez","Luque","Sieso"};
        List<String> jugadores = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            String nombre = nombres[random.nextInt(nombres.length)];
            String apellido = apellidos[random.nextInt(apellidos.length)];
            jugadores.add(nombre + " " + apellido);
        }

        return jugadores;
    }

    private void confirmarSeleccionEquipos() {
        // Contar cuántos equipos están seleccionados
        long equiposSeleccionados = checkboxesEquipos.stream().filter(JCheckBox::isSelected).count();

        if (equiposSeleccionados == 6) {
            // Si se han seleccionado exactamente 6 equipos, proceder con la confirmación
            List<String> equiposSeleccionadosList = new ArrayList<>();
            for (JCheckBox checkBox : checkboxesEquipos) {
                if (checkBox.isSelected()) {
                    equiposSeleccionadosList.add(checkBox.getText());
                }
            }

            // Ordenar los equipos seleccionados alfabéticamente (A-Z)
            Collections.sort(equiposSeleccionadosList);
            
            // Crear una nueva ventana para mostrar la clasificación
            JFrame clasificacionFrame = new JFrame("Clasificación de Equipos");
            clasificacionFrame.setSize(500, 300);
            clasificacionFrame.setLocationRelativeTo(null);
            clasificacionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Definir las columnas de la tabla
            String[] columnas = {"Equipo", "Puntos", "Victorias", "Derrotas"};

            // Crear los datos de la tabla (inicialmente 0 puntos, 0 victorias, 0 derrotas)
            Object[][] datos = new Object[equiposSeleccionadosList.size()][4];
            for (int i = 0; i < equiposSeleccionadosList.size(); i++) {
                datos[i][0] = equiposSeleccionadosList.get(i); // Nombre del equipo
                datos[i][1] = 0; // Puntos
                datos[i][2] = 0; // Victorias
                datos[i][3] = 0; // Derrotas
            }

            // Crear la tabla con los datos
            JTable tablaClasificacion = new JTable(datos, columnas);
            tablaClasificacion.setFillsViewportHeight(true);
            tablaClasificacion.getTableHeader().setReorderingAllowed(false); // Deshabilitar reordenamiento
            tablaClasificacion.setDefaultEditor(Object.class, null);

            // Crear un panel con scroll para la tabla
            JScrollPane scrollPane = new JScrollPane(tablaClasificacion);

            // Crear los botones de exportación
            JButton btnExportarXML = new JButton("Exportar a XML");
            btnExportarXML.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    exportarXML(equiposSeleccionadosList, datos);
                }
            });

            JButton btnExportarPDF = new JButton("Exportar a PDF");
            btnExportarPDF.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    exportarPDF(equiposSeleccionadosList, datos);
                }
            });

            // Crear un panel para los botones de exportación
            JPanel panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER)); // Alinear los botones a la derecha
            panelBotones.add(btnExportarXML);
            panelBotones.add(btnExportarPDF);

            // Configurar la ventana
            clasificacionFrame.setLayout(new BorderLayout()); // Usar BorderLayout para el contenedor principal
            clasificacionFrame.add(scrollPane, BorderLayout.CENTER); // Agregar la tabla al centro
            clasificacionFrame.add(panelBotones, BorderLayout.SOUTH); // Agregar el panel con los botones en la parte baja

            clasificacionFrame.setVisible(true);
            this.dispose();
        } 
    }

 // Método para exportar a XML
 

    private void exportarXML(List<String> equipos, Object[][] datos ) {
    	
    	int añoTemporada = Integer.parseInt(comboBoxTemporadas.getSelectedItem().toString());
  	
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Crear el elemento raíz
            Element rootElement = doc.createElement("Clasificacion");
            doc.appendChild(rootElement);

            // Recorrer los equipos y agregar los datos
            for (int i = 0; i < equipos.size(); i++) {
                Element equipoElement = doc.createElement("Equipo");

                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode(equipos.get(i)));
                equipoElement.appendChild(nombre);

                Element puntos = doc.createElement("Puntos");
                puntos.appendChild(doc.createTextNode(datos[i][1].toString()));
                equipoElement.appendChild(puntos);

                Element victorias = doc.createElement("Victorias");
                victorias.appendChild(doc.createTextNode(datos[i][2].toString()));
                equipoElement.appendChild(victorias);

                Element derrotas = doc.createElement("Derrotas");
                derrotas.appendChild(doc.createTextNode(datos[i][3].toString()));
                equipoElement.appendChild(derrotas);

                rootElement.appendChild(equipoElement);
            }

            // Definir la carpeta donde se guardará el archivo
            String carpeta = "C:/xampp/htdocs/xml";
            File directorio = new File(carpeta);
            
            // Si la carpeta no existe, crearla
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Nombre del archivo XML (ejemplo: Clasificacion_2025.xml)
            String nombreArchivo =  añoTemporada + ".xml";
            File archivoXML = new File(directorio, nombreArchivo);

            // Guardar el XML en la carpeta
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(archivoXML);
            transformer.transform(source, result);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "Se exportó el XML correctamente", 
                                          "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al exportar el XML.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    
    private void exportarPDF(List<String> equiposSeleccionados, Object[][] datos) {  
    	try {
            // Crear documento PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("clasificacion.pdf"));
            document.open();

            // Título del documento
            document.add(new Paragraph("Clasificación de Equipos"));

            // Crear tabla para el PDF
            PdfPTable table = new PdfPTable(4); // 4 columnas
            table.addCell("Equipo");
            table.addCell("Puntos");
            table.addCell("Victorias");
            table.addCell("Derrotas");

            // Agregar datos a la tabla
            for (int i = 0; i < equiposSeleccionados.size(); i++) {
    			table.addCell(equiposSeleccionados.get(i));
                table.addCell(String.valueOf(datos[i][1]));
                table.addCell(String.valueOf(datos[i][2]));
                table.addCell(String.valueOf(datos[i][3]));
            }

            // Agregar tabla al documento
            document.add(table);
            document.close();

            JOptionPane.showMessageDialog(null, "Datos exportados a PDF con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
    }
  }

    private void accederALaPaginaPrincipal() {
        // Crear la ventana de la página principal (MainPage) y mostrarla
    	 int permiso = 0; // Cambia el valor del permiso según sea necesario
    	    mainPage vh = new mainPage(permiso);
      
    	    vh.setVisible(true);

        // Cerrar la ventana actual (TemporadasFrame) si lo deseas
        this.dispose();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
