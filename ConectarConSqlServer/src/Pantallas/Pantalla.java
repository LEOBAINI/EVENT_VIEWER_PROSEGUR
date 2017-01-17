package Pantallas;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;

import MetodosSql.MetodosSql;
import javax.swing.JLabel;
import java.awt.TextField;
import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Toolkit;
import javax.swing.WindowConstants;
import javax.swing.BorderFactory;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;

import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

public class Pantalla extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JButton jButton = null;
	private JLabel jLabelDesde = null;
	private JLabel jLabelHasta = null;
	private JButton jButtonFiltrarPorFecha = null;
	private Timer timer;  //  @jve:decl-index=0:
	private JLabel jLabelImagen = null;
	private JTextField jTextFieldSegundos = null;
	private int segundos=10;
	private JDateChooser dateChooserDesde;
	private JDateChooser dateChooserHasta;
	private JPanel jPanelRefrescar = null;
	private JPanel jPanelFecha = null;
	private JPanel jPanelControles = null;
	public JDateChooser getDateChooser() {
		return dateChooserDesde;
	}

	public Pantalla() throws HeadlessException, FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		super();
		initialize();
	}

	public Pantalla(GraphicsConfiguration arg0) throws FileNotFoundException, IOException {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public Pantalla(String arg0) throws HeadlessException, FileNotFoundException, IOException {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public Pantalla(String arg0, GraphicsConfiguration arg1) throws FileNotFoundException, IOException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private void initialize() throws FileNotFoundException, IOException {
		this.setSize(1373, 691);
		this.setMinimumSize(new Dimension(27, 39));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/ProsegurAlarmas.png")));
		
		this.setBackground(new Color(255, 255, 102));
		this.setContentPane(getJContentPane());
		this.setTitle("Prosegur Alarmas");
		this.setVisible(true);
		
		
		inicializarTablaYtimer(segundos);
		
	
	}
private void validarSegundosTimer(int segundos) {
	try{
		segundos=Integer.parseInt(jTextFieldSegundos.getText());
	}catch(Exception e){
		JOptionPane.showMessageDialog(null, e.getMessage());
	}
	if (segundos<10){
		segundos=10;
		jTextFieldSegundos.setText(String.valueOf(segundos));
}
		
	}

private void inicializarTablaYtimer(int segundos) throws FileNotFoundException, IOException{
	MetodosSql m=new MetodosSql();

final String query = m.LeeArchivoParametros(".\\src\\Queries\\QueriePantallaPpal.txt");
try{timer.cancel();}catch(Exception e){}	

	
		 TimerTask timerTask = new TimerTask() 
	     { 
	         public void run()  
	         { 
	        	 
	        	 try{
	        		 	jTable.setModel(MetodosSql.llenarJtable(query).getModel());
						jTable.repaint();
						}catch(Exception e){
							JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
						}
	     		  		
	             System.out.println("TESTING TIMER");
	         } 
	     }; 
	     

	     
	     timer = new Timer(); 
	   
	     timer.scheduleAtFixedRate(timerTask, 0, segundos*1000);	
	    
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelImagen = new JLabel();
			jLabelImagen.setText("");
			jLabelImagen.setBounds(new Rectangle(519, 16, 356, 123));
			URL urlDeLaImagen = Pantalla.class.getClassLoader().getResource("Imagenes/ProsegurAlarmas.png");
			ImageIcon fot = new ImageIcon(urlDeLaImagen);
			
			Icon icono = new ImageIcon(fot.getImage().getScaledInstance(jLabelImagen.getWidth(), jLabelImagen.getHeight(), Image.SCALE_DEFAULT));
			jLabelImagen.setIcon(icono);
			jLabelHasta = new JLabel();
			jLabelHasta.setText("Hasta ");
			jLabelHasta.setBounds(new Rectangle(7, 45, 148, 20));
			jLabelDesde = new JLabel();
			jLabelDesde.setText("Desde ");
			jLabelDesde.setBounds(new Rectangle(7, 22, 148, 20));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
			jContentPane.setBackground(new Color(247, 215, 6));
			jContentPane.add(getJScrollPane(), null);
			

			dateChooserDesde = new JDateChooser();
			dateChooserDesde.setBounds(new Rectangle(162, 23, 121, 20));
		    this.getContentPane().add(dateChooserDesde);
			
			dateChooserHasta = new JDateChooser();
			dateChooserHasta.setBounds(new Rectangle(162, 46, 121, 20));
		    this.getContentPane().add(dateChooserHasta);
			jContentPane.add(getJPanelControles(), null);
			
				
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(8, 9, 1337, 474));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTable.setRowSelectionAllowed(false);
			jTable.setShowGrid(true);
			jTable.setToolTipText("Eventos de Alarmas");
			
			jTable.setBackground(new Color(255, 255, 204));
			
		}
		return jTable;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Refrescar Pantalla");
			jButton.setBounds(new Rectangle(147, 89, 140, 26));
			jButton.setToolTipText("Enciende la consulta periódica refrescando la pantalla");
			jButton.setBorder(new LineBorder(Color.YELLOW));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				
					try {
						validarSegundosTimer(segundos);
						inicializarTablaYtimer(segundos);
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButtonFiltrarPorFecha	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonFiltrarPorFecha() {
		if (jButtonFiltrarPorFecha == null) {
			jButtonFiltrarPorFecha = new JButton();
			jButtonFiltrarPorFecha.setToolTipText("Filtra los eventos en el rango de fechas indicado y apaga la consulta periódica");
			jButtonFiltrarPorFecha.setBounds(new Rectangle(118, 94, 166, 22));
			jButtonFiltrarPorFecha.setText("Filtrar Por Fecha");
			jButtonFiltrarPorFecha.setBorder(new LineBorder(Color.YELLOW));
			jButtonFiltrarPorFecha.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{ 
					timer.cancel();
					}catch(Exception e1){}
					 System.out.println("Tarea Cancelada"); 
					 System.out.println();
					 String fecha1=obtenerFechaChooser(dateChooserDesde);
					 String fecha2=obtenerFechaChooser(dateChooserHasta);
					
					 
					 inicializarTablaConFecha(fecha1,fecha2);
				}

				
				
				
				private void inicializarTablaConFecha(String fecha1,
						String fecha2)  {
						MetodosSql m=new MetodosSql();
						String query = null;
						if(fecha1==null || fecha2==null){
							JOptionPane.showMessageDialog(null,"Verifique los campos de fecha");
						}else{
						try {
							query = m.LeeArchivoParametros(".\\src\\Queries\\QuerieConFiltroFecha.txt");
							query=query.replaceAll("@fechadesde", "'"+fecha1+"'");
							query=query.replaceAll("@fechahasta", "'"+fecha2+"'");
							System.out.println(query);
							jTable.setModel(MetodosSql.llenarJtable(query).getModel());							
							jTable.repaint();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,e1.getMessage());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,e1.getMessage());
						}
						
					 				
						}		
									
				}
			});
		}
		return jButtonFiltrarPorFecha;
	}

	/**
	 * This method initializes jTextFieldSegundos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldSegundos() {
		if (jTextFieldSegundos == null) {
			jTextFieldSegundos = new JTextField();
			jTextFieldSegundos.setText("10");
			jTextFieldSegundos.setBounds(new Rectangle(250, 15, 34, 20));
		}
		return jTextFieldSegundos;
	}

	
	
	/**
	 * This method initializes jButtonCalendar1	
	 * @return 
	 * 	
	 * @return javax.swing.JButton	
	 */
	private String obtenerFechaChooser(JDateChooser dateChooser){
		
		String resultado=null;
		try{
		String anio=String.valueOf(dateChooser.getCalendar().get(Calendar.YEAR));
		String mes=String.valueOf(dateChooser.getCalendar().get(Calendar.MONTH)+1);
		String dia=String.valueOf(dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH));
		if(mes.length()==1)
			mes="0"+mes;
		if(dia.length()==1)
			dia="0"+dia;
		resultado=anio+"-"+mes+"-"+dia;
		System.out.println(resultado);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return resultado;
	
	}

	/**
	 * This method initializes jPanelRefrescar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelRefrescar() {
		if (jPanelRefrescar == null) {
			jPanelRefrescar = new JPanel();
			jPanelRefrescar.setLayout(null);
			jPanelRefrescar.setBackground(new Color(247, 215, 6));
			jPanelRefrescar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.yellow), "Seteo de tiempo en segundos", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelRefrescar.setBounds(new Rectangle(928, 22, 294, 124));
			jPanelRefrescar.add(getJButton(), null);
			jPanelRefrescar.add(getJTextFieldSegundos(), null);
		}
		return jPanelRefrescar;
	}

	/**
	 * This method initializes jPanelFecha	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelFecha() {
		if (jPanelFecha == null) {
			jPanelFecha = new JPanel();
			jPanelFecha.setLayout(null);
			jPanelFecha.setBackground(new Color(247, 215, 6));
			jPanelFecha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.yellow), "Consulta con filtro de fecha", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelFecha.setBounds(new Rectangle(156, 22, 294, 124));
			jPanelFecha.add(getJButtonFiltrarPorFecha(), null);
			jPanelFecha.add(jLabelDesde, null);
			jPanelFecha.add(jLabelHasta, null);
			jPanelFecha.add(getDateChooser(), null);
			jPanelFecha.add(dateChooserHasta, null);
		}
		return jPanelFecha;
	}

	/**
	 * This method initializes jPanelControles	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelControles() {
		if (jPanelControles == null) {
			jPanelControles = new JPanel();
			jPanelControles.setLayout(null);
			jPanelControles.setBounds(new Rectangle(4, 486, 1330, 159));
			jPanelControles.setBackground(new Color(247, 215, 6));
			jPanelControles.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.yellow, 5), "PROSEGUR EVENT VIEWER", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelControles.add(getJPanelFecha(), null);
			jPanelControles.add(getJPanelRefrescar(), null);
			jPanelControles.add(jLabelImagen, null);
		}
		return jPanelControles;
	}

}  //  @jve:decl-index=0:visual-constraint="6,13"
