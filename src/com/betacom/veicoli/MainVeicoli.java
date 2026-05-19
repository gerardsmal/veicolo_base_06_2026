package com.betacom.veicoli;

import java.util.List;

import com.betacom.veicoli.process.StartVeicolo;
import com.betacom.veicoli.singleton.ListManager;
import com.betacom.veicoli.utilities.Utils;

public class MainVeicoli {
	public static String PATH_OUPUT= "/Users/gerard/Downloads/result_veicoli.txt";
	
	public static void main(String[] args) {
		List<String> params = Utils.readFile("src/parametri.txt");
		
		try {
			ListManager.getInstance().loadConstant();
			new StartVeicolo().execute(params);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
