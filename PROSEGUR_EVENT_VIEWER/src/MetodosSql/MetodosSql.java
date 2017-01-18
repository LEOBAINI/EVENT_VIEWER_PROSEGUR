package MetodosSql;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.awt.Color;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
 
import java.util.Date;
 
import java.util.Locale;
 
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

import Pantallas.Pantalla;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
 
public class MetodosSql extends Conexion {
     
    public MetodosSql() {
    }
     
    public void exportarExcel(String rutaOutPut,JTable tabla,String nombreHoja) throws IOException, RowsExceededException, WriteException{
        WritableWorkbook wworkbook;
        wworkbook = Workbook.createWorkbook(new File(rutaOutPut));
        WritableSheet wsheet = wworkbook.createSheet(nombreHoja, 0);
        //COLUMNA FILA DESDE 0    
       
       
       
        int filas=tabla.getRowCount();
		int columnas=tabla.getColumnCount();
		
		 
		for(int fila=0;fila<=filas;fila++){
			for(int columna=0;columna<columnas;columna++){
				if(fila==0){
					wsheet.addCell(new Label( columna,fila,tabla.getModel().getColumnName(columna)));	
					
				}else{
					wsheet.addCell(new Label( columna,fila, tabla.getValueAt(fila-1, columna).toString()));	
					System.out.println(tabla.getValueAt(fila-1, columna));
				}
				
			
				
				
				
			}
		}     
		wworkbook.write();
		wworkbook.close();
        
       
       

        
  	  }
        
     
         
    public static String dameFechaDeHoy(){
         SimpleDateFormat formateador = new SimpleDateFormat("yyyy'-'MM'-'dd", new Locale("es_ES"));
         Date fechaDate = new Date();
          String fecha=formateador.format(fechaDate);
           
     
    return fecha;
    }
     
    public String dameFechaDeHoyConFormatoX(String formatoFechaseparadoXguionyGuionEntreComillas){//el MM va con mayuscula
         SimpleDateFormat formateador = new SimpleDateFormat(formatoFechaseparadoXguionyGuionEntreComillas, new Locale("es_ES"));
         Date fechaDate = new Date();
         String fecha=formateador.format(fechaDate);
          
     
    return fecha;
    }
    
 
    public int insertarOmodif(String sentenciaSql) {
        System.out.println(sentenciaSql);
        //System.out.println("Luego borrar este syso, solo es para mostrar los datos enviados a la base, (metodosSql linea 34 y 35)");
        int status=0;
        Conexion con = new Conexion();
 
        try {
            con.conectar();
            con.statemente.executeUpdate(sentenciaSql);
 
            con.desconectar();
            status=1;
             
 
        } catch (SQLException e) {
            System.out.println("Error en insertarOmodificar");
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
            con.desconectar();
            status=-1;
        }
        return status;
 
    }
 
    public ArrayList<ArrayList<String>> consultar(String SentenciaSql) {
        ResultSet res =null;
        ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();//creo una matriz
        String aux=null;
         
        Conexion con = new Conexion();
         
         
        try {
            con.conectar();
            con.resulsete=con.statemente.executeQuery(SentenciaSql);
            res = con.resulsete;
            ResultSetMetaData rmd = res.getMetaData(); //guardo los datos referentes al resultset
             
              
                while ( res.next()){
                        ArrayList<String> columnas = new ArrayList<String>();
                         for (int i=1; i<=rmd.getColumnCount(); i++) {
                             aux=res.getString(i);            
                                  
                             columnas.add(aux);
                         }
                         matriz.add(columnas);
                }
            con.desconectar();
 
             
 
        } catch (Exception e) {
            System.out.println("Error en metodosSql.consultar"+e.getMessage());
            System.out.println(e.getLocalizedMessage());
             
        }
 
        return matriz;
         
 
    }
     
         
         
     
    public ArrayList<String>consultarUnaColumna(String SentenciaSql) {
        ResultSet res =null;
        ArrayList<String> arreglo = new ArrayList<String>();//creo una matriz
         
         
        Conexion con = new Conexion();
        System.out.println(SentenciaSql);
         
        try {
            con.conectar();
            con.resulsete=con.statemente.executeQuery(SentenciaSql);
            res = con.resulsete;
             
             
              
                while ( res.next()){
                     
                    arreglo.add(res.getString(1));
                }
            con.desconectar();
 
             
 
        } catch (Exception e) {
            System.out.println("Error en metodosSql.consultarUnaColumna"+e.getMessage());
            con.desconectar();
             
        }
 
        return arreglo;
         
 
    }
   
         
      
    public static JTable llenarJtable(String sentencia ){
        Conexion con = new Conexion();
        DefaultTableModel modelo=new DefaultTableModel();//voy a modelar mi jtable
        JTable tablaDatos=new JTable(modelo);
        if(con.conectar()==true){
        //TableColumnModel modeloColumnas = null;
        java.sql.ResultSetMetaData metadatos;
         
       
         
        
         
        try {
         
        con.resulsete = con.statemente.executeQuery(sentencia);
        metadatos=con.resulsete.getMetaData();//extraigo datos sobre el resulset
         
        int cantColumnas=metadatos.getColumnCount();// pido cant columnas
        
        //modeloColumnas.setSelectionModel((ListSelectionModel) tablaDatos);
         
         
         
        for(int i=1;i<=cantColumnas;i++){
        modelo.addColumn(metadatos.getColumnName(i).toUpperCase());
         
         
        }
        //avanzo por el resulset para mostrar resultado de consultas
          while(con.resulsete.next()){
            // Bucle para cada resultado en la consulta
              
                 // Se crea un array que será una de las filas de la tabla. 
                 Object [] fila = new Object[cantColumnas]; // Hay columnas en la tabla
 
                 // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
                 for (int i=0;i<cantColumnas;i++)
                    fila[i] = con.resulsete.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
 
                 // Se añade al modelo la fila completa.
                 modelo.addRow(fila);
                 //cell.setBackground(Color.RED);
              }
          
 
         
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
           // e.printStackTrace();
        }
        con.desconectar();
        return tablaDatos;
        }else{
        return tablaDatos;	
        }
        
         
 
    }
    public int teDoyNombreProductoDameNumeroID(String nombreProducto){
        int resultado=0;
        MetodosSql metodos=new MetodosSql();
        resultado=Integer.parseInt(metodos.consultarUnaColumna("SELECT idProducto  FROM imprenta.producto where nombreproducto= '"+nombreProducto+"';").get(0));
        return resultado;
    }
 
    public String LeeArchivoParametros(String archivo) throws FileNotFoundException, IOException {
    	  String resultado = null;
    	  String cadena;
	      FileReader f = new FileReader(archivo);
	      BufferedReader b = new BufferedReader(f);
	      while((cadena = b.readLine())!=null) {
	         if(resultado==null){
	    	  resultado=cadena;
	         }else{
	        	 resultado=resultado+" "+cadena;
	         }
	        // System.out.println(cadena);
	      }
	      b.close();
		return resultado;
	}
 
   
 
     
   
     
 
}