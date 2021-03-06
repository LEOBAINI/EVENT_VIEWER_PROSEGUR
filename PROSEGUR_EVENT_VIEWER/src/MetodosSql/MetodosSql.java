package MetodosSql;
 
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
 
public class MetodosSql extends Conexion {
	 final static Logger logger = Logger.getLogger(MetodosSql.class);
     
    public MetodosSql() {
    }
     
    public void exportarExcel(String rutaOutPut,JTable tabla,String nombreHoja) throws IOException, RowsExceededException, WriteException{
        WritableWorkbook wworkbook;
        wworkbook = Workbook.createWorkbook(new File(rutaOutPut));
        WritableSheet wsheet = wworkbook.createSheet(nombreHoja, 0);
        //COLUMNA FILA DESDE 0    
       try{
       
       
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
		 logger.info("Archivo "+rutaOutPut+" exportado ok");
        
       }catch(Exception e){
    	   logger.error("Error : " +e.getMessage());
       }
       

        
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
            con.desconectar();
            status=-1;
            logger.error("Error : " +e.getMessage());
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
            logger.error("Error : " +e.getMessage());
             
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
            logger.error("Error : " +e.getMessage());
             
        }
 
        return arreglo;
         
 
    }
   
         
      
    public static JTable llenarJtable(String sentencia, JLabel labelInfo, String hora, String minutos, String segundo ) throws InterruptedException{
        Conexion con = new Conexion();
        DefaultTableModel modelo=new DefaultTableModel();//voy a modelar mi jtable
        JTable tablaDatos=new JTable(modelo);
        if(con.conectar()==true){
        //TableColumnModel modeloColumnas = null;
        java.sql.ResultSetMetaData metadatos;
         
       
         
        
        
         
        try {
        labelInfo.setText("Ejecutando Consulta a la Base");
        con.resulsete = con.statemente.executeQuery(sentencia);
        Thread.sleep(1500);
        
        metadatos=con.resulsete.getMetaData();//extraigo datos sobre el resulset
        labelInfo.setText("Obteniendo datos");
         
        int cantColumnas=metadatos.getColumnCount();// pido cant columnas
        
        //modeloColumnas.setSelectionModel((ListSelectionModel) tablaDatos);
         
         
         
        for(int i=1;i<=cantColumnas;i++){
        modelo.addColumn(metadatos.getColumnName(i).toUpperCase());
         
         
        }
        //avanzo por el resulset para mostrar resultado de consultas
        labelInfo.setText("Cargando datos...");
        Thread.sleep(1500);
          while(con.resulsete.next()){
            // Bucle para cada resultado en la consulta
              
                 // Se crea un array que ser� una de las filas de la tabla. 
                 Object [] fila = new Object[cantColumnas]; // Hay columnas en la tabla
 
                 // Se rellena cada posici�n del array con una de las columnas de la tabla en base de datos.
                 for (int i=0;i<cantColumnas;i++)
                    fila[i] = con.resulsete.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
 
                 // Se a�ade al modelo la fila completa.
                 modelo.addRow(fila);
                
                 //cell.setBackground(Color.RED);
              }
          labelInfo.setText("Entregando Datos.");
          Thread.sleep(1500);
          labelInfo.setText("�ltima conexi�n "+hora + ":" + minutos + ":" + segundo);
          Thread.sleep(1500);
         
        } 
        catch (SQLException e) {
        
            logger.error("Error : " +e.getMessage());
        }catch (Exception e1){
        	logger.error("Error : " +e1.getMessage());
        }
        con.desconectar();
        return tablaDatos;
        }else{
        return tablaDatos;	
        }
        
         
 
    }
   
   
    public String LeeArchivoParametros(String archivo)  {
    	  logger.info("Intentando leer archivo "+archivo);
    	  String resultado=null;    
    	  String strLinea=null;
    	  InputStream fstream = this.getClass().getResourceAsStream(archivo);
          // Creamos el objeto de entrada
          DataInputStream entrada = new DataInputStream(fstream);
          // Creamos el Buffer de Lectura
          BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada)); 
          
          // Leer el archivo linea por linea
          try {
			while ((strLinea = buffer.readLine()) != null)   {
			      // Imprimimos la l�nea por pantalla
				  if(resultado==null){
			    	  resultado=strLinea;
			         }else{
			        	 resultado=resultado+" "+strLinea;
			         }
			  
			   
			     // System.out.println(strLinea);
				 
			  }
			 logger.info(archivo+" le�do correctamente.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			 logger.error("Error : " +e.getMessage());
		}
          // Cerramos el archivo
          try {
			entrada.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			 logger.error("Error : " +e.getMessage());
		}    			
          return resultado;
   	}   	
    
    public ArrayList<String> LeeArchivoParametrosArray(String archivo)  {
    	ArrayList<String>listaParam=new ArrayList<String>();
  	  logger.info("Intentando leer archivo "+archivo);
  	  String resultado=null;    
  	  String strLinea=null;
  	  InputStream fstream = this.getClass().getResourceAsStream(archivo);
        // Creamos el objeto de entrada
        DataInputStream entrada = new DataInputStream(fstream);
        // Creamos el Buffer de Lectura
        BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada)); 
        
        // Leer el archivo linea por linea
        try {
			while ((strLinea = buffer.readLine()) != null)   {
			      // Imprimimos la l�nea por pantalla
				  if(resultado==null){
			    	  resultado=strLinea;
			         }else{
			        	 resultado=resultado+" "+strLinea;
			         }
			  
			   
				  listaParam.add(strLinea);
				 
			  }
			 logger.info(archivo+" le�do correctamente.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			 logger.error("Error : " +e.getMessage());
		}
        // Cerramos el archivo
        try {
			entrada.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			 logger.error("Error : " +e.getMessage());
		}    			
        return listaParam;
 	}   	
	       
	      
		
	
    public void abrirarchivo(String archivo){

        try {
        	
        	   logger.info("Intentando abrir : " +archivo);
               File objetofile = new File (archivo);
               Desktop.getDesktop().open(objetofile);
               logger.info(archivo+ " abierto ok");  
               

        }catch (IOException ex) {
        //System.out.println(ex.getMessage());
        logger.error("Error : " +ex.getMessage());
        }
        
    }


 
   
 
     
   
     
 
}