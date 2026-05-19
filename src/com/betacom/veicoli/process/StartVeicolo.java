package com.betacom.veicoli.process;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betacom.veicoli.MainVeicoli;
import com.betacom.veicoli.services.ListImpl;
import com.betacom.veicoli.services.implmentations.BiciImpl;
import com.betacom.veicoli.services.implmentations.MacchinaImpl;
import com.betacom.veicoli.services.implmentations.MotoImpl;
import com.betacom.veicoli.services.interfaces.VeicoliAbstract;
import com.betacom.veicoli.utilities.Utils;

public class StartVeicolo {
	public final static int OPERATION=0;
	public final static int TIPO_VEICOLO=1;
	public final static int PARAMETERS=2;
	
	public void execute(List<String> params) throws Exception{
		Map<String,VeicoliAbstract> serv = new HashMap<String, VeicoliAbstract>();
		serv.put("macchina", new MacchinaImpl());
		serv.put("moto", new MotoImpl());
		serv.put("bici", new BiciImpl());
		serv.put("list", new ListImpl());
		
		Utils.writeFile(MainVeicoli.PATH_OUPUT, "************", false);
		
		params.forEach(para -> {
			String[] inp = para.split(";");
			String operation = inp[OPERATION].trim();
			try {
				if ("list".equalsIgnoreCase(operation)) {
					VeicoliAbstract ex = serv.get("list");
					ex.controlExecute("list",para);
				} else {
					String tipo = inp[TIPO_VEICOLO].trim();										
					if (serv.containsKey(tipo)) {					
						VeicoliAbstract ex = serv.get(tipo);
						ex.controlExecute(operation,inp[PARAMETERS]);
						
					} else 
						System.out.println("Tipo veicolo non previsto:" + tipo);
					
				}
				
				
			} catch (Exception e) {
				System.out.println("error:" + e.getMessage());
			}
			
		});
			
			
		
	}
}
