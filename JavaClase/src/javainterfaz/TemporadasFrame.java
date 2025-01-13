
package javainterfaz;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

	// Clase Equipo
	class Equipo {
    private String nombre;
    private List<String> jugadores;

    public Equipo(String nombre, List<String> jugadores) {
        this.nombre = nombre;
        this.jugadores = jugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getJugadores() {
        return jugadores;
    }
}

	// Clase Temporada
	class Temporada {
    private int anio;
    private List<Equipo> equipos;

    public Temporada(int anio, List<Equipo> equipos) {
        this.anio = anio;
        this.equipos = equipos;
    }

    public int getAnio() {
        return anio;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }
    
    //CLASE CLASIFICACION
    class Clasificacion {
        private String nombreEquipo;
        private int puntos;

        public Clasificacion(String nombreEquipo) {
            this.nombreEquipo = nombreEquipo;
            this.puntos = 0; // Inicialmente, los equipos tienen 0 puntos
        }

        public String getNombreEquipo() {
            return nombreEquipo;
        }

        public int getPuntos() {
            return puntos;
        }

        public void setPuntos(int puntos) {
            this.puntos = puntos;
        }
    
}}
//VENTANA CLASIFICACION CREADA AUTOMATICAMENTE 
class ClasificacionWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    public ClasificacionWindow(List<String> equiposSeleccionados) {
        setTitle("Clasificación de Equipos");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear un panel para mostrar la clasificación
        JPanel panelClasificacion = new JPanel();
        panelClasificacion.setLayout(new BoxLayout(panelClasificacion, BoxLayout.Y_AXIS));

        // Mostrar los equipos seleccionados con puntos (inicialmente 0)
        for (String equipo : equiposSeleccionados) {
            JPanel panelEquipo = new JPanel();
            panelEquipo.setLayout(new BorderLayout());
            panelEquipo.add(new JLabel(equipo), BorderLayout.WEST); // Nombre del equipo
            panelEquipo.add(new JLabel("0 Puntos"), BorderLayout.EAST); // Puntos iniciales
            panelClasificacion.add(panelEquipo);
            
        }

        JScrollPane scrollPane = new JScrollPane(panelClasificacion);
        add(scrollPane);
        
      
		
    }
}


// Clase principal para la interfaz gráfica
public class TemporadasFrame extends JFrame {
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


    public TemporadasFrame() {
        // Configuración básica del frame
        setTitle("Gestión de Temporadas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout(10, 10));

        // Inicializar datos de ejemplo
        inicializarDatos();

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
            // Abrir la ventana con las pestañas
            VentanaPestanas ventanaPestanas = new VentanaPestanas();
            ventanaPestanas.setVisible(true);
        });
      
        
        getContentPane().add(btnConfirmar, BorderLayout.SOUTH);

        // Actualizar lista inicial de equipos
        actualizarEquipos();
    }
    
   

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
        temporadas.add(new Temporada(2023, equipos2023));

        // Temporada 2024
        List<Equipo> equipos2024 = Arrays.asList(
            new Equipo("Pozuelo", Arrays.asList("Roberto López", "Jose María", "Arnaitz", "Son-Gijun", "Juanillo Juan")),
            new Equipo("Las Abelles", Arrays.asList("José Bordalás", "Sancho Panza", "Andrés Fernández", "Juanjo Jiménez", "Erik Prieto")),
            new Equipo("Labiana", Arrays.asList("Takhasi Inui", "Justin Quiles", "Mariano Rajoy", "Imanol Gullón", "Jaime Cmarero")),
            new Equipo("Unio Esportiva", Arrays.asList("Manuel Turizo", "Alberto Millán", "Ramón Pérez", "Christantus Uche", "Franceso Virgolini")),
            new Equipo("Barca", Arrays.asList("Albert Rivera", "Oscar Mato", "Iñigo Errejón", "Echenique", "Xavier Cebrián")),
            new Equipo("Cisneros", Arrays.asList("Pepe Viyuela", "Antonio Luque", "Nico Jr", "Julian Álvarez", "Benito Antonio")),
            new Equipo("Eibar", Arrays.asList("Iosu Cabrera", "Endika Sanchez", "Danel Santiago", "Asier Carabantes", "Markel Larreina")),
            new Equipo("Hernani Club", Arrays.asList("Gorka Guruzeta", "Oihan Sancet", "Dudu Gutiérrez", "Juanlu", "Unai Simón"))
        );
        temporadas.add(new Temporada(2024, equipos2024));
    }

    private void actualizarEquipos() {
        panelEquipos.removeAll();
        panelJugadores.removeAll();
        checkboxesEquipos = new ArrayList<>();

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
    
    private void crearPestanas() {
        for (int i = 1; i <= 10; i++) {
            // Crear un panel para cada pestaña
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            tabbedPane.addTab("Clasificación", panel);

            // Agregar contenido al panel de cada pestaña
            panel.add(new JLabel("Contenido de la pestaña " + i));
            panel.add(new JTextArea("Este es el contenido de la pestaña " + i));

            // Añadir el panel a la pestaña
            JScrollPane scrollPanel = new JScrollPane(panel);
            tabbedPane.addTab("Pestaña " + i, scrollPanel);
        }
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

        int opcion = JOptionPane.showConfirmDialog(
            this,
            new Object[]{
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

            // Crear un panel con scroll para la tabla
            JScrollPane scrollPane = new JScrollPane(tablaClasificacion);
            
            // Configurar la ventana
            clasificacionFrame.add(scrollPane);
            clasificacionFrame.setVisible(true);
        } else {
            // Si no se han seleccionado exactamente 6 equipos, mostrar un mensaje de advertencia
            JOptionPane.showMessageDialog(this, "Debes seleccionar exactamente 6 equipos para confirmar.", "Selección incorrecta", JOptionPane.WARNING_MESSAGE);
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
}
