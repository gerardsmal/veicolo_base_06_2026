package com.betacom.veicoli;

import java.util.ArrayList;
import java.util.List;

import com.betacom.veicoli.process.StartVeicolo;
import com.betacom.veicoli.singleton.ListManager;

public class MainVeicoli {
	public static void main(String[] args) {
		List<String> params = new ArrayList<String>();
		params.add("add;macchina;ruote=4,alim=benzina,cat=strada,colore=bianco,marca=fiat,anno=2025,modello=500,porte=4,targa=el234gx,cc=1200");
		params.add("add;macchina;ruote=4,alim=benzina,cat=strada,colore=bianco,marca=fiat,anno=2026,modello=panda,porte=4,targa=fl234gx,cc=1300");
		params.add("delete;macchina;id=2");
		params.add("add;moto;ruote=2,alim=benzina,cat=strada,colore=nero,marca=Yamaha,anno=2025,modello=r1,targa=EL22239,cc=900");
		params.add("add;bici;ruote=2,alim=manuale,cat=strada,colore=nero,marca=Bianchi,anno=2025,modello=Grizl 5,marce=10,sospenzione=senza,piegevole=no");
		params.add("list;type=all");
		params.add("list;type=filter,tipoVeicolo=macchina");
		
		try {
			ListManager.getInstance().loadConstant();
			new StartVeicolo().execute(params);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
