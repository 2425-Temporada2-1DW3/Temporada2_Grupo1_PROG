
package javainterfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Login extends Icono implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLogo, lblTitulo, lblUsuario, lblContraseña;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private JButton btnAcceder, btnRegistrarse;


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
		setBounds(100, 100, 1063, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);	
	
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Login.class.getResource("/img/imagenes/logo.png")));
		
		lblTitulo = new JLabel("Iniciar Sesion");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		
		lblContraseña = new JLabel("Constraseña");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		btnAcceder = new JButton("Acceder");
		btnAcceder.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnAcceder.addActionListener(this);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnRegistrarse.addActionListener(this);
		
		passwordField = new JPasswordField();

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(155)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(textUsuario, 221, 221, Short.MAX_VALUE)
									.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblContraseña, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
									.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
								.addGap(112))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(189)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnRegistrarse, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnAcceder, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
								.addGap(145)))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
							.addGap(49)))
					.addComponent(lblLogo)
					.addGap(39))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(56)
							.addComponent(lblTitulo)
							.addGap(53)
							.addComponent(lblUsuario)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(lblContraseña)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(46)
							.addComponent(btnAcceder, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnRegistrarse, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblLogo)))
					.addContainerGap(227, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	

	// Acción del botón
		btnRegistrarse.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Abrir el segundo JFrame
        	RegistrarUsuarios RegistrarUsuarios = new RegistrarUsuarios();
        	RegistrarUsuarios.setVisible(true);

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
		        }
		    }
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        
	}
	
	 private void validarUsuario(String username, String password) {
		    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuarios.ser"))) {
		        Object obj = ois.readObject();
		        if (obj instanceof HashMap) {
		            @SuppressWarnings("unchecked")
					HashMap<String, String> usuarios = (HashMap<String, String>) obj;

		            // Validar usuario y contraseña
		            if (usuarios.containsKey(username) && usuarios.get(username).equals(password)) {
		            	pruebaAcceso pruebaAcceso = new pruebaAcceso();
		                pruebaAcceso.setVisible(true);
		                this.dispose();		            } else {
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
}
