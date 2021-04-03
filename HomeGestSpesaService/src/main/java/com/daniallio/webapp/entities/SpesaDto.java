package com.daniallio.webapp.entities;

import java.util.Date;

import lombok.Data;

@Data
public class SpesaDto {

	
	
	private int idSpesa;
	
	private String spesaElenco;	
	
	private String errMail;
	
	private String statoMail;
	
	private boolean invioMail; 	

	private Date dateListaSpesa;	
	
	private String codiceUtente;
	
	
}
