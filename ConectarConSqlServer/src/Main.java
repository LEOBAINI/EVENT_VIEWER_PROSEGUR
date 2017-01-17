import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import MetodosSql.MetodosSql;
import Pantallas.Login;
import Pantallas.Pantalla;

 
public class Main {
 
	public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
	
		Login login=new Login();
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Login.setDefaultLookAndFeelDecorated(true);
		login.setLocationRelativeTo(null);
		login.setVisible(true);
	
	
		}		
		
}

