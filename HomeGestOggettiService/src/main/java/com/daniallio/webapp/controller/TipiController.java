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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daniallio.webapp.entities.Tipi;
import com.daniallio.webapp.entities.TipiDTO;
import com.daniallio.webapp.exceptions.TipoExistException;
import com.daniallio.webapp.exceptions.TipoNotFoundException;
import com.daniallio.webapp.services.TipiService;


@Controller
@RequestMapping("api/tipi")
public class TipiController {

	
	private static final Logger logger = LoggerFactory.getLogger(TipiController.class);
	
	@Autowired
	TipiService serviceTipi;
	
	
	//recupero tutti i tipi
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<TipiDTO>> selAllTipi() {
		
		logger.info("********Medoto selAllTipi");
		
		//recupero tutti i tipi
		List<Tipi> tipi = serviceTipi.sellTipi();
		//li converto in DTO
		List<TipiDTO> tipiDTO = new ArrayList<TipiDTO>();
				
		tipi.forEach(tipo -> tipiDTO.add(tipo.tipiDTO()));
		
		return new ResponseEntity<List<TipiDTO>> (tipiDTO, HttpStatus.OK);
		
		
	}
	
	
	
	@GetMapping(value = "/cerca/{codice}" , produces =  "application/json")
	public ResponseEntity<TipiDTO> sellTipoById (@PathVariable("codice") String codice) throws TipoNotFoundException{
		
		
		logger.info("********Medoto sellTipoById");
		
		Optional<Tipi> tipi = serviceTipi.selTipoById(codice);
		TipiDTO tipiDTO;
		
		//se esite lo converto in DTO altrimenti eccezione
		if(tipi.isPresent()) {
			
			tipiDTO  = tipi.get().tipiDTO();
		}else {
			
			throw new TipoNotFoundException("Codice tipo inesistente " + codice);
		}
		
		return new ResponseEntity<TipiDTO>(tipiDTO,HttpStatus.OK);
		
	}
	
	
	@PostMapping(value ="/inserisci", produces =  "application/json")
	public ResponseEntity<TipiDTO> insTipo (@RequestBody TipiDTO tipiDTO) throws TipoExistException{
		
		//verifico che il tipo non sia già esistente
		Optional<Tipi> tipoOpt = serviceTipi.selTipoById(tipiDTO.getCodice());
		
		//se esiste già errore altrimenti inserisco
		if(tipoOpt.isPresent()) {
			
			throw new TipoExistException("Tipo già esistente");
		}else {
			
			//attribuisco i valori dell tipo da inserire
			Tipi tipo = new Tipi();
			tipo.setCodice(tipiDTO.getCodice());
			tipo.setDescrizione(tipiDTO.getDescrizione());
			tipo.setEntity(tipiDTO.getEntity());
			
			serviceTipi.insTipo(tipo);
			
			
			return new ResponseEntity<TipiDTO>(tipiDTO, HttpStatus.OK);
			
		}
		
		
		
	}
	
	
	
	
	
	
}
