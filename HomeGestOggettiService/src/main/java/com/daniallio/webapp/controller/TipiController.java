package com.daniallio.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daniallio.webapp.entities.Tipi;
import com.daniallio.webapp.entities.TipiDTO;
import com.daniallio.webapp.services.TipiService;


@Controller
@RequestMapping("api/tipi")
public class TipiController {

	
	
	
	@Autowired
	TipiService serviceTipi;
	
	
	//recupero tutti i tipi
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<TipiDTO>> selAllTipi() {
		
		//recupero tutti i tipi
		List<Tipi> tipi = serviceTipi.sellTipi();
		//li converto in DTO
		List<TipiDTO> tipiDTO = new ArrayList<TipiDTO>();
				
		tipi.forEach(tipo -> tipiDTO.add(tipo.tipiDTO()));
		
		return new ResponseEntity<List<TipiDTO>> (tipiDTO, HttpStatus.OK);
		
		
	}
	
	
	
	@GetMapping(value = "/cerca/{codice}" , produces =  "application/json")
	public ResponseEntity<TipiDTO> sellTipoById (@PathVariable("codice") String codice){
		
		Optional<Tipi> tipi = serviceTipi.selTipoById(codice);
		TipiDTO tipiDTO;
		
		if(tipi.isPresent()) {
			
			tipiDTO  = tipi.get().tipiDTO();
		}else {
			return null;
		}
		
		return new ResponseEntity<TipiDTO>(tipiDTO,HttpStatus.OK);
		
	}
	
	
}
