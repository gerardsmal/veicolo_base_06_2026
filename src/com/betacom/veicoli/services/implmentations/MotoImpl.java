package com.betacom.veicoli.services.implmentations;

import java.util.Map;

import com.betacom.veicoli.models.Moto;
import com.betacom.veicoli.services.interfaces.VeicoliAbstract;
import com.betacom.veicoli.singleton.ListManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MotoImpl extends VeicoliAbstract{

	@Override
	public void add(String oper, String params) throws Exception{
		Map<String, String> p = decodeParamers(params);
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
		log.info("Moto inserita");

	}

}
