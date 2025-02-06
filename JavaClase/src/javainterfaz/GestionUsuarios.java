package javainterfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

public class GestionUsuarios extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textUsuario;
	public JPasswordField passwordField;
	public JTable table;
	public JButton btnCrear, btnModificar, btnEliminar, btnGuardar;
	public DefaultTableModel model;
	public JButton btnSalir;
	public Object comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionUsuarios frame = new GestionUsuarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionUsuarios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);	


		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Gestion Usuarios");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lblNewLabel_1 = new JLabel("Nombre Usuario");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		
		String[] opciones = {"Administrador", "Árbitro", "Invitado"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        
		btnCrear = new JButton("Crear\r\n");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane();

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultTableModel(
		    new Object[][] { }, // Elimina la fila vacía
		    new String[] {
		        "Nombre Usuario", "Contraseña", "Tipo"
		    }
		);
		table.setModel(model);
		scrollPane.setViewportView(table);
		cargarUsuarios();
		// Asignar el modelo a la tabla
		
		for (int i = 0; i < table.getColumnCount(); i++) {
		    Class<?> colClass = table.getColumnClass(i);
		    table.setDefaultEditor(colClass, null);
		}


		
		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
		            int selectedRow = table.getSelectedRow();
		            
		            // Obtener valores de la fila seleccionada
		            String usuario = table.getValueAt(selectedRow, 0).toString();  
		            String contraseña = table.getValueAt(selectedRow, 1).toString();  
		            String tipo = table.getValueAt(selectedRow, 2).toString();  
		            
		            // Asignar valores a los JTextField y JPasswordField
		            textUsuario.setText(usuario);
		            passwordField.setText(contraseña);
		            
		            // Seleccionar la opción en el JComboBox
		            comboBox.setSelectedItem(tipo);
		        }
		    }
		});

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(GestionUsuarios.class.getResource("/img/imagenes/logoPequeño.png")));
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
							.addGap(63)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(textUsuario, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(21)
							.addComponent(btnCrear, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(24)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))))
							.addGap(10)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 689, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addComponent(btnCrear, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	

	
		
	btnCrear.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        String usuario = textUsuario.getText();
	        String contraseña = String.valueOf(passwordField.getPassword());
	        String rolSeleccionado = (String) comboBox.getSelectedItem(); // Obtener el rol seleccionado

	        int permiso = 0; // Nivel de permiso por defecto
	        
	     // Asignar nivel de permiso según el rol seleccionado
	        switch (rolSeleccionado) {
	            case "Administrador":
	                permiso = 2;
	                break;
	            case "Árbitro":
	                permiso = 1;
	                break;
	            case "Invitado":
	                permiso = 0;
	                break;
	        }

	        if (!usuario.isEmpty() && !contraseña.isEmpty()) {
	            if (RegistroValidacion.usuarioValido(usuario)) {
	                if (RegistroValidacion.contraseñaValida(contraseña)) {
	                    if (!LeerGuardarUsuarios.usuarioExiste(usuario)) { // Verificar si el usuario ya existe
	                    // Si todas las validaciones son correctas
	                    validarUsuarios(usuario, contraseña, permiso);
	                    writeLog("Usuario "+usuario+" de tipo "+rolSeleccionado+" ha sido creado");
	                      JOptionPane.showMessageDialog(GestionUsuarios.this, "Usuario creado correctamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
	              		cargarUsuarios();

	                   	} else {
	                      JOptionPane.showMessageDialog(GestionUsuarios.this, "El nombre de usuario ya existe. Elija otro.", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(GestionUsuarios.this, "La contraseña debe tener al menos 5 caracteres, incluir una letra mayúscula y un número.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } else {
	                JOptionPane.showMessageDialog(GestionUsuarios.this, "El nombre de usuario no puede solo números ni caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(GestionUsuarios.this, "Rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	});
	
	
	btnEliminar.addActionListener(new ActionListener() {
		@Override
	    public void actionPerformed(ActionEvent e) {
	        // Obtener la fila seleccionada
	        int selectedRow = table.getSelectedRow();
	        String usuario = textUsuario.getText();
	        // Verificar si se ha seleccionado una fila
	        if (selectedRow != -1) {
	            // Eliminar la fila seleccionada
	            model.removeRow(selectedRow);
	            writeLog("Usuario "+ usuario + " ha sido eliminado");
	        } else {
	            // Mostrar un mensaje si no hay fila seleccionada
	            JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
	        }
	    }
	});
	
	btnModificar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        int filaSeleccionada = table.getSelectedRow();
	       
	        if (filaSeleccionada != -1) { // Verifica que haya una fila seleccionada
	            // Obtiene los nuevos valores desde los campos de texto
	            String nuevoUsuario = textUsuario.getText();
	            String nuevaContraseña = new String(passwordField.getPassword()); // Obtiene el texto de PasswordField
	            String nuevoTipoUsuario = comboBox.getSelectedItem().toString();
	            
	            // Actualiza la tabla con los nuevos valores
	            table.setValueAt(nuevoUsuario, filaSeleccionada, 0); // Columna del usuario
	            table.setValueAt(nuevaContraseña, filaSeleccionada, 1); // Columna de la contraseña
	            table.setValueAt(nuevoTipoUsuario, filaSeleccionada, 2); // Columna del tipo de usuario
	            writeLog("Se ha modificado el usuario: " +filaSeleccionada+ " por Nombre de usuario " + nuevoUsuario + 
	                    ", Nueva contraseña: " + nuevaContraseña + ", Nuevo tipo de usuario: " + nuevoTipoUsuario);
	            } else {
	            JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar.");
	        }
	    }
	});


	
	btnGuardar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        // Crear un HashMap para almacenar los usuarios
	        HashMap<String, Object[]> usuarios = new HashMap<>();

	        // Recorrer las filas de la tabla y agregar los datos al HashMap
	        for (int i = 0; i < model.getRowCount(); i++) {
	            String nombreUsuario = (String) model.getValueAt(i, 0); // Obtener el nombre de usuario
	            String contraseña = (String) model.getValueAt(i, 1);    // Obtener la contraseña
	            String tipoUsuario = (String) model.getValueAt(i, 2);    // Obtener el tipo de usuario (Administrador, Árbitro, Invitado)

	            // Verificar si tipoUsuario es null y asignar un valor por defecto si es necesario
	            if (tipoUsuario == null) {
	                tipoUsuario = "Invitado";  // Asignar un valor por defecto si es null
	            }

	            // Convertir el tipo de usuario a nivel de permiso
	            int nivelPermiso = 0;
	            if (tipoUsuario.equals("Administrador")) {
	                nivelPermiso = 2;
	            } else if (tipoUsuario.equals("Árbitro")) {
	                nivelPermiso = 1;
	            } else if (tipoUsuario.equals("Invitado")) {
	                nivelPermiso = 0;
	            }

	            // Almacenar los datos en el HashMap
	            usuarios.put(nombreUsuario, new Object[]{contraseña, nivelPermiso});
	        }

	        // Sobrescribir el archivo usuarios.ser con los datos actuales
	        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("usuarios.ser"))) {
	            out.writeObject(usuarios); // Escribir el HashMap en el archivo
	            JOptionPane.showMessageDialog(null, "Datos guardados correctamente.");
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error al guardar los datos.");
	        }
	    }
	});
	
	btnSalir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Abrir el segundo JFrame
        	TemporadasFrame TemporadasFrame = new TemporadasFrame();
        	TemporadasFrame.setVisible(true);
            dispose(); // Cierra la ventana actual
        }
    });	
		

	
}
	// Modificar validarUsuarios para recibir el permiso
	private void validarUsuarios(String nombreUsuario, String contraseñaUsuario, int permiso) {
	    LeerGuardarUsuarios.guardarUsuarios(nombreUsuario, contraseñaUsuario, permiso);
	}

	public void cargarUsuarios() {
		// Cargar usuarios desde el archivo
        HashMap<String, Object[]> usuarios = Usuario.cargarUsuarios("usuarios.ser");

        // Recorrer el HashMap y agregar los usuarios a la tabla
        for (Map.Entry<String, Object[]> entry : usuarios.entrySet()) {
            String nombreUsuario = entry.getKey(); // Nombre de usuario (clave)
            Object[] datosUsuario = entry.getValue(); // Datos del usuario (valor)

            String contraseña = (String) datosUsuario[0];  // Contraseña (posición 0)
            int nivelPermiso = (int) datosUsuario[1];     // Nivel de permiso (posición 1)
            
         // Convertir el nivel de permiso a su representación textual
            String tipoUsuario = "";
            switch (nivelPermiso) {
                case 2:
                    tipoUsuario = "Administrador";
                    break;
                case 1:
                    tipoUsuario = "Árbitro";
                    break;
                case 0:
                    tipoUsuario = "Invitado";
                    break;
                default:
                    tipoUsuario = "Desconocido"; // Por si hay un valor inesperado
            }

            // Verificar si el nombre de usuario ya está en la tabla
            boolean usuarioExistente = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                // Comprobar que el valor no sea null antes de comparar
                if (model.getValueAt(i, 0) != null && model.getValueAt(i, 0).equals(nombreUsuario)) {
                    usuarioExistente = true;
                    break; // Salir del bucle si el usuario ya está en la tabla
                }
            }

            // Si el usuario no está en la tabla, agregarlo
            if (!usuarioExistente) {
                model.addRow(new Object[]{nombreUsuario, contraseña, tipoUsuario});
            }
        }
	}
	
	private void writeLog(String message) {
	    try (PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true))) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String timestamp = dateFormat.format(new Date());
	        writer.println(timestamp + " - " + message);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
