
package javainterfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Login extends Icono implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private JButton btnAcceder, btnRegistrate;


    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registrarse");
		setBounds(100, 100, 510, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);	
	
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Iniciar Sesion");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		
		JLabel lblContraseña = new JLabel("Contraseña");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		passwordField = new JPasswordField();
		
		btnAcceder = new JButton("Acceder\r\n");
		btnAcceder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnRegistrate = new JButton("Registrate\r\n");
		btnRegistrate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/img/imagenes/logoPequeño.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(62)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(textUsuario, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblContraseña, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAcceder, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRegistrate, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
							.addGap(99)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(153)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(textUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblContraseña, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(btnAcceder)
							.addGap(18)
							.addComponent(btnRegistrate))
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
		
		// Acción del botón
				btnRegistrate.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            // Abrir el segundo JFrame
		        	Registro Registro = new Registro();
		        	Registro.setVisible(true);

		            dispose(); // Cierra la ventana actual
		        }
		    });	
				
				btnAcceder.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        String usuario = textUsuario.getText();
				        String contraseña = String.valueOf(passwordField.getPassword());
				        if (usuario.isEmpty() || contraseña.isEmpty()) {
			                JOptionPane.showMessageDialog(Login.this, "Rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				        } else {
					        validarUsuario(usuario, contraseña);
					        writeLog("Inicio exitoso: " + usuario);
				        }
				    }
				});
			}
	

	@Override
	public void actionPerformed(ActionEvent e) {
        
	}
	
	private void validarUsuario(String username, String password) {
	    // Definir credenciales del superusuario
	    String superadmin = "admin";
	    String superadmincontra = "admin123";

	    // Verificar si el usuario es el superadministrador
	    if (username.equals(superadmin) && password.equals(superadmincontra)) {
	        JOptionPane.showMessageDialog(this, "Bienvenido, Superadministrador.");
	        abrirVentanaPorPermiso(3); // Permiso 3 para el superadmin
	        return;
	    }

	    // Si no es el superusuario, proceder con la validación desde el archivo
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuarios.ser"))) {
	        Object obj = ois.readObject();
	        if (obj instanceof HashMap) {
	            @SuppressWarnings("unchecked")
	            HashMap<String, Object[]> usuarios = (HashMap<String, Object[]>) obj;

	            // Validar si el usuario existe en el HashMap
	            if (usuarios.containsKey(username)) {
	                Object[] datos = usuarios.get(username);

	                // Verificar que el array contiene datos válidos
	                if (datos != null && datos.length >= 2 && datos[0] instanceof String && datos[0].equals(password)) {
	                    int permiso = (datos[1] instanceof Integer) ? (int) datos[1] : -1;

	                    // Verificar el nivel de permiso y abrir la ventana correspondiente
	                    abrirVentanaPorPermiso(permiso);
	                } else {
	                    JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } else {
	                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(this, "No se pudo leer el archivo usuarios.ser.\n" +
	                "Por favor, verifique que el archivo existe.", "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (ClassNotFoundException e) {
	        JOptionPane.showMessageDialog(this, "El archivo usuarios.ser tiene un formato inválido.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	// Método para abrir la ventana según el nivel de permisos
	private void abrirVentanaPorPermiso(int permiso) {
	    TemporadasFrame vh = new TemporadasFrame();

	    // Dependiendo del nivel de permiso, ocultar o mostrar botones
	    if (permiso == 3) { // Superadministrador
	        // Mostrar todos los botones
	    } else if (permiso == 2) { // Administrador
	        // Mostrar todos los botones
	    } else if (permiso == 1) { // Árbitro
	        // Ocultar algunos botones
	        vh.btnConfirmar.setVisible(false);
	    } else if (permiso == 0) { // Usuario básico
	        // Ocultar todos los botones
	        vh.btnCrearTemporada.setVisible(false);
	        vh.btnConfirmar.setVisible(false);
	    } else { // Nivel de permiso no válido
	        JOptionPane.showMessageDialog(this, "Nivel de permiso no válido.", "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    vh.setVisible(true);
	    this.dispose();
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
}