package Clases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;

import org.apache.log4j.Logger;


import Pantallas.Login;

 
public class Main {
	 final static Logger logger = Logger.getLogger(Main.class);
 
	public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		ParametrosInicio.setearParametros("/ParametrosConexion/Parametros.txt");
		logger.info("Iniciando programa");
		Login login=new Login();
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setLocationRelativeTo(null);
		login.setVisible(true);	
		}
		
	
}

