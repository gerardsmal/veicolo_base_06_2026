package com.betacom.veicoli.services.implmentations;

import java.util.Map;

import com.betacom.veicoli.models.Bici;
import com.betacom.veicoli.services.interfaces.VeicoliAbstract;
import com.betacom.veicoli.singleton.ListManager;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class BiciImpl extends VeicoliAbstract{
	
	@Override
	public void add(String oper, String params) throws Exception{
		Map<String, String> p = decodeParamers(params);
		Bici bici = new Bici();
		bici.setTipoVeicolo("bici");
		
		bici = (Bici) controlExecute(bici, p);

		try {
			bici.setNumeroMarce(Integer.parseInt(p.get("marce")));			
		} catch (Exception e) {
			throw new Exception("numero marce invalido");
		}

		if (!ListManager.getInstance().isValidValue("sospenzione", p.get("sospenzione")))
			throw new Exception("Tipo sospenzione invalida");
		bici.setTipoSospenzione(p.get("sospenzione"));
		
		bici.setPiegevole(p.get("piegevole").trim().equalsIgnoreCase("si") ? true : false);


		bici = (Bici) ListManager.getInstance().insertVeicolo(bici);
		
		log.info(".... Bici inserita");
		
	}
}
