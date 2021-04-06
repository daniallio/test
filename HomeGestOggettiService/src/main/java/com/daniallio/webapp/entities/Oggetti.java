package com.daniallio.webapp.entities;



import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "HMOGGETTI")
@Data
public class Oggetti {

	
	
	
	@Id
	@Column(name = "ogg_code", nullable = false, updatable = false, insertable = false)
	private String codice;
	
	
	@Column(name = "ogg_desc")
	@Basic(optional = false)
	private String descrizione;
	
	@Column(name = "ogg_att")
	@Type(type = "numeric_boolean")
	private boolean attivo; 
	
	@ManyToOne
	@EqualsAndHashCode.Exclude //per problema su loombook
	@JoinColumn(name = "ogg_tipo",referencedColumnName = "tip_code") //name è il nome della colonna FK, reference.. nome della chiave primaria nella tab collegata
	@JsonBackReference (value="tipo") //punto di arrivo che non verrà mostrato se estraggo il json della classe
	private Tipi tipo;
	
	@Column(name = "ogg_data_acq")
	@Temporal(TemporalType.DATE)
	private Date dataAcquisto;
	
	@Column(name = "ogg_note")
	private String note;
	
	
	@Column(name = "ogg_valore")
	private Double valore;
	
	@ManyToOne
	@EqualsAndHashCode.Exclude //per problema su loombook
	@JoinColumn(name = "ogg_stanza",referencedColumnName = "sta_code") //name è il nome della colonna FK, reference.. nome della chiave primaria nella tab collegata
	@JsonBackReference (value="stanza") //punto di arrivo che non verrà mostrato se estraggo il json della classe
	private Stanza stanza;
	
	
	
	public OggettiDTO convertToDTO() {
		
	  OggettiDTO oggettoDTO = new OggettiDTO();
	
	  oggettoDTO.setCodice(this.codice);
	  oggettoDTO.setAttivo(this.attivo);
	  oggettoDTO.setDataAcquisto(this.dataAcquisto);
	  oggettoDTO.setDescrizione(this.descrizione);
	  oggettoDTO.setNote(this.note);
	  oggettoDTO.setValore(this.valore);
	  oggettoDTO.setTipo(this.tipo.getCodice());
	  oggettoDTO.setStanza(this.stanza.getCodice());
	  
	  return oggettoDTO;
	  
	  
			  
		
		
	}
}
