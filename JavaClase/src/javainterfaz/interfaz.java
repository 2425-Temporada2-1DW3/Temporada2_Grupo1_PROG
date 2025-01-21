
package javainterfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToggleButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;

public class interfaz extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPasswordField passwordField;
	private JToggleButton tglbtnNewToggleButton;
	private JFormattedTextField formattedTextField;
	private JLabel lblimagen;
	private JLabel lblContrasena;
	private JLabel lblUsuario;
	private JLabel lblLogin;
	private GroupLayout gl_panel;
	private JLabel errorLabel;
	private int permiso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfaz frame = new interfaz();
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
 //Interfazzzzzzzzzzz
	public interfaz() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(715, 583);
		setLocationRelativeTo(null);
		setResizable(false);

		setIconImage(Toolkit.getDefaultToolkit().getImage(interfaz.class.getResource("/img/imagenes/rugby1.png")));
		setTitle("Login");
		setBackground(new Color(255, 255, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 476);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 0));
		contentPane.add(panel, BorderLayout.EAST);

		formattedTextField = new JFormattedTextField();

		formattedTextField.addFocusListener(new FocusAdapter() {
			@Override
			// Cuando recibe el foco
			public void focusGained(FocusEvent e) {
				formattedTextField.select(0, formattedTextField.getText().length());
			}

			@Override
			// Cuando pierde el foco
			public void focusLost(FocusEvent e) {
				formattedTextField.select(0, 0);

			}
		});

		formattedTextField.setColumns(8);
		formattedTextField.addActionListener(this);// Con este this coge todo el código de abajo

		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				passwordField.setSelectionStart(0);
				String contrasena = new String(passwordField.getPassword());
				passwordField.setSelectionStart(contrasena.length());
			}

			@Override
			public void focusLost(FocusEvent e) {

				passwordField.select(0, 0);
			}
		});

		passwordField.addActionListener(this);
		passwordField.setColumns(8);

		tglbtnNewToggleButton = new JToggleButton("Acceder");
		tglbtnNewToggleButton.setForeground(new Color(0, 0, 0));
		tglbtnNewToggleButton.setFont(new Font("Verdana", Font.BOLD, 15));
		tglbtnNewToggleButton.setBackground(new Color(51, 153, 204));
		tglbtnNewToggleButton.addActionListener(this);

		lblimagen = new JLabel("");
		lblimagen.setIcon(new ImageIcon(interfaz.class.getResource("/img/imagenes/rugby1.png")));

		lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBackground(Color.WHITE);
		lblContrasena.setFont(new Font("Verdana", Font.BOLD, 12));

		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Verdana", Font.BOLD, 12));
		lblUsuario.setBackground(new Color(255, 255, 255));

		lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Verdana", Font.BOLD, 25));
		
		errorLabel = new JLabel("New label");
		errorLabel.setForeground(new Color(255, 0, 0));
		errorLabel.setVisible(false);
		
		JToggleButton tglbtnRegistrarse = new JToggleButton("Registrarse");
		tglbtnRegistrarse.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Abrir la ventana de registrar usuario
		        RegistrarUsuarios registrarUsuarios = new RegistrarUsuarios();
		        registrarUsuarios.setVisible(true);

		        // Cerrar la ventana actual de login
		        setVisible(false); // Esto hace que la ventana de login desaparezca
		    }
		});
		tglbtnRegistrarse.setForeground(Color.BLACK);
		tglbtnRegistrarse.setFont(new Font("Verdana", Font.BOLD, 15));
		tglbtnRegistrarse.setBackground(new Color(51, 153, 204));

		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblLogin, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
					.addGap(71))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(40)
					.addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(39, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(50)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblContrasena, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(63)
							.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(tglbtnRegistrarse)
								.addComponent(tglbtnNewToggleButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addComponent(lblimagen, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGap(59))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(79)
					.addComponent(lblLogin)
					.addGap(18)
					.addComponent(errorLabel)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblimagen)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblUsuario)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(22)
							.addComponent(lblContrasena)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnNewToggleButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(tglbtnRegistrarse, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(91, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    Object o = e.getSource();

	    if (o == tglbtnNewToggleButton) {
	        // Cuando pulsa aceptar

	        // Usuarios hardcodeados para admin, arbitro y usuario
	        String admincorrecto = "admin";
	        String admincontrasena = "admin123";

	        String arbitrocorrecto = "arbitro";
	        String arbitrocontrasena = "arbitro123";

	        String usuariocorrecto = "usuario";
	        String usuariocontrasena = "usuario123";

	        // Datos introducidos por el usuario
	        String usuario = formattedTextField.getText().replaceAll("\\s+", "");
	        String contrasena = new String(passwordField.getPassword()).replaceAll("\\s+", "");

	        // Verifica si los campos no están vacíos
	        if (usuario.equals("") || contrasena.equals("")) {
	            errorLabel.setText("Campos en blanco o espacios introducidos");
	            errorLabel.setVisible(true);
	        } else {
	            // Si el usuario es uno de los hardcodeados, validar con sus contraseñas
	            if (usuario.equals(admincorrecto) && contrasena.equals(admincontrasena)) {
	                permiso = 2;
	                TemporadasFrame vh = new TemporadasFrame();
	                vh.setVisible(true);
	                this.setVisible(false);
	                this.dispose();
	            } else if (usuario.equals(arbitrocorrecto) && contrasena.equals(arbitrocontrasena)) {
	                permiso = 1;
	                mainPage vh = new mainPage(permiso);
	                vh.setVisible(true);
	                this.setVisible(false);
	                this.dispose();
	            } else if (usuario.equals(usuariocorrecto) && contrasena.equals(usuariocontrasena)) {
	                permiso = 0;
	                mainPage vh = new mainPage(permiso);
	                vh.setVisible(true);
	                this.setVisible(false);
	                this.dispose();
	            }
	            // Si no es uno de los usuarios hardcodeados, verificar en el archivo de usuarios registrados
	            else if (LeerGuardarUsuarios.usuarioExiste(usuario)) {
	                // Verificar contraseña de usuarios registrados
	                String contrasenaGuardada = LeerGuardarUsuarios.obtenerContraseña(usuario);
	                if (contrasena.equals(contrasenaGuardada)) {
	                    // Asignar permisos si es un usuario registrado
	                    permiso = 0; // Aquí puedes asignar permisos según el tipo de usuario registrado
	                    mainPage vh = new mainPage(permiso);
	                    vh.setVisible(true);
	                    this.setVisible(false);
	                    this.dispose();
	                } else {
	                    errorLabel.setText("Contraseña incorrecta");
	                    errorLabel.setVisible(true);
	                }
	            } else {
	                errorLabel.setText("Usuario no existente");
	                errorLabel.setVisible(true);
	            }
	        }
	    }
	}

}
