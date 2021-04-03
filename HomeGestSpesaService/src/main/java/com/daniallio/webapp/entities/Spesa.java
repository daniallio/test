package com.daniallio.webapp.entities;

import java.util.Date;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "HMSPESA")
@Data

public class Spesa {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spe_id_spesa", nullable = false, updatable = false, insertable = false)
	private int idSpesa;
	
	@Column(name = "spe_elenco")
	@Basic(optional = false)
	private String spesaElenco;
	
	
	@Column(name = "spe_error_mail")
	private String errMail;
	
	@Column(name = "spe_mail_stato")
	private String statoMail;
	
	@Column(name = "spe_invio_mail")
	@Type(type = "numeric_boolean")
	private boolean invioMail; 
	
	@Column(name = "spe_data")
	@Temporal(TemporalType.DATE)
	private Date dateListaSpesa;
	
	
	@ManyToOne
	@EqualsAndHashCode.Exclude //per problema su loombook
	@JoinColumn(name = "spe_id_utente",referencedColumnName = "usr_codice") //name è il nome della colonna FK, reference.. nome della chiave primaria nella tab collegata
	@JsonBackReference //punto di arrivo che non verrà mostrato se estraggo il json della classe
	private User utente;
	
	
	public SpesaDto convertToDto() {
		
		SpesaDto spesaDTO = new SpesaDto();
		
		spesaDTO.setCodiceUtente(this.utente.getCodice());
		spesaDTO.setIdSpesa(this.idSpesa);
		spesaDTO.setSpesaElenco(this.spesaElenco);
		spesaDTO.setDateListaSpesa(this.dateListaSpesa);
		spesaDTO.setInvioMail(this.invioMail);
		spesaDTO.setStatoMail(this.statoMail);
		spesaDTO.setErrMail(this.errMail);
		
		return spesaDTO;
		
		
	}
}
