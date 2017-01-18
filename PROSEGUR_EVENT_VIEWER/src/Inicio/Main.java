package Inicio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;

import Pantallas.Login;

 
public class Main {
 
	public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
	
		Login login=new Login();
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		
		
	
	
		}		
		
}

