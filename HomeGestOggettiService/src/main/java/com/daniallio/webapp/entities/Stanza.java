package com.daniallio.webapp.entities;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
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
@Table(name = "HMSTANZE")
@Data
public class Stanza {

	
	
	@Id
	@Column(name = "sta_code", nullable = false, updatable = false, insertable = false)
	private String codice;
	
	@Column(name = "sta_desc")
	@Basic(optional = false)
	private String descrizione;
	
	@Column(name = "sta_note")
	@Basic(optional = true)
	private String note;
	
	// relazione una molti con la tabella degli oggetti
	//una stanza può avere piu oggetti
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "stanza", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonManagedReference // solo quando il webservice resituisce un json, punto di partenza nella classe
							// colllegata invece inserisco JSONBACK
	private Set<Oggetti> oggetto = new HashSet();

	
	public StanzaDTO convertToDTO(){
		
		StanzaDTO stanzaDTO = new StanzaDTO();
		
		stanzaDTO.setCodice(this.codice);
		stanzaDTO.setDescrizione(this.descrizione);
		stanzaDTO.setNote(this.note);
		
		return stanzaDTO;
	}
	
}
