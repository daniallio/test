package com.daniallio.webapp.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "HMTIPI")
@Data
public class Tipi {

	
	@Id
	@Column(name = "tip_code", nullable = false, updatable = false, insertable = false)
	private String codice;
	
	
	@Column(name = "tip_desc")
	private String descrizione;
	
	@Column(name = "tip_entity")
	private String entity;
	
	
	// relazione una molti con la tabella degli oggetti
		//un tipo può avere piu oggetti
		@OneToMany(fetch = FetchType.EAGER, mappedBy = "tipo", cascade = CascadeType.REMOVE, orphanRemoval = true)
		@JsonManagedReference (value="tipo") // solo quando il webservice resituisce un json, punto di partenza nella classe
								// colllegata invece inserisco JSONBACK
		private Set<Oggetti> oggetto = new HashSet();
		
		
		public  TipiDTO tipiDTO () {
			
			
			
			TipiDTO tipiDTO = new TipiDTO();
			
			tipiDTO.setCodice(this.codice);
			tipiDTO.setDescrizione(this.descrizione);
			tipiDTO.setEntity(this.entity);
			
			return tipiDTO;
			
			
			
		}
	
}
