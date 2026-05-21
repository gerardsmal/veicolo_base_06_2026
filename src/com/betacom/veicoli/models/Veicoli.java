package com.betacom.veicoli.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Veicoli {
	private Integer id;    // id univoco del record
	private String tipoVeicolo;  // macchina, moto, bici
	private Integer numeroRuote;  // dipende del tipo veicolo
	private String tipoAlimentazione;   // bensine, diesel, electrica, hybrid, manual
	private String categoria;     // strada, fuoristrada, suv, motocross,,, // dipende del tipo veicolo
	private String colore;
	private String marca;
	private Integer annoProduzione;
	private String modello;
	
}
