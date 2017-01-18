package Inicio;

import java.util.ArrayList;
import java.util.HashMap;

import MetodosSql.MetodosSql;

public  class ColorPrioridad {
	
	private static HashMap<Integer, String> coloresPrioridad = new HashMap<Integer, String>();
	
	private static void cargarColores(){
		String SentenciaSql="select priority,color from alarm_priority order by color";
		MetodosSql metodos=new MetodosSql();
		ArrayList<ArrayList<String>> nrosPrioridad = metodos.consultar(SentenciaSql);	
		for(int i=0;i<nrosPrioridad.size();i++){
			coloresPrioridad.put(Integer.parseInt(nrosPrioridad.get(i).get(0)), nrosPrioridad.get(i).get(1));
			
		}
	}
	public static String darColor(int nroPrioridad){
		if(coloresPrioridad.isEmpty()){
			cargarColores();
		}
		return coloresPrioridad.get(nroPrioridad);
		
	}
	

}
