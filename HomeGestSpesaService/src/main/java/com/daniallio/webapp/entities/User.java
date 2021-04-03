package com.daniallio.webapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "HMUTENTI")
@Data
public class User implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -78173817642881556L;

	@Id
	@Column(name = "usr_codice", nullable = false, updatable = false, insertable = false)
	private String codice;

	@Column(name = "usr_nome")
	@Basic(optional = false)
	private String nome;

	@Column(name = "usr_psw")
	private String password;

	@Column(name = "usr_email")
	private String email;

	// relazione una molti con la tabella della lista spesa	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "utente", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonManagedReference // solo quando il webservice resituisce un json, punto di partenza nella classe
							// colllegata invece inserisco JSONBACK
	private Set<Spesa> spesa = new HashSet();

	
	
	
	//metodo per convertire l'utente e restituire solo i dati non sensibili e l'elenco spese
	public UserDto convertToDto() {
		
		UserDto userDto = new UserDto();
		
		userDto.setCodice(this.codice);
		userDto.setEmail(this.email);
		userDto.setNome(this.nome);
		
		
		return userDto;
	}
	
	
}
