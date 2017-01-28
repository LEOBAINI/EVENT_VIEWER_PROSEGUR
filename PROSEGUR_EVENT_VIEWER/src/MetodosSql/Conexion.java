package MetodosSql;
 


import java.sql.*;

import org.apache.log4j.Logger;

import Clases.ParametrosInicio;
 
 
 
public class Conexion {
     
 
        private  Connection c;
        protected  Statement statemente;
        protected  ResultSet resulsete;
        public static String user=null;
        public static String pass=null;
        private static String cadena=null;
        private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        private static String server=null;
        private static String database=null;
        final static Logger logger = Logger.getLogger(Conexion.class);
    
        
        
        
    
        
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
        		  server=ParametrosInicio.getIpDestino();
        	      database=ParametrosInicio.getBaseDatos();
            	
            	cadena="jdbc:sqlserver://"+server+";user="+user+";password="+pass+";database="+database+"";
            	
                Class.forName(driver);
                c=DriverManager.getConnection(cadena);
                 statemente=c.createStatement();
                 logger.info("Conectado ok! a "+server);
                 
                conecto=true;
            }catch(ClassNotFoundException e1){
            	logger.error("Error en los drivers : " +e1.getMessage());
             System.out.println("Error en los drivers");
             conecto=false;
            }
            catch(SQLException e2){
             
                logger.error("Error en la conexion : " +e2.getMessage());
               
                conecto=false;
 
            }catch(Exception e3){
            	logger.error("Error al conectar : " +e3.getMessage());
            	
                 conecto=false;
            	
            }
 return conecto;
    }
        
        public  void desconectar(){
            //estado=new JTextField();
             
                try {
                    if (c != null){
                        c.close();
                        logger.info("Liberando conexión al servidor.");
                         
                         
                         
                    //  System.out.println("Conexión liberada OK");
                    }
                    else{
                        System.out.println("Ya estaba desconectado");
                         
                    }
                     
                    //statemente.close();
                     
                     
                     
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                	logger.error("Error al desconectar : " +e.getMessage());
                    System.out.println("Desconectado incorrecto");
                    e.printStackTrace();
                }
                 
             
        }
}