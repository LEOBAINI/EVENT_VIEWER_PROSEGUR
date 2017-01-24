package Clases;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import MetodosSql.MetodosSql;


public class ParametrosInicio {

	private static String ipDestino;
	private static String baseDatos;
	private static String tiempoRefrescoTabla;
	final static Logger logger = Logger.getLogger(ParametrosInicio.class); 
	
	
	
	
	public ParametrosInicio() {
		super();
		
	}

	static void setearParametros(String archivo){
/*
ipDEstino:10.54.54.41
nombreBaseDatos:monitordb
tiempoConsultaSegundos:10
*/		ArrayList<String>lineas;
		String lineaLeida=null;

		MetodosSql metodos=new MetodosSql();
		lineas=metodos.LeeArchivoParametrosArray(archivo);
		
		for(int i=0;i<lineas.size();i++){
		if(lineas.get(i).contains("ipDEstino")){
			lineaLeida=lineas.get(i).replaceAll("ipDEstino:", "");
			setIpDestino(lineaLeida);
		}else if(lineas.get(i).contains("nombreBaseDatos")){
			lineaLeida=lineas.get(i).replaceAll("nombreBaseDatos:", "");	
			setBaseDatos(lineaLeida);			
		}else if(lineas.get(i).contains("tiempoConsultaSegundos")){
			lineaLeida=lineas.get(i).replaceAll("tiempoConsultaSegundos:","");	
			setTiempoRefrescoTabla(lineaLeida);
		}		
		}
		logger.info("Intentara conexión con "+ParametrosInicio.getIpDestino());
		logger.info("La base de datos es "+ParametrosInicio.getBaseDatos());
	}
	


	public static String getIpDestino() {
		return ipDestino;
	}




	public static void setIpDestino(String ipDestino) {
		ParametrosInicio.ipDestino = ipDestino;
	}




	public static String getBaseDatos() {
		return baseDatos;
	}




	public static void setBaseDatos(String baseDatos) {
		ParametrosInicio.baseDatos = baseDatos;
	}




	public static String getTiempoRefrescoTabla() {
		return tiempoRefrescoTabla;
	}




	public static void setTiempoRefrescoTabla(String lineaLeida) {
		ParametrosInicio.tiempoRefrescoTabla = lineaLeida;
	}
	
	
	
}
