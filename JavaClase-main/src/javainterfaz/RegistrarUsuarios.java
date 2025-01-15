package javainterfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class RegistrarUsuarios extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	
	private JPanel contentPane;
	private JLabel lblLogo, lblTitulo, lblUsuario, lblEmail, lblContraseña;
	private JTextField textUsuario, textEmail;
	private JPasswordField passwordField;
	private JButton btnRegistrate, btnIniciarSesion; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarUsuarios frame = new RegistrarUsuarios();
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
	public RegistrarUsuarios() {
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
		lblLogo.setIcon(new ImageIcon(RegistrarUsuarios.class.getResource("/img/imagenes/logo.png")));
		
		lblTitulo = new JLabel("Registrarse");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		
		lblContraseña = new JLabel("Constraseña");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		btnRegistrate = new JButton("Resgistrate");
		btnRegistrate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
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
										.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
										.addComponent(textEmail, 221, 221, Short.MAX_VALUE)
										.addComponent(lblContraseña, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
										.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))))
							.addGap(112))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(189)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnIniciarSesion, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRegistrate, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
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
							.addGap(27)
							.addComponent(lblEmail)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(lblContraseña)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
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
            IniciarSesion IniciarSesion = new IniciarSesion();
            IniciarSesion.setVisible(true);

            // Cerrar o esconder la ventana actual (opcional)
            dispose(); // Cierra la ventana actual
            // setVisible(false); // Alternativa: Esconde la ventana actual
        }
    });
		
		
}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
