package com.betacom.veicoli.services.implmentations;

import java.util.Map;

import com.betacom.veicoli.models.Macchina;
import com.betacom.veicoli.services.interfaces.VeicoliAbstract;
import com.betacom.veicoli.singleton.ListManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MacchinaImpl extends VeicoliAbstract{
	@Override
	public void add(String oper, String params) throws Exception{
		log.info("Execute Macchina " + oper);	
		
		Map<String, String> p = decodeParamers(params);

		Macchina mac = new Macchina();
		mac.setTipoVeicolo("macchina");
		
		mac = (Macchina) controlExecute(mac, p);
		
		try {
			mac.setNumeroPorte(Integer.parseInt(p.get("porte")));			
		} catch (Exception e) {
			throw new Exception("numero porte invalido");
		}
		
		if (ListManager.getInstance().isTargaExist(p.get("targa").toUpperCase()))
			throw new Exception("Targa già inserita");
		mac.setTarga(p.get("targa").toUpperCase());
		
		try {
			mac.setCc(Integer.parseInt(p.get("cc")));			
		} catch (Exception e) {
			throw new Exception("cilindrato invalido");
		}
		
		mac = (Macchina) ListManager.getInstance().insertVeicolo(mac);
		log.info("Macchina inserita");
	}

}
