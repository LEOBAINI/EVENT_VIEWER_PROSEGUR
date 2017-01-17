package MetodosSql;
 
import java.sql.*;

import javax.swing.JOptionPane;
 
 
 
public class Conexion {
     
 
        private  Connection c;
        protected  Statement statemente;
        protected  ResultSet resulsete;
        public static String user=null;
        public static String pass=null;
        private static String cadena=null;
        private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        private static String server="10.54.54.41";
        private static String database="monitordb";
        
        
        
    
        
        public static String getUser() {
			return user;
		}



		public void setUser(String user) {
			Conexion.user = user;
		}



		public static String getPass() {
			return pass;
		}



		public void setPass(String pass) {
			Conexion.pass = pass;
		}



		public Conexion(){
             
        }
         
         
        
        public boolean conectar(){
        	boolean conecto;
        	try{
            	
            	cadena="jdbc:sqlserver://"+server+";user="+user+";password="+pass+";database="+database+"";
            	
                Class.forName(driver);
                c=DriverManager.getConnection(cadena);
                 statemente=c.createStatement();
                System.out.println("Conectado OK");
                 
                conecto=true;
            }catch(ClassNotFoundException e1){
             System.out.println("Error en los drivers");
             conecto=false;
            }
            catch(SQLException e2){
                System.out.println("Error en la conexion");
                JOptionPane.showMessageDialog(null, e2.getMessage());
                conecto=false;
 
            }catch(Exception e3){
            	 JOptionPane.showMessageDialog(null, e3.getMessage());
                 conecto=false;
            	
            }
 return conecto;
    }
        
        public  void desconectar(){
            //estado=new JTextField();
             
                try {
                    if (c != null){
                        c.close();
                         
                         
                         
                    //  System.out.println("Conexión liberada OK");
                    }
                    else{
                        System.out.println("Ya estaba desconectado");
                         
                    }
                     
                    //statemente.close();
                     
                     
                     
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Desconectado incorrecto");
                    e.printStackTrace();
                }
                 
             
        }
}