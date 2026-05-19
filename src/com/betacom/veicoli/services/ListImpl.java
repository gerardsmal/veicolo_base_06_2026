package com.betacom.veicoli.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.betacom.veicoli.MainVeicoli;
import com.betacom.veicoli.models.Veicoli;
import com.betacom.veicoli.services.interfaces.VeicoliAbstract;
import com.betacom.veicoli.singleton.ListManager;
import com.betacom.veicoli.utilities.Utils;

public class ListImpl extends VeicoliAbstract{
	
	
	@Override
	public void controlExecute(String ope,String params) throws Exception {
		
		System.out.println("List process :"+ params);
		String[] pp = params.split(";");
		
		Map<String, String> p = decodeParamers(pp[1]);
		
		List<Veicoli>   lV = ListManager.getInstance().getListV();
		Utils.writeFile(MainVeicoli.PATH_OUPUT, params, true);
		
		lV.stream()
	    	.filter(v -> p.get("tipoVeicolo") == null 
	            || v.getTipoVeicolo().equalsIgnoreCase(p.get("tipoVeicolo")))
	    	.filter(v -> p.get("cat") == null 
	            || v.getCategoria().equalsIgnoreCase(p.get("cat")))
	    	.forEach(v -> Utils.writeFile(MainVeicoli.PATH_OUPUT, v.toString(), true));
		
		
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
}
