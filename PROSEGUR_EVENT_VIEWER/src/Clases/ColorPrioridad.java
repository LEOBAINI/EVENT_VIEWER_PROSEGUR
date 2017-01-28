package Clases;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import MetodosSql.MetodosSql;
import Pantallas.Pantalla;

public  class ColorPrioridad {
	final static Logger logger = Logger.getLogger(Pantalla.class);
	private static HashMap<Integer, String> coloresPrioridad = new HashMap<Integer, String>();
	
	private static void cargarColores(){
		int codigoColor=0;
		String color=null;
		String SentenciaSql="select priority,color from alarm_priority order by color";
		MetodosSql metodos=new MetodosSql();
		ArrayList<ArrayList<String>> nrosPrioridad = metodos.consultar(SentenciaSql);	
		for(int i=0;i<nrosPrioridad.size();i++){
			codigoColor=Integer.parseInt(nrosPrioridad.get(i).get(0));
			color=nrosPrioridad.get(i).get(1);
			if(color==null){
				color="black";
				logger.info("Cargado color "+color+" en codigo: "+codigoColor+ " porque color era null." );
			}
			coloresPrioridad.put(codigoColor, color);
			logger.info("Cargado codigo: "+codigoColor+ " -> color: "+color );
		}
		logger.info("Colores cargados desde .\"ColorPrioridad.cargarColores().\"");
	}
	public static String darColor(int nroPrioridad){
		if(coloresPrioridad.isEmpty()){
			cargarColores();
		}
		return coloresPrioridad.get(nroPrioridad);
		
	}
	

}
