package com.daniallio.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daniallio.webapp.entities.Stanza;
import com.daniallio.webapp.entities.StanzaDTO;
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
		
		List<Stanza> stanze =  service.selAllStanze();		
		//converto in DTO
		List<StanzaDTO> stanzeDTO = new ArrayList<StanzaDTO>();
		stanze.forEach(s -> stanzeDTO.add(s.convertToDTO()));
		
		return new ResponseEntity<List<StanzaDTO>>(stanzeDTO, HttpStatus.OK);

	}
	
	
}
