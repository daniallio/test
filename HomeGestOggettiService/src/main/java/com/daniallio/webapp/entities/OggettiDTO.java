package com.daniallio.webapp.entities;

import java.util.Date;

import lombok.Data;


@Data
public class OggettiDTO {

	
	
	
	private String codice;
	
	

	private String descrizione;
	
	
	private boolean attivo; 
	
	
	private String tipo;
	
	
	private Date dataAcquisto;
	
	
	private String note;
	
	
	private Double valore;
	
	
	private String stanza;
	
	
	
}
