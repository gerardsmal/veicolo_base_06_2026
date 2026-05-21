package com.betacom.veicoli.process;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betacom.veicoli.MainVeicoli;
import com.betacom.veicoli.services.interfaces.VeicoliAbstract;
import com.betacom.veicoli.utilities.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartVeicolo {
	public final static int OPERATION=0;
	public final static int TIPO_VEICOLO=1;
	public final static int PARAMETERS=2;
	private Map<String,Object> cache = new HashMap<String, Object>();  // cache per evitare multiple new instance
	private final static String PATH_SERVICES = "com.betacom.veicoli.services.implmentations";
	
	public void execute(List<String> params) throws Exception{
		
		Utils.writeFile(MainVeicoli.PATH_OUPUT, "************", false);
		
		params.forEach(para -> {
			String[] inp = para.split(";");
			String operation = inp[OPERATION].trim();
			try {
				String tipo = ("list".equalsIgnoreCase(operation)) ? "list" : inp[TIPO_VEICOLO].trim();	
				VeicoliAbstract ex = (VeicoliAbstract) loadService(tipo);
			
				String p = ("list".equalsIgnoreCase(operation)) ? para : inp[PARAMETERS];
				executeOperation(operation,ex,p);

			} catch (Exception e) {
				log.error("error: {}" , e.getMessage());
			}
			
		});
	}
	/*
	 * Dynamic load e new instance process class
	 */
	private Object loadService(String name)  throws Exception{
		try {
			if (cache.containsKey(name)) return cache.get(name);
			
			Class<?> cl = Class.forName(PATH_SERVICES + "." + Utils.buildClassName(name));
			Object obj = cl.getDeclaredConstructor().newInstance();
			cache.put(name, obj);
			return obj;
			
		} catch (ClassNotFoundException e) {
			throw new Exception("tipo veicolo non supportato " + name);
		}
		
	}
	
	/*
	 * Dynamic execute specific method for any vehicle
	 * use reflection API
	 */
	private void executeOperation(String operation, VeicoliAbstract myService, String parameters) throws Exception{
		try {
			String methodName =  operation.toLowerCase().trim();
			Method metodo = myService.getClass().getMethod(methodName, String.class, String.class);
			metodo.invoke(myService,operation,parameters);		
			
		} catch (SecurityException e) {
			throw new Exception("error di sicurezza.." + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new Exception("Errore IllegalAccess" + e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new Exception("Errore IllegalArgument" + e.getMessage());
		} catch (InvocationTargetException e) {            // retrieve error from invoke method (AcademyException)
			throw new Exception(e.getCause().getMessage());
		} catch (NoSuchMethodException e) {
			throw new Exception("funzione non trovata:" + operation);
		}
	}
}
