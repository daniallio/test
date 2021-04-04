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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daniallio.webapp.entities.Stanza;
import com.daniallio.webapp.entities.StanzaDTO;
import com.daniallio.webapp.exceptions.StanzaExistException;
import com.daniallio.webapp.exceptions.StanzaNotFoundException;
import com.daniallio.webapp.services.StanzaService;

@Controller
@RequestMapping("api/stanza")
public class StanzaController {

	
	
	private static final Logger logger = LoggerFactory.getLogger(StanzaController.class);
	
	@Autowired
	StanzaService service;
	
	//ritorna tutte le stanze
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<StanzaDTO>> selAllStanze (){
		
		logger.info("********Medoto selAllStanze");
		
		
		List<Stanza> stanze =  service.selAllStanze();		
		//converto in DTO
		List<StanzaDTO> stanzeDTO = new ArrayList<StanzaDTO>();
		stanze.forEach(s -> stanzeDTO.add(s.convertToDTO()));
		
		return new ResponseEntity<List<StanzaDTO>>(stanzeDTO, HttpStatus.OK);

	}
	
	
	//inserisco una nuova stanza
	@PostMapping(value = "/inserisci", produces = "application/json")
	public ResponseEntity<Stanza> insStanza(@RequestBody Stanza stanza) throws StanzaExistException {
		
		logger.info("********MEedoto insStanza. Stanza con ID " + stanza.getCodice());
		
		//verifico se già esistente
		
		Optional<Stanza> existsStanza = service.selStanzaById(stanza.getCodice());
		
		if(existsStanza.isPresent()) {
			throw new StanzaExistException("La stanza con codice " + stanza.getCodice() + " è già esistente." );
		}
		
		service.insStanza(stanza);
		
		return new ResponseEntity<Stanza>(stanza,HttpStatus.OK);
	}
	
	
	//aggiorno stanza
	@PutMapping(value ="/aggiorna", produces = "application/json")
	public ResponseEntity<Stanza> updStanza(@RequestBody Stanza stanza) throws StanzaNotFoundException{
		
		
		logger.info("********Medoto updStanza. Stanza con ID " + stanza.getCodice());
		
		
		//verifico che la stanza esista
		Optional<Stanza> existsStanza = service.selStanzaById(stanza.getCodice());
		
		if(!existsStanza.isPresent()) {
			throw new StanzaNotFoundException("La stanza con codice " + stanza.getCodice() + " non esiste." );
		}
		
		//aggiorno
		service.insStanza(stanza);
		
		return new ResponseEntity<Stanza>(stanza, HttpStatus.OK);
	}
	
	
	
}
