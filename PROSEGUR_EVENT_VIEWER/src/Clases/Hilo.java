package Clases;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import MetodosSql.MetodosSql;

public class Hilo extends Thread{
		 public static boolean estaVivo=false;
	     String fecha1;
	     String fecha2;
	     JTable table;
	     JLabel labelInfo;
	
	    private static String QuerieConFiltroFecha = null;
		String mensaje;
	
	    public Hilo(String fecha1, String fecha2, JTable table, JLabel labelInfo)
	
	    {
	
	        this.fecha1=fecha1;
	        this.fecha2=fecha2;
	        this.table=table;
	        this.labelInfo=labelInfo;
	
	    }
	
	     
	
	    public void run()
	
	    {
	    	estaVivo=true;
	    	inicializarTablaConFecha(fecha1,fecha2,table,labelInfo);
	    	estaVivo=false;
	    }
	
	    public void setearTamanioColumnas(){
	    	table.getColumnModel().getColumn(0).setMinWidth(150);
	     	table.getColumnModel().getColumn(0).setMaxWidth(150);
	     	table.getColumnModel().getColumn(3).setMinWidth(100);
	     	table.getColumnModel().getColumn(3).setMaxWidth(110);
	     	table.getColumnModel().getColumn(4).setMinWidth(200);
	     	table.getColumnModel().getColumn(5).setMaxWidth(100);
	     	table.getColumnModel().getColumn(7).setMaxWidth(70);
	    }
	
	    private void inicializarTablaConFecha(String fecha1,
				String fecha2,JTable jTable,JLabel jLabelInfo)  {
		//	Object logger;
			try{
				MetodosSql m=new MetodosSql();
				String query = null;
				if(fecha1==null || fecha2==null){
					JOptionPane.showMessageDialog(null,"Verifique los campos de fecha");
				//	logger.info("Usuario no completa campos fecha para el filtro");
				}else{
				// Para que cargue los datos sólo una vez.
					if(QuerieConFiltroFecha==null){
						QuerieConFiltroFecha=m.LeeArchivoParametros("/Queries/QuerieConFiltroFecha.txt");
						query = QuerieConFiltroFecha;
					}else{
						query = QuerieConFiltroFecha;
					}
				
				
				query=query.replaceAll("@fechadesde", "'"+fecha1+"'");
				query=query.replaceAll("@fechahasta", "'"+fecha2+"'");
				System.out.println(query);
				 GregorianCalendar calendario = new GregorianCalendar();
        		 String hora =    String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
        		 String minutos = String.valueOf( calendario.get(Calendar.MINUTE));
        		 String segundo = String.valueOf(calendario.get(Calendar.SECOND));
        		 if(hora.length()==1)
        			 hora="0"+hora;
        		 if(minutos.length()==1)
        			 minutos="0"+minutos;
        		 if(segundo.length()==1)
        			 segundo="0"+segundo;
				jTable.setModel(MetodosSql.llenarJtable(query, jLabelInfo, hora, minutos, segundo).getModel());	
				
				setearTamanioColumnas();
				jTable.repaint();
				jLabelInfo.setText("Pulsó Botón filtro, para ver automático\n oprima Refrescar");
			//	logger.info("Refrescando tabla manual por oprimir botón filtro");
				}
				
			}catch(Exception e){
				//	logger.error(e.getMessage());
				}
			 				
	
	 
	
	}
}

