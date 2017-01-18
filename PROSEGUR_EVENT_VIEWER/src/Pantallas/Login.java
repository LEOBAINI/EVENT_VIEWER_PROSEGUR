package Pantallas;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextPane;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import MetodosSql.Conexion;
import MetodosSql.MetodosSql;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabelUsuario = null;
	private JLabel jLabelPass = null;
	private JLabel jLabelLogo = null;
	private JLabel jLabelEventViewer = null;
	private JButton jButtonAceptar = null;
	private JTextField jTextFieldUsuario = null;
	private JPasswordField jPasswordField = null;
	boolean conecto;

	public boolean isConecto() {
		return conecto;
	}

	public void setConecto(boolean conecto) {
		this.conecto = conecto;
	}

	/**
	 * This is the default constructor
	 */
	public Login() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(637, 234);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/ProsegurAlarmas.png")));
		this.setContentPane(getJContentPane());
		this.setTitle("Login");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelEventViewer = new JLabel();
			jLabelEventViewer.setBounds(new Rectangle(447, 167, 162, 24));
			jLabelEventViewer.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabelEventViewer.setText("ALARM EVENT VIEWER");
			jLabelLogo = new JLabel();
			jLabelLogo.setBounds(new Rectangle(332, 7, 281, 166));
			jLabelLogo.setIcon(new ImageIcon(getClass().getResource("/Imagenes/ProsegurAlarmas.png")));
			jLabelLogo.setText("");
			jLabelPass = new JLabel();
			jLabelPass.setBounds(new Rectangle(9, 42, 94, 22));
			jLabelPass.setText("Contraseña :");
			jLabelUsuario = new JLabel();
			jLabelUsuario.setBounds(new Rectangle(9, 5, 64, 27));
			jLabelUsuario.setText("Usuario :");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(new Color(255, 204, 0));
			jContentPane.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.yellow));
			jContentPane.add(jLabelUsuario, null);
			jContentPane.add(jLabelPass, null);
			jContentPane.add(jLabelLogo, null);
			jContentPane.add(jLabelEventViewer, null);
			jContentPane.add(getJButtonAceptar(), null);
			jContentPane.add(getJTextFieldUsuario(), null);
			jContentPane.add(getJPasswordField(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButtonAceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonAceptar() {
		if (jButtonAceptar == null) {
			jButtonAceptar = new JButton();
			jButtonAceptar.setBounds(new Rectangle(126, 123, 157, 28));
			jButtonAceptar.setBackground(new Color(247, 244, 16));
			jButtonAceptar.setText("Aceptar");
			jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(jTextFieldUsuario.getText());
					System.out.println(jPasswordField.getPassword());
					try{
					MetodosSql metodos=new MetodosSql();
					Conexion.user=(jTextFieldUsuario.getText());
					Conexion.pass=(String.copyValueOf(jPasswordField.getPassword()));
					System.out.println("El pass es : "+String.copyValueOf(jPasswordField.getPassword()));
					setConecto( metodos.conectar());
					if(conecto==true){
						
						metodos.desconectar();
						Pantalla pantalla=new Pantalla();
						pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						//Pantalla.setDefaultLookAndFeelDecorated(true);
						//pantalla.setLocationRelativeTo(null);
						pantalla.setVisible(true);
						pantalla.setExtendedState(JFrame.MAXIMIZED_BOTH);
						/*
						Pantalla pant=new Pantalla();
						
						pant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						Pantalla.setDefaultLookAndFeelDecorated(true);
						//pant.setAlwaysOnTop(true);
						pant.setExtendedState(JFrame.MAXIMIZED_BOTH);
						pant.setLocationRelativeTo(null);					
						pant.setVisible(true);		
						*/
					}else{
						JOptionPane.showMessageDialog(null,"Fallo la conexión, verifique red \n usuario y contraseña");
					}
						
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null,e1.getMessage());
					}
				}
			});
		}
		return jButtonAceptar;
	}

	/**
	 * This method initializes jTextFieldUsuario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldUsuario() {
		if (jTextFieldUsuario == null) {
			jTextFieldUsuario = new JTextField();
			jTextFieldUsuario.setBounds(new Rectangle(124, 7, 157, 24));
			jTextFieldUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					     jButtonAceptar.doClick();
					   }
				}
			});
			
			
			
			
		}
		return jTextFieldUsuario;
	}

	/**
	 * This method initializes jPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setBounds(new Rectangle(124, 41, 157, 24));
			jPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					     jButtonAceptar.doClick();
					   }
				}
			});
		}
		return jPasswordField;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
