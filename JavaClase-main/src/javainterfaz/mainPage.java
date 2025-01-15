package javainterfaz; // Declaración del paquete que contiene la clase

import java.util.*; // Importar utilidades, como Listas y Mapas
import java.awt.BorderLayout; // Importar layout para la disposición de la GUI
import java.awt.Color; // Importar para utilizar colores en componentes
import java.awt.EventQueue; // Importar para la gestión de eventos
import java.awt.Font; // Importar para usar diferentes fuentes de texto
import javax.swing.*; // Importar todos los componentes de Swing para construir la interfaz gráfica
import javax.swing.border.EmptyBorder; // Importar bordes vacíos para componentes
import javax.swing.table.DefaultTableModel; // Importar modelo de tabla por defecto
import java.awt.event.ActionEvent; // Importar clases para manejar eventos de acción
import java.awt.event.ActionListener; // Importar interfaz para escuchar eventos
import javax.swing.text.*; // Importar clases para el manejo de texto
import java.time.LocalDate; // Importar para manejar fechas
import java.time.Month; // Importar para utilizar meses

public class mainPage extends JFrame implements ActionListener { // Definición de la clase que hereda de JFrame e implementa ActionListener

    private static final long serialVersionUID = 1L; // Uso de serialVersionUID para la serialización de objetos

    // Declaración de variables y componentes de la interfaz
    private JPanel contentPane; // Panel principal que contendrá toda la interfaz
    private JTextField puntos1, puntos2, puntos3, puntos4, puntos5, puntos6; // Campos de texto para ingresar puntos de los equipos
    private JLabel lblEquipo1, lblEquipo2, lblEquipo3, lblEquipo4, lblEquipo5, lblEquipo6; // Etiquetas para mostrar los nombres de los equipos
    private JButton btnNext, btnBack, btnUpdate, btnReset; // Botones para la navegación y las acciones
    private JLabel lblJornada; // Etiqueta que muestra la jornada actual
    private JButton iniciarsesion; // Botón para volver a la ventana de inicio
    private JPanel centro; // Panel que contendrá los partidos y resultados

    // Matriz para guardar los resultados de las jornadas: 10 jornadas, 6 resultados (para 3 partidos entre 2 equipos)
    private int[][] resultados = new int[10][6];

    // Datos de los partidos en formato {jornada, equipo1, golesEquipo1, equipo2, golesEquipo2}
    Object[][] partidos = {
        {1, "Cisneros", 2, "Pozuelo", 1},
        {1, "Barca", 3, "Unio Esportiva", 1},
        {1, "Labiana", 1, "Las Abelles", 0},              
        {2, "Pozuelo", 0, "Las Abelles", 1},
        {2, "Unio Esportiva", 2, "Labiana", 2},
        {2, "Cisneros", 1, "Barca", 1},             
        {3, "Barca", 3, "Pozuelo", 1},
        {3, "Labiana", 2, "Cisneros", 2},
        {3, "Las Abelles", 3, "Unio Esportiva", 0},       
        {4, "Pozuelo", 2, "Unio Esportiva", 1},
        {4, "Cisneros", 1, "Las Abelles", 2},
        {4, "Barca", 2, "Labiana", 1},       
        {5, "Labiana", 2, "Pozuelo", 0},
        {5, "Las Abelles", 1, "Barca", 2},
        {5, "Unio Esportiva", 3, "Cisneros", 1},
        {6, "Pozuelo", 1, "Cisneros", 1},
        {6, "Unio Esportiva", 2, "Barca", 2},
        {6, "Las Abelles", 2, "Labiana", 3},
        {7, "Las Abelles", 3, "Pozuelo", 0},
        {7, "Labiana", 1, "Unio Esportiva", 1},
        {7, "Barca", 2, "Cisneros", 0},
        {8, "Pozuelo", 1, "Barca", 2},
        {8, "Cisneros", 1, "Labiana", 3},
        {8, "Unio Esportiva", 2, "Las Abelles", 2},
        {9, "Unio Esportiva", 1, "Pozuelo", 0},
        {9, "Las Abelles", 3, "Cisneros", 1},
        {9, "Labiana", 2, "Barca", 1},
        {10, "Pozuelo", 0, "Labiana", 2},
        {10, "Barca", 1, "Las Abelles", 2},
        {10, "Cisneros", 2, "Unio Esportiva", 1},
    };

    private DefaultTableModel modelo; // Modelo de tabla para guardar la clasificación de los equipos

    public static void main(String[] args) { // Método principal que se ejecuta al iniciar el programa
        EventQueue.invokeLater(() -> { // Ejecutar en el hilo de eventos
            try {
                int permiso = 2; // Cambia el valor del permiso según sea necesario, 2 representa un usuario administrador
                mainPage frame = new mainPage(permiso); // Crear una nueva instancia de la página principal
                frame.setVisible(true); // Hacer visible la ventana
            } catch (Exception e) {
                e.printStackTrace(); // Manejar excepciones y mostrar el error en la consola
            }
        });
    }

    public mainPage(int permiso) { // Constructor de la clase. Se llama al crear una nueva instancia
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configurar la operación al cerrar la ventana
        setSize(800, 600); // Establecer el tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setResizable(false); // No permitir que la ventana sea redimensionable

        // Panel principal que contendrá toda la interfaz
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Establecer un borde vacío alrededor del panel
        setContentPane(contentPane); // Asignar el panel al contenido de la ventana
        contentPane.setLayout(new BorderLayout(0, 0)); // Usar un layout de BorderLayout para el panel

        // Panel en el lado izquierdo que contendrá los resultados de los partidos
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.WEST); // Añadir panel al lado izquierdo
        panel.setLayout(new BorderLayout(0, 0)); // Usar BorderLayout para este panel

        // Panel para controles de la jornada (navegación, títulos)
        JPanel panel_1 = new JPanel();
        panel.add(panel_1, BorderLayout.NORTH); // Añadir este panel en la parte superior
        panel_1.setLayout(new BorderLayout(0, 0)); // Usar BorderLayout

        centro = new JPanel(); // Crear un nuevo panel para el centro
        panel_1.add(centro, BorderLayout.CENTER); // Añadir panel en el centro del panel_1
        centro.setLayout(new BorderLayout(0, 0)); // Usar BorderLayout

        // Crear un panel que contenga la etiqueta de "Partidos"
        JPanel Partidos = new JPanel();
        centro.add(Partidos, BorderLayout.NORTH); // Añadir en el norte del panel central
        JLabel lblNewLabel_1 = new JLabel("Partidos "); // Crear etiqueta
        Partidos.add(lblNewLabel_1); // Añadir etiqueta al panel de Partidos

        // Crear un panel para los campos de los resultados
        JPanel panel_2 = new JPanel();
        centro.add(panel_2, BorderLayout.CENTER); // Añadir en el centro del panel central
        panel_2.setLayout(new BorderLayout(0, 0)); // Usar BorderLayout

        // Crear un subpanel para la parte superior que contendrá los resultados
        JPanel panel_4 = new JPanel();
        panel_2.add(panel_4, BorderLayout.NORTH); // Añadir en la parte superior
        panel_4.setLayout(new BorderLayout(0, 0)); // Usar BorderLayout

        // Crear otro subpanel que contendrá los resultados del primer par de equipos
        JPanel panel_5 = new JPanel();
        panel_4.add(panel_5, BorderLayout.NORTH); // Añadir en la parte norte de panel_4
        lblEquipo1 = new JLabel("Equipo 1"); // Crear etiqueta para primer equipo
        panel_5.add(lblEquipo1); // Añadir etiqueta al panel_5
        puntos1 = new JTextField(); // Crear un campo de texto para ingresar resultados
        panel_5.add(puntos1); // Añadir campo de texto al panel_5
        puntos1.setColumns(5); // Establecer tamaño del campo de texto
        lblEquipo2 = new JLabel("Equipo 2"); // Crear etiqueta para segundo equipo
        panel_5.add(lblEquipo2); // Añadir etiqueta al panel_5
        puntos2 = new JTextField(); // Crear un campo de texto para el segundo equipo
        panel_5.add(puntos2); // Añadir al panel
        puntos2.setColumns(5); // Establecer tamaño del campo de texto

        // Subpanel para los siguientes resultados de partidos
        JPanel panel_6 = new JPanel();
        panel_4.add(panel_6, BorderLayout.CENTER); // Añadir en el centro de panel_4
        panel_6.setLayout(new BorderLayout(0, 0)); // Usar BorderLayout

        // Subpanel para los resultados del tercer y cuarto equipo
        JPanel panel_7 = new JPanel();
        panel_6.add(panel_7, BorderLayout.NORTH); // Añadir en la parte norte de panel_6
        lblEquipo3 = new JLabel("Equipo 3"); // Crear etiqueta para tercer equipo
        panel_7.add(lblEquipo3); // Añadir etiqueta al panel
        puntos3 = new JTextField(); // Campo de texto para el tercer equipo
        panel_7.add(puntos3); // Añadir campo de texto al panel
        puntos3.setColumns(5); // Establecer tamaño
        lblEquipo4 = new JLabel("Equipo 4"); // Etiqueta para cuarto equipo
        panel_7.add(lblEquipo4); // Añadir etiqueta al panel
        puntos4 = new JTextField(); // Campo de texto para cuarto equipo
        panel_7.add(puntos4); // Añadir campo de texto
        puntos4.setColumns(5); // Establecer tamaño

        // Subpanel para resultados de quinto y sexto equipo
        JPanel panel_8 = new JPanel();
        panel_6.add(panel_8, BorderLayout.CENTER); // Añadir en el centro de panel_6
        panel_8.setLayout(new BorderLayout(0, 0)); // Usar BorderLayout

        // Subpanel para el quinto equipo
        JPanel panel_9 = new JPanel();
        panel_8.add(panel_9, BorderLayout.NORTH); // Añadir en la parte norte
        lblEquipo5 = new JLabel("Equipo 5"); // Etiqueta para quinto equipo
        panel_9.add(lblEquipo5); // Añadir al panel
        puntos5 = new JTextField(); // Campo de texto para quinto equipo
        panel_9.add(puntos5); // Añadir campo
        puntos5.setColumns(5); // Establecer tamaño
        lblEquipo6 = new JLabel("Equipo 6"); // Etiqueta para sexto equipo
        panel_9.add(lblEquipo6); // Añadir etiqueta
        puntos6 = new JTextField(); // Campo de texto para sexto equipo
        panel_9.add(puntos6); // Añadir campo
        puntos6.setColumns(5); // Establecer tamaño

        // Panel para la parte inferior que contendrá el botón de sesión
        JPanel sesion = new JPanel();
        panel.add(sesion, BorderLayout.CENTER); // Añadir al centro del panel
        sesion.setLayout(new BorderLayout(0, 0)); // Usar BorderLayout

        // Subpanel para la parte inferior de la sesión
        JPanel sesion2 = new JPanel();
        sesion.add(sesion2, BorderLayout.SOUTH); // Añadir en la parte inferior
        sesion2.setLayout(new BorderLayout(0, 0)); // Usar BorderLayout

        // Botón para regresar a la ventana de inicio
        iniciarsesion = new JButton("Cerrar sesión");
        sesion2.add(iniciarsesion); // Añadir botón al panel de sesión
        iniciarsesion.setFont(new Font("Verdana", Font.BOLD, 11)); // Establecer fuente del botón
        iniciarsesion.setForeground(new Color(0, 0, 0)); // Establecer color del texto
        iniciarsesion.addActionListener(this); // Registrar este método como el escucha de la acción

        // Configuración de filtros para aceptar solo números enteros en los campos
        ((AbstractDocument) puntos1.getDocument()).setDocumentFilter(new IntegerFilter());
        ((AbstractDocument) puntos2.getDocument()).setDocumentFilter(new IntegerFilter());
        ((AbstractDocument) puntos3.getDocument()).setDocumentFilter(new IntegerFilter());
        ((AbstractDocument) puntos4.getDocument()).setDocumentFilter(new IntegerFilter());
        ((AbstractDocument) puntos5.getDocument()).setDocumentFilter(new IntegerFilter());
        ((AbstractDocument) puntos6.getDocument()).setDocumentFilter(new IntegerFilter());

        // Panel superior que contendrá la información sobre la jornada
        JPanel top = new JPanel();
        panel_1.add(top, BorderLayout.NORTH); // Añadir en la parte norte de panel_1
        top.setLayout(new BorderLayout(0, 0)); // Usar BorderLayout

        // Subpanel para mostrar el título de la jornada
        JPanel titulo = new JPanel();
        top.add(titulo, BorderLayout.NORTH); // Añadir en la parte norte
        titulo.setLayout(new BorderLayout(0, 0)); // Usar BorderLayout

        // Subpanel para la etiqueta "Jornada"
        JPanel panel_3 = new JPanel();
        titulo.add(panel_3, BorderLayout.NORTH); // Añadir en la parte norte de titulo
        JLabel lblNewLabel = new JLabel("Jornada"); // Crear etiqueta
        panel_3.add(lblNewLabel); // Añadir etiqueta al panel

        // Subpanel para la navegación de la jornada
        JPanel seleccionJornada = new JPanel();
        titulo.add(seleccionJornada, BorderLayout.SOUTH); // Añadir en la parte sur del título 
        btnBack = new JButton("<"); // Botón para ir a la jornada anterior
        btnBack.addActionListener(this); // Register this button to listen for actions
        seleccionJornada.add(btnBack); // Añadir botón al panel de selección
        lblJornada = new JLabel("1"); // Etiqueta que muestra la jornada actual, inicializada en 1
        seleccionJornada.add(lblJornada); // Añadir etiqueta al panel de selección
        btnNext = new JButton(">"); // Botón para ir a la siguiente jornada
        btnNext.addActionListener(this); // Register this button to listen for actions
        seleccionJornada.add(btnNext); // Añadir botón al panel de selección

        // Panel para la clasificación
        JPanel clasificacion = new JPanel();
        panel.add(clasificacion, BorderLayout.SOUTH); // Añadir en la parte sur del panel
        clasificacion.setLayout(new BorderLayout(0, 0)); // Usar BorderLayout

        // Botón para actualizar la clasificación en base a los resultados
        btnUpdate = new JButton("Actualizar Clasificación");
        btnUpdate.addActionListener(this); // Registrar acción
        clasificacion.add(btnUpdate); // Añadir botón al panel de clasificación

        // Botón para reiniciar los resultados a cero
        btnReset = new JButton("Restablecer Resultados");
        btnReset.addActionListener(this); // Registrar acción
        clasificacion.add(btnReset, BorderLayout.SOUTH); // Añadir en el fondo del panel de clasificación

        // Protección de funcionalidad según permisos
        switch (permiso) {
            case 2: // Para administrador
                break; // El administrador tiene todas las funciones
            case 1: // Para árbitro
                btnReset.setVisible(false); // Ocultar botón de restablecimiento
                break;
            default: // Usuario no válido o inexistente
                btnUpdate.setVisible(false); // Ocultar botón de actualización
                puntos1.setEditable(false); // Deshabilitar edición en los campos 
                puntos2.setEditable(false);
                puntos3.setEditable(false);
                puntos4.setEditable(false);
                puntos5.setEditable(false);
                puntos6.setEditable(false);
                btnReset.setVisible(false); // Ocultar el botón de reinicio
                break;
        }

        // Panel derecho para la clasificación
        JPanel panel_12 = new JPanel();
        contentPane.add(panel_12, BorderLayout.EAST); // Añadir al lado derecho de la ventana
        panel_12.setLayout(new BorderLayout(0, 0)); // Usar BorderLayout

        // Título de clasificación en el panel derecho
        JPanel panel_13 = new JPanel();
        panel_12.add(panel_13, BorderLayout.NORTH); // Añadir en la parte norte
        JLabel lblNewLabel_8 = new JLabel("Clasificación"); // Crear etiqueta
        panel_13.add(lblNewLabel_8); // Añadir etiqueta al panel

        // Panel central para mostrar los datos de clasificación
        JPanel panel_11 = new JPanel();
        panel_12.add(panel_11, BorderLayout.CENTER); // Añadir en el centro

        // Inicialización de la tabla de clasificación
        String[] columnasClasificacion = {"Equipo", "Puntos", "Victorias", "Derrotas"}; // Encabezados de la tabla
        String[][] datosClasificacion = {
            {"Barca", "0", "0", "0"},
            {"Cisneros", "0", "0", "0"},
            {"Labiana", "0", "0", "0"},
            {"Las Abelles", "0", "0", "0"},
            {"Unio Esportiva", "0", "0", "0"},
            {"Pozuelo", "0", "0", "0"}
        };
        modelo = new DefaultTableModel(datosClasificacion, columnasClasificacion); // Crear modelo de tabla
        JTable tablaClasificacion = new JTable(modelo); // Crear tabla utilizando el modelo
        JScrollPane scrollPane = new JScrollPane(tablaClasificacion); // Crear un scroll para la tabla
        panel_11.setLayout(new BorderLayout(0, 0)); // Usar BorderLayout
        panel_11.add(scrollPane, BorderLayout.CENTER); // Añadir la tabla al centro del panel
        tablaClasificacion.setEnabled(false); // Deshabilitar la edición de la tabla

        // Establecer resultados predeterminados y nombres de equipos
        setDefaultResults(); // Establecer resultados por defecto
        actualizarNombresEquipos(1); // Actualizar los nombres de equipos de la jornada 1
        actualizarClasificacion(); // Actualizar la clasificación
    }

    private void setDefaultResults() {
        // Inicializar resultados predeterminados para cada jornada
        resultados[0] = new int[]{2, 1, 1, 2, 0, 1}; // Resultados para jornada 1
        resultados[1] = new int[]{0, 1, 2, 1, 0, 3}; // Resultados para jornada 2
        resultados[2] = new int[]{3, 0, 1, 1, 2, 0}; // Resultados para jornada 3
        resultados[3] = new int[]{2, 1, 1, 0, 3, 1}; // Resultados para jornada 4
        resultados[4] = new int[]{2, 0, 1, 1, 0, 3}; // Resultados para jornada 5
        resultados[5] = new int[]{1, 1, 2, 0, 2, 1}; // Resultados para jornada 6
        resultados[6] = new int[]{3, 0, 1, 1, 2, 1}; // Resultados para jornada 7
        resultados[7] = new int[]{1, 2, 2, 0, 3, 0}; // Resultados para jornada 8
        resultados[8] = new int[]{2, 1, 0, 1, 3, 2}; // Resultados para jornada 9
        resultados[9] = new int[]{1, 2, 0, 2, 1, 0}; // Resultados para jornada 10
    }

    private void actualizarNombresEquipos(int jornada) {
        // Actualiza las etiquetas con los nombres de los equipos de la jornada actual
        int contador = 0; // Contador para controlar qué equipo se está mostrando

        // Limpiar etiquetas de equipos
        lblEquipo1.setText("");
        lblEquipo2.setText("");
        lblEquipo3.setText("");
        lblEquipo4.setText("");
        lblEquipo5.setText("");
        lblEquipo6.setText("");

        // OBTENCIÓN DE EQUIPOS POR JORNADA:
        for (int i = 0; i < partidos.length; i++) {
            if ((int) partidos[i][0] == jornada) { // Si el partido es de la jornada actual
                String equipo1 = (String) partidos[i][1]; // Obtener nombre del equipo 1
                String equipo2 = (String) partidos[i][3]; // Obtener nombre del equipo 2

                switch (contador) {
                    case 0:
                        lblEquipo1.setText(equipo1); // Asignar nombre del equipo 1 a la etiqueta correspondiente
                        lblEquipo2.setText(equipo2); // Asignar nombre del equipo 2
                        break;
                    case 1:
                        lblEquipo3.setText(equipo1); // Asignar nombre del equipo 1 del segundo partido
                        lblEquipo4.setText(equipo2); // Asignar nombre del equipo 2
                        break;
                    case 2:
                        lblEquipo5.setText(equipo1); // Asignar nombre del equipo 1 del tercer partido
                        lblEquipo6.setText(equipo2); // Asignar nombre del equipo 2
                        break;
                    default:
                        break;
                }
                contador++; // Incrementar el contador para el siguiente equipo
            }
        }

        // Cargar resultados de la jornada actual
        cargarResultadosPorJornada(jornada); // Llamar al método para cargar resultados
    }

    private void cargarResultadosPorJornada(int jornada) {
        // Cargar los resultados en los campos de texto según la jornada actual
        int[] resultadosActuales = resultados[jornada - 1]; // Obtener resultados de la jornada
        puntos1.setText(String.valueOf(resultadosActuales[0])); // Mostrar resultado equipo 1
        puntos2.setText(String.valueOf(resultadosActuales[1])); // Mostrar resultado equipo 2
        puntos3.setText(String.valueOf(resultadosActuales[2])); // Mostrar resultado equipo 3
        puntos4.setText(String.valueOf(resultadosActuales[3])); // Mostrar resultado equipo 4
        puntos5.setText(String.valueOf(resultadosActuales[4])); // Mostrar resultado equipo 5
        puntos6.setText(String.valueOf(resultadosActuales[5])); // Mostrar resultado equipo 6
    }

    private void guardarResultadosPorJornada(int jornada) {
        // Guardar los resultados introducidos en los campos de texto para la jornada actual
        try {
            resultados[jornada - 1][0] = Integer.parseInt(puntos1.getText()); // Guardar resultado equipo 1
            resultados[jornada - 1][1] = Integer.parseInt(puntos2.getText()); // Guardar resultado equipo 2
            resultados[jornada - 1][2] = Integer.parseInt(puntos3.getText()); // Guardar resultado equipo 3
            resultados[jornada - 1][3] = Integer.parseInt(puntos4.getText()); // Guardar resultado equipo 4
            resultados[jornada - 1][4] = Integer.parseInt(puntos5.getText()); // Guardar resultado equipo 5
            resultados[jornada - 1][5] = Integer.parseInt(puntos6.getText()); // Guardar resultado equipo 6
        } catch (NumberFormatException ex) {
            // Mostrar error si la entrada no es válida
            JOptionPane.showMessageDialog(null, "Por favor, introduce solo números válidos para los resultados.",
                    "Error", JOptionPane.ERROR_MESSAGE); // Mostrar mensaje de error
        }
    }

    // Clase que representa un equipo con sus estadísticas
    class Equipo {
        String nombre; // Nombre del equipo
        int puntos; // Puntos acumulados
        int victorias; // Numero de victorias
        int derrotas; // Numero de derrotas
        LocalDate fechaCreacion; // Fecha de creación del equipo

        public Equipo(String nombre, int puntos, int victorias, int derrotas, LocalDate fechaCreacion) {
            // Constructor para inicializar un equipo
            this.nombre = nombre; // Asignar el nombre del equipo
            this.puntos = puntos; // Asignar puntos
            this.victorias = victorias; // Asignar número de victorias
            this.derrotas = derrotas; // Asignar número de derrotas
            this.fechaCreacion = fechaCreacion; // Asignar fecha de creación
        }
    }

    public void actualizarClasificacion() {
        // Método para actualizar la clasificación según los resultados
        Map<String, int[]> clasificacion = new HashMap<>(); // Mapa para almacenar estadísticas de equipos Y luego Recuperarlas

        // Recorrer los resultados y calcular estadísticas para cada equipo
        for (int i = 0; i < resultados.length; i++) {
            for (int j = 0; j < 3; j++) { // Hay 3 partidos por jornada
                int partidoIndex = i * 3 + j; // Calcular Numero del partido // J sirve para saber en que jornda estamos actualmente por 0,1,2,3....
                if (partidoIndex < partidos.length) { // Comprobar que el Numero está dentro del rango
                    String equipo1 = (String) partidos[partidoIndex][1]; // Obtener nombre del primer equipo
                    int goles1 = resultados[i][j * 2]; // Obtener goles del primer equipo
                    String equipo2 = (String) partidos[partidoIndex][3]; // Obtener nombre del segundo equipo
                    int goles2 = resultados[i][j * 2 + 1]; // Obtener goles del segundo equipo

                    // Inicializar ambos equipos en el Map si no existen
                    clasificacion.putIfAbsent(equipo1, new int[4]); // [0] = puntos, [1] = victorias, [2] = derrotas
                    clasificacion.putIfAbsent(equipo2, new int[4]);

                    // Contabilizando resultados válidos
                    if (goles1 > 0 || goles2 > 0) { // Solo contabilizar partidos con resultados válidos
                        if (goles1 > goles2) { // Equipo 1 gana
                            clasificacion.get(equipo1)[0] += 4; // 4 puntos para el ganador
                            clasificacion.get(equipo1)[1] += 1; // Incrementar victorias del equipo 1
                            clasificacion.get(equipo2)[2] += 1; // Incrementar derrotas del equipo 2
                        } else if (goles1 < goles2) { // Equipo 2 gana
                            clasificacion.get(equipo2)[0] += 4; // 4 puntos para el ganador
                            clasificacion.get(equipo1)[2] += 1; // Incrementar derrotas del equipo 1
                            clasificacion.get(equipo2)[1] += 1; // Incrementar victorias del equipo 2
                        } else { // Empate
                            clasificacion.get(equipo1)[0] += 2; // 2 puntos para el equipo 1
                            clasificacion.get(equipo2)[0] += 2; // 2 puntos para el equipo 2
                        }
                    }
                }
            }
        }

        // Crear lista de equipos para luego ordenarlos
        List<Equipo> equipos = new ArrayList<>(); // Array para almacenar objetos del Equipo Y De Forma Mas Modificable que un Array normal
        for (Map.Entry<String, int[]> entry : clasificacion.entrySet()) {
            String nombre = entry.getKey(); // Obtener nombre del equipo
            int[] stats = entry.getValue(); // Obtener estadísticas
            LocalDate fechaCreacion = obtenerFechaCreacion(nombre); // Obtener fecha de creación
            equipos.add(new Equipo(nombre, stats[0], stats[1], stats[2], fechaCreacion)); // Crear y agregar equipo a la lista
        }

        // Ordenar equipos por puntos y fecha de creación
        equipos.sort((e1, e2) -> {
            int cmp = Integer.compare(e2.puntos, e1.puntos); // Comparar puntos
            if (cmp == 0) {
                return e1.fechaCreacion.compareTo(e2.fechaCreacion); // Comparar fechas si puntos son iguales
            }
            return cmp; // Retornar comparación de puntos
        });

        // Actualizar la tabla con los datos de los equipos ordenados
        for (int i = 0; i < equipos.size(); i++) {
            Equipo equipo = equipos.get(i); // Obtener equipo
            modelo.setValueAt(equipo.nombre, i, 0); // Actualizar nombre en la tabla
            modelo.setValueAt(equipo.puntos, i, 1); // Actualizar puntos en la tabla
            modelo.setValueAt(equipo.victorias, i, 2); // Actualizar victorias en la tabla
            modelo.setValueAt(equipo.derrotas, i, 3); // Actualizar derrotas en la tabla
        }
    }

    private LocalDate obtenerFechaCreacion(String nombre) {
        // Este método retorna la fecha de creación de un equipo según su nombre
        switch (nombre) { // localdate es un paquete para sacar su fecha directamente para compararlo luego en caso de empate por diferentes razones
            case "Barca": return LocalDate.of(1899, Month.OCTOBER, 29); // Fecha de creación del FC Barcelona
            case "Cisneros": return LocalDate.of(1902, Month.MARCH, 6); // Fecha de creación del Cisneros
            case "Labiana": return LocalDate.of(1903, Month.APRIL, 26); // Fecha de creación del Labiana
            case "Las Abelles": return LocalDate.of(1890, Month.OCTOBER, 25); // Fecha de creación de las Abelles
            case "Unio Esportiva": return LocalDate.of(1923, Month.MARCH, 10); // Fecha de creación de la Unio Esportiva
            case "Pozuelo": return LocalDate.of(1909, Month.SEPTEMBER, 7); // Fecha de creación del Pozuelo
            default: return LocalDate.now(); // Si no encuentra el equipo, devuelve la fecha actual
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { // Método que maneja los eventos de acción de los botones
        if (e.getSource() == btnNext) { // Si se presiona el botón siguiente
            if (validarResultados()) { // Verificar si los resultados son válidos
                int jornada = Integer.parseInt(lblJornada.getText()); // Obtener la jornada actual
                if (jornada < 10) { // Comprobar que la jornada no exceda 10
                    guardarResultadosPorJornada(jornada); // Guardar resultados
                    lblJornada.setText(String.valueOf(jornada + 1)); // Incrementar la jornada
                    actualizarNombresEquipos(jornada + 1); // Actualizar nombres según la nueva jornada
                }
            } else {
                // Mensaje si los resultados no son válidos
                JOptionPane.showMessageDialog(this, "Por favor, introduce todos los resultados válidos antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnBack) { // Si se presiona el botón atrás
            if (validarResultados()) { // Verificar que los resultados sean válidos
                int jornada = Integer.parseInt(lblJornada.getText()); // Obtener jornada actual
                if (jornada > 1) { // Comprobar que la jornada sea mayor a 1
                    guardarResultadosPorJornada(jornada); // Guardar resultados
                    lblJornada.setText(String.valueOf(jornada - 1)); // Disminuir jornada
                    actualizarNombresEquipos(jornada - 1); // Actualizar nombres para la jornada anterior
                }
            } else {
                // Mensaje si los resultados no son válidos
                JOptionPane.showMessageDialog(this, "Por favor, introduce todos los resultados válidos antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnUpdate) { // Si se presiona el botón actualizar
            guardarResultadosPorJornada(Integer.parseInt(lblJornada.getText())); // Guardar resultados actuales
            actualizarClasificacion(); // Actualizar la clasificación en la tabla
        } else if (e.getSource() == iniciarsesion) { // Si se presiona el botón de inicio de sesión
            interfaz vh = new interfaz(); // Crear una nueva instancia de la ventana de inicio
            vh.setVisible(true); // Mostrar ventana de inicio
            this.setVisible(false); // Ocultar ventana actual
            this.dispose(); // Descartar ventana actual
        } else if (e.getSource() == btnReset) { // Si se presiona el botón de reiniciar resultados
            resetearResultados(); // Llamar al método para reiniciar los resultados
        }
    }

    private boolean validarResultados() { // Validar que los resultados sean numéricos y no estén vacíos
        String[] inputs = {puntos1.getText(), puntos2.getText(), puntos3.getText(), puntos4.getText(), puntos5.getText(), puntos6.getText()};
        for (String input : inputs) { // Recorrer todos los campos de texto
            if (input.isEmpty()) {
            	
                return false; // Retornar falso si algún campo está vacío
            }
            try {
                Integer.parseInt(input); // Comprobar si la entrada es un número
            } catch (NumberFormatException e) {
                return false; // Retornar falso si hay error en la conversión
            }
            
            
        }
        return true; // Todos los campos son válidos
    }

    private void resetearResultados() { // Resetear los resultados a cero
        // Reiniciar la matriz de resultados a cero
        for (int i = 0; i < resultados.length; i++) {
            Arrays.fill(resultados[i], 0); // Rellenar cada jornada con ceros
        }

        // Nombres de los equipos en el mismo orden que aparecen en la tabla
        String[] equipos = {"Barca", "Cisneros", "Labiana", "Las Abelles", "Unio Esportiva", "Pozuelo"};
        
        // Establecer puntos, victorias y derrotas a cero en la tabla
        for (String equipo : equipos) {
            int index = getIndexOfTeam(equipo); // Buscar índice del equipo en la tabla
            if (index != -1) {
                modelo.setValueAt(0, index, 1); // Puntos a cero
                modelo.setValueAt(0, index, 2); // Victorias a cero
                modelo.setValueAt(0, index, 3); // Derrotas a cero
            }
        }

        // Mostrar mensaje de confirmación
        JOptionPane.showMessageDialog(this, "Todos los resultados han sido restablecidos a 0.", "Restablecer Resultados", JOptionPane.INFORMATION_MESSAGE);
    }

    private int getIndexOfTeam(String teamName) { // Obtener el índice de un equipo en la tabla
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 0).equals(teamName)) { // Comprobar si el nombre del equipo coincide
                return i; // Retornar índice si se encuentra
            }
        }
        return -1; // Equipo no encontrado
    }

    class IntegerFilter extends DocumentFilter { // Clase para filtrar la entrada de texto
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            // Filtrar la inserción de texto, permitiendo solo números
            if (isNumeric(string)) {
                if (isWithinLimit(fb.getDocument().getText(0, fb.getDocument().getLength()) + string)) {
                    super.insertString(fb, offset, string, attr); // Permitir inserción si es numérico y dentro del límite
                } else {
                    mostrarError("El resultado no puede ser mayor a 100."); // Mostrar error si el número excede 100
                }
            } else {
                mostrarError("Solo se permiten números enteros."); // Mostrar error si no es un número
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
            // Filtrar la sustitución de texto, permitiendo solo números
            if (isNumeric(string)) {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + string + currentText.substring(offset + length);
                if (isWithinLimit(newText)) {
                    super.replace(fb, offset, length, string, attrs); // Permitir sustitución si es numérico y dentro del límite
                } else {
                    mostrarError("El resultado no puede ser mayor a 100."); // Mostrar error si el número excede 100
                }
            } else {
                mostrarError("Solo se permiten números enteros."); // Mostrar error si no es un número
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length); // Permitir eliminación sin filtro
        }

        private boolean isNumeric(String str) { // Comprobar si un string es numérico
            return str.matches("\\d*"); // Coincide solo si es un número
        }

        private void mostrarError(String mensaje) { // Mostrar un mensaje de error
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE); // Ventana emergente con mensaje de error
        }

        private boolean isWithinLimit(String text) { // Verificar si el valor es menor o igual a 100
            try {
                int value = Integer.parseInt(text); // Convierte el texto a número
                return value <= 100; // Comprueba si es menor o igual a 100
            } catch (NumberFormatException e) {
                return false; // Si falla la conversión, no es un número válido
            }
        }
    }
}