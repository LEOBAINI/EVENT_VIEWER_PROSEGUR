package Pantallas;
 
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
 
public class PruebaJtable extends JFrame {
 
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JScrollPane jScrollPane = null;
    private JTable jTable = null;
    private DefaultTableModel modelo;
    private JButton jButton = null;
    private JEditorPane jEditorPane = null;
    private JEditorPane jEditorPane1 = null;
    private JLabel jLabel = null;
    private JLabel jLabel1 = null;
 
    /**
     * This is the default constructor
     */
    public PruebaJtable() {
        super();
        initialize();
    }
 
    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(530, 279);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(getJContentPane());
        this.setTitle("JFrame");
    }
 
    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jLabel1 = new JLabel();
            jLabel1.setBounds(new Rectangle(188, 140, 92, 18));
            jLabel1.setText("Apellido");
            jLabel = new JLabel();
            jLabel.setBounds(new Rectangle(187, 107, 93, 17));
            jLabel.setText("Nombre");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJScrollPane(), null);
            jContentPane.add(getJButton(), null);
            jContentPane.add(getJEditorPane(), null);
            jContentPane.add(getJEditorPane1(), null);
            jContentPane.add(jLabel, null);
            jContentPane.add(jLabel1, null);
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
            jScrollPane.setBounds(new Rectangle(53, 24, 404, 63));
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
        modelo=new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
         
        if (jTable == null) {
            jTable = new JTable(modelo);
            jTable.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    System.out.println(jTable.getSelectedRow()+jTable.getSelectedColumn());
                    System.out.println(jTable.getValueAt(jTable.getSelectedRow(), jTable.getSelectedColumn()));
                }
            });
             
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
            jButton.setBounds(new Rectangle(346, 134, 136, 42));
            jButton.setText("Agregar");
            jButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                 
                jEditorPane1.getText();
                Object[]fila=new Object[2];
                fila[0]=jEditorPane.getText();
                fila[1]=jEditorPane1.getText();
                modelo.addRow(fila);
                }
            });
        }
        return jButton;
    }
 
    /**
     * This method initializes jEditorPane  
     *  
     * @return javax.swing.JEditorPane  
     */
    private JEditorPane getJEditorPane() {
        if (jEditorPane == null) {
            jEditorPane = new JEditorPane();
            jEditorPane.setBounds(new Rectangle(53, 105, 119, 24));
        }
        return jEditorPane;
    }
 
    /**
     * This method initializes jEditorPane1 
     *  
     * @return javax.swing.JEditorPane  
     */
    private JEditorPane getJEditorPane1() {
        if (jEditorPane1 == null) {
            jEditorPane1 = new JEditorPane();
            jEditorPane1.setBounds(new Rectangle(55, 138, 118, 24));
        }
        return jEditorPane1;
    }
 
}  //  @jve:decl-index=0:visual-constraint="10,10"