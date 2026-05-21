package com.betacom.veicoli.services.implmentations;

import java.util.List;
import java.util.Map;

import com.betacom.veicoli.MainVeicoli;
import com.betacom.veicoli.models.Veicoli;
import com.betacom.veicoli.services.interfaces.VeicoliAbstract;
import com.betacom.veicoli.singleton.ListManager;
import com.betacom.veicoli.utilities.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListImpl extends VeicoliAbstract{
	private Gson gson = null;
	
	@Override
	public void list(String ope,String params) throws Exception {
		
		log.info("List process :"+ params);
		String[] pp = params.split(";");
		
		Map<String, String> p = decodeParamers(pp[1]);
		gson = new GsonBuilder().setPrettyPrinting().create();
		List<Veicoli>   lV = ListManager.getInstance().getListV();
		Utils.writeFile(MainVeicoli.PATH_OUPUT, params, true);
		
		lV.stream()
	    	.filter(v -> p.get("tipoVeicolo") == null 
	            || v.getTipoVeicolo().equalsIgnoreCase(p.get("tipoVeicolo")))
	    	.filter(v -> p.get("cat") == null 
	            || v.getCategoria().equalsIgnoreCase(p.get("cat")))
	    	.forEach(v -> buildJson(v));
		
		
//		for (Veicoli v:lV) {
//			if (p.get("tipoVeicolo") != null) {
//				if (v.getTipoVeicolo().equalsIgnoreCase(p.get("tipoVeicolo"))) Utils.writeFile(MainVeicoli.PATH_OUPUT, v.toString(), true);
//				if (v.getCategoria().equalsIgnoreCase(p.get("cat"))) Utils.writeFile(MainVeicoli.PATH_OUPUT, v.toString(), true);
//
//			} else {
//				Utils.writeFile(MainVeicoli.PATH_OUPUT, v.toString(), true);				
//			}
//				
//		}
	}
	@Override
	public void delete(String ope,String params) throws Exception {
		log.warn("questo metodo non é valido per la list");
	}
	private void buildJson(Veicoli ve) {
		
		String jsonString = gson.toJson(ve);
		Utils.writeFile(MainVeicoli.PATH_OUPUT, jsonString, true);
	}
}
