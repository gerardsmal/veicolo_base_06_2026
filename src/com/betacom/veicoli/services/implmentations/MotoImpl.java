package com.betacom.veicoli.services.implmentations;

import java.util.Map;

import com.betacom.veicoli.models.Moto;
import com.betacom.veicoli.services.interfaces.VeicoliAbstract;
import com.betacom.veicoli.singleton.ListManager;

public class MotoImpl extends VeicoliAbstract{

	@Override
	public void controlExecute(String oper, String params) throws Exception {
		System.out.println("Execute MOTO");
		
		Map<String, String> p = decodeParamers(params);
		if (oper.equalsIgnoreCase("add")) 
			addVeicolo(p);
		else if (oper.equalsIgnoreCase("delete")) 
			deleteVeicolo(p);
		
	}
	
	private void addVeicolo(Map<String, String> p) throws Exception {
		Moto moto = new Moto();
		moto.setTipoVeicolo("moto");
		
		moto = (Moto) controlExecute(moto, p);
		
		if (ListManager.getInstance().isTargaExist(p.get("targa").toUpperCase()))
			throw new Exception("Targa già inserita");
		moto.setTarga(p.get("targa").toUpperCase());
		
		try {
			moto.setCc(Integer.parseInt(p.get("cc")));			
		} catch (Exception e) {
			throw new Exception("cilindrato invalido");
		}
		
		moto = (Moto) ListManager.getInstance().insertVeicolo(moto);
		System.out.println("Moto inserita");

	}

}
