
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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Registro extends Icono implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private JButton btnRegistrate, btnIniciarSesion;


    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 
	public Registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registrarse");
		setBounds(100, 100, 510, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);	
	
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Registrate");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lblUsuario = new JLabel("Nombre Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		
		JLabel lblContraseña = new JLabel("Contraseña");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		passwordField = new JPasswordField();
		
		btnRegistrate = new JButton("Registrate");
		btnRegistrate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Registro.class.getResource("/img/imagenes/logoPequeño.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(62)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addComponent(textUsuario, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContraseña, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnIniciarSesion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRegistrate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(96)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(163, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(147))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addComponent(lblNewLabel)
					.addGap(30)
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
							.addComponent(btnRegistrate)
							.addGap(18)
							.addComponent(btnIniciarSesion))
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
		
		// Acción del botón
				btnIniciarSesion.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            // Abrir el segundo JFrame
		        	Login Login = new Login();
		        	Login.setVisible(true);

		            dispose(); // Cierra la ventana actual
		        }
		    });
				
				btnRegistrate.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        String usuario = textUsuario.getText();
				        String contraseña = String.valueOf(passwordField.getPassword());

				        if (!usuario.isEmpty() && !contraseña.isEmpty()) {
				            if (RegistroValidacion.usuarioValido(usuario)) {
				                if (RegistroValidacion.contraseñaValida(contraseña)) {
				                    if (!LeerGuardarUsuarios.usuarioExiste(usuario)) { // Verificar si el usuario ya existe
				                    // Si todas las validaciones son correctas
				                    validarUsuarios(usuario, contraseña);
				                    writeLog("Usuario " + usuario+" registrado como invitado");
				                    Login Login = new Login();
				                	Login.setVisible(true);
				                    dispose();
				                   	} else {
				                      JOptionPane.showMessageDialog(Registro.this, "El nombre de usuario ya existe. Elija otro.", "Error", JOptionPane.ERROR_MESSAGE);
				                    }
				                } else {
				                    JOptionPane.showMessageDialog(Registro.this, "La contraseña debe tener al menos 5 caracteres, incluir una letra mayúscula y un número.", "Error", JOptionPane.ERROR_MESSAGE);
				                }
				            } else {
				                JOptionPane.showMessageDialog(Registro.this, "El nombre de usuario no puede contener números ni caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
				            }
				        } else {
				            JOptionPane.showMessageDialog(Registro.this, "Rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				        }
				    }
				});
		}

	private void validarUsuarios(String nombreUsuario, String contraseñaUsuario) {
	    int permisoPredeterminado = 0; // Puedes cambiar este valor según tus necesidades
	    LeerGuardarUsuarios.guardarUsuarios(nombreUsuario, contraseñaUsuario, permisoPredeterminado);
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