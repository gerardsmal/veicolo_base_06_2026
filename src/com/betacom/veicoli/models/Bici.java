package com.betacom.veicoli.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bici extends Veicoli{
	private Integer numeroMarce;
	private String tipoFreno; // tamburo, disco
	private String tipoSospenzione; // senza, mono, bi
	private Boolean piegevole;
	
	@Override
	public String toString() {
		return "Bici [Id=" + getId() + ", TipoVeicolo=" + getTipoVeicolo() + ", NumeroRuote="
				+ getNumeroRuote() + ", TipoAlimentazione=" + getTipoAlimentazione() + ", Categoria="
				+ getCategoria() + ", getColore()=" + getColore() + ", getMarca()=" + getMarca()
				+ ", AnnoProduzione=" + getAnnoProduzione() + ", Modello=" + getModello() 
				+ ", numeroMarche=" + numeroMarce + ", tipoSospenzione=" + tipoSospenzione + ", piegevole="
				+ piegevole +"]";
	}
}
