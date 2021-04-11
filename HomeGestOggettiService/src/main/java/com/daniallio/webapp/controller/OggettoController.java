package com.daniallio.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daniallio.webapp.entities.Oggetti;
import com.daniallio.webapp.entities.OggettiDTO;
import com.daniallio.webapp.entities.Stanza;
import com.daniallio.webapp.entities.Tipi;
import com.daniallio.webapp.services.OggettoService;
import com.daniallio.webapp.services.StanzaService;
import com.daniallio.webapp.services.TipiService;


@Controller
@RequestMapping("api/oggetto")
public class OggettoController {

	
	private static final Logger logger = LoggerFactory.getLogger(OggettoController.class);
	
	
	@Autowired
	OggettoService serviceOggetto;
	
	@Autowired
	StanzaService serviceStanza;
	
	@Autowired
	TipiService serviceTipo;
	
	
	//ritorno tutti gli oggetti
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<OggettiDTO>> selAllOggetti (){
		
		List<Oggetti> stanze = serviceOggetto.sellAllOggetti();
		
		
		//converto l'elenco in DTO
		List<OggettiDTO> stanzeDTO = new ArrayList<OggettiDTO>();
				
		stanze.forEach(s -> stanzeDTO.add(s.convertToDTO()));
		
		return new ResponseEntity<List<OggettiDTO>>(stanzeDTO,HttpStatus.OK);
		
	}
	
	//inserisco un oggetto
	//DA SISTEMARE
	@PostMapping(value="/inserisci", produces = "application/json")
	public ResponseEntity<OggettiDTO> insOggetto (@RequestBody OggettiDTO oggDTO){
		
		
		Oggetti oggetto = new Oggetti();
		
		Optional <Stanza> stanza = serviceStanza.selStanzaById(oggDTO.getStanza());
		Optional <Tipi> tipo = serviceTipo.selTipoById(oggDTO.getTipo());
		
		oggetto.setAttivo(oggDTO.isAttivo());
		oggetto.setCodice(oggDTO.getCodice());
		oggetto.setDataAcquisto(oggDTO.getDataAcquisto());
		oggetto.setDescrizione(oggDTO.getDescrizione());
		oggetto.setNote(oggDTO.getNote());
		oggetto.setStanza(stanza.get());
		oggetto.setTipo(tipo.get());
		oggetto.setValore(oggDTO.getValore());
		
		serviceOggetto.insOggetto(oggetto);
		
		return new ResponseEntity<OggettiDTO>(oggDTO,HttpStatus.OK);
		
		
	}
	
}
