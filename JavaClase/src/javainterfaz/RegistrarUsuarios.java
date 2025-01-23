
package javainterfaz;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class RegistrarUsuarios extends Icono implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblLogo, lblTitulo, lblUsuario, lblContraseña;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private JButton btnRegistrate, btnIniciarSesion; 

	public RegistrarUsuarios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registrarse");
        setSize(715, 583);
        setLocationRelativeTo(null);
//        setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarUsuarios.class.getResource("/img/imagenes/rugby1.png")));
		getContentPane().setBackground(new Color(255, 255, 0));
		setBounds(100, 100, 510, 476);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
		

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		 panel.setBackground(new Color(255, 255, 0));
	     contentPane.add(panel, BorderLayout.CENTER);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(RegistrarUsuarios.class.getResource("/img/imagenes/logo.png")));
		
		lblTitulo = new JLabel("Registrarse");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		lblUsuario = new JLabel("Nombre Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textUsuario = new JTextField();
		textUsuario.setColumns(10);

		lblContraseña = new JLabel("Constraseña");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		btnRegistrate = new JButton("Resgistrate");
		btnRegistrate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegistrate.addActionListener(this);
		
		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 20));
        		
		passwordField = new JPasswordField();

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(123)
									.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(155)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textUsuario, 221, 221, Short.MAX_VALUE)
										.addComponent(lblContraseña, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
										.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
										.addComponent(lblUsuario))))
							.addGap(112))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(189)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnRegistrate, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnIniciarSesion, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
							.addGap(145)))
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
							.addComponent(btnRegistrate, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnIniciarSesion, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblLogo)))
					.addContainerGap(227, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	
	// Acción del botón
		btnIniciarSesion.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Abrir el segundo JFrame
        	interfaz interfaz = new interfaz();
        	interfaz.setVisible(true);

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
		                    
		                	interfaz interfaz = new interfaz();
							interfaz.setVisible(true);
		                    dispose();
		                   	} else {
		                      JOptionPane.showMessageDialog(RegistrarUsuarios.this, "El nombre de usuario ya existe. Elija otro.", "Error", JOptionPane.ERROR_MESSAGE);
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(RegistrarUsuarios.this, "La contraseña debe tener al menos 5 caracteres, incluir una letra mayúscula y un número.", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
		                JOptionPane.showMessageDialog(RegistrarUsuarios.this, "El nombre de usuario no puede contener números ni caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(RegistrarUsuarios.this, "Rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
}

private void validarUsuarios(String nombreUsuario, String contraseñaUsuario){

	LeerGuardarUsuarios.guardarUsuarios(nombreUsuario, contraseñaUsuario);
	}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}
