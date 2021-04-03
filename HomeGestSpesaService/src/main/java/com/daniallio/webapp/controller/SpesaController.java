package com.daniallio.webapp.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daniallio.webapp.comp.SmtpMailSender;
import com.daniallio.webapp.entities.Spesa;
import com.daniallio.webapp.entities.SpesaDto;
import com.daniallio.webapp.entities.User;
import com.daniallio.webapp.exceptions.SpesaNotFoundException;
import com.daniallio.webapp.exceptions.UserNotFoundException;
import com.daniallio.webapp.services.SpesaService;
import com.daniallio.webapp.services.UserService;

@Controller
@RequestMapping("api/spesa")
public class SpesaController {

	private static final Logger logger = LoggerFactory.getLogger(SpesaController.class);

	@Autowired
	private SpesaService spesaService;

	@Autowired
	private UserService userService;

	// componente per invio mail
	@Autowired
	private SmtpMailSender sender;

	@GetMapping(value = "/all/amministratore", produces = "application/json")
	public ResponseEntity<List<SpesaDto>> listAllSpeseAMM() {
		logger.info("****** Metodo  listAllSpeseAMM *******");

		List<Spesa> spese = spesaService.selAllSpese();
		List<SpesaDto> speseDto = new ArrayList<>();

		spese.forEach(spesa -> speseDto.add(spesa.convertToDto()));

		return new ResponseEntity<List<SpesaDto>>(speseDto, HttpStatus.OK);
	}

	@GetMapping(value = "/user/{codice}", produces = "application/json")
	public ResponseEntity<List<Spesa>> listAllSpeseUser(@PathVariable("codice") String codice) {

		logger.info("****** Metodo  listAllSpeseUser *******");
		List<Spesa> spese = spesaService.sellAllByUserId(codice);
		return new ResponseEntity<List<Spesa>>(spese, HttpStatus.OK);

	}

	// inserimento spesa
	@PostMapping(value = "/inserisci")
	public ResponseEntity<SpesaDto> insSpesa(@RequestBody SpesaDto spesaDto) throws UserNotFoundException {

		logger.info("****** Metodo  insSpesa" + " ID " + spesaDto.getCodiceUtente() + "*******");

		// verifico l'esistenza dell'utente
		Optional<User> userOpt = userService.selByCod(spesaDto.getCodiceUtente());

		if (!userOpt.isPresent()) {
			logger.info(
					"****** Metodo  listAllSpeseUser - Utente non presente" + spesaDto.getCodiceUtente() + "*******");
			throw new UserNotFoundException("Codice Utente errato o inesistente");

		} else {

			// recupero l'utente da associare alla spesa
			User user = userOpt.get();

			// inserisco la spesa

			Spesa spesa = new Spesa();

			// data d'inserimento
			
			Date date = new Date();

			
			logger.info("****** Prima di settare spesa *******");
			
			// mappo i campi da inserire
			spesa.setSpesaElenco(spesaDto.getSpesaElenco());
			spesa.setErrMail(spesaDto.getErrMail());
			spesa.setUtente(user);
			spesa.setStatoMail(spesaDto.getStatoMail());
			spesa.setInvioMail(spesaDto.isInvioMail());
			spesa.setDateListaSpesa(spesaDto.getDateListaSpesa());
			spesa.setDateListaSpesa(date);

			logger.info("****** Metodo  prima dell'invio mail *******");
			
			
			if (spesa.isInvioMail() == true) {

				try {
					sender.send(user.getEmail(), "Lista Spesa", spesa.getSpesaElenco());
					spesa.setStatoMail("S");
				} catch (MessagingException e) {

					spesa.setErrMail(e.getMessage());
					spesa.setStatoMail("E");
					// e.printStackTrace();
				} catch (MailSendException e) {
					spesa.setErrMail(e.getMessage());
					spesa.setStatoMail("E");
				}

			}

			spesaService.insSpesa(spesa);

			// setto l'id della spesa appena creato e lo stato della mail e relativo errore
			spesaDto.setIdSpesa(spesa.getIdSpesa());
			spesaDto.setErrMail(spesa.getErrMail());
			spesaDto.setStatoMail(spesa.getStatoMail());
			spesaDto.setDateListaSpesa(spesa.getDateListaSpesa());

			return new ResponseEntity<SpesaDto>(spesaDto, HttpStatus.OK);

		}

	}

	// aggiorno spesa
	@PutMapping(value = "/aggiorna")
	public ResponseEntity<SpesaDto> updSpesa(@RequestBody SpesaDto spesaDto) throws SpesaNotFoundException, UserNotFoundException {

		
		logger.info("****** Metodo  updSpesa" + " ID " + spesaDto.getIdSpesa() + "*******");
		
		//verifico la correttezza del codice spesa
		Optional<Spesa> optSpesa = spesaService.selById(spesaDto.getIdSpesa());
		
		//se non esiste la spesa errore
		if(!optSpesa.isPresent()) {
			logger.warn(
					"****** Metodo  updSpesa - spesa non presente" + spesaDto.getIdSpesa() + "*******");
			throw new SpesaNotFoundException("Codice Spesa Errato o non esistente");
			
		}
		
		// verifico presenza utente
		Optional <User> optUser = userService.selByCod(spesaDto.getCodiceUtente());
		
		if (!optUser.isPresent()) {
			logger.warn(
					"****** Metodo  updSpesa - spesa non presente" + spesaDto.getCodiceUtente() + "*******");
			throw new UserNotFoundException("Codice Utente errato o inesistente");

		} 
		
		
		
		
		// recupero l'utente da associare alla spesa
		User user = optUser.get();

		// inserisco la spesa

		Spesa spesa = optSpesa.get();

		// data d'inserimento
		
		Date date = new Date();

		// mappo i campi da inserire
		spesa.setSpesaElenco(spesaDto.getSpesaElenco());
		spesa.setErrMail(spesaDto.getErrMail());
		spesa.setUtente(user);
		spesa.setStatoMail(spesaDto.getStatoMail());
		spesa.setInvioMail(spesaDto.isInvioMail());
		spesa.setDateListaSpesa(spesaDto.getDateListaSpesa());
		spesa.setDateListaSpesa(date);

		if (spesa.isInvioMail() == true) {

			try {
				sender.send(user.getEmail(), "Lista Spesa", spesa.getSpesaElenco());
				spesa.setStatoMail("S");
			} catch (MessagingException e) {

				spesa.setErrMail(e.getMessage());
				spesa.setStatoMail("E");
				// e.printStackTrace();
			} catch (MailSendException e) {
				spesa.setErrMail(e.getMessage());
				spesa.setStatoMail("E");
			}

		}

		spesaService.insSpesa(spesa);

		// setto l'id della spesa appena creato e lo stato della mail e relativo errore
		spesaDto.setIdSpesa(spesa.getIdSpesa());
		spesaDto.setErrMail(spesa.getErrMail());
		spesaDto.setStatoMail(spesa.getStatoMail());
		spesaDto.setDateListaSpesa(spesa.getDateListaSpesa());

		return new ResponseEntity<SpesaDto>(spesaDto, HttpStatus.OK);
		

	}
	
	
	//elimina spesa
	@DeleteMapping (value = "/elimina/{id}", produces = "application/json")
	public ResponseEntity<Spesa> deleteSpesa(@PathVariable("id") int idSpesa) throws SpesaNotFoundException{
		
		logger.info("****** Metodo  deleteSpesa" + " ID " + idSpesa + "*******");
		
		//verifico la correttezza del codice spesa
		Optional<Spesa> optSpesa = spesaService.selById(idSpesa);
		//se non esiste la spesa errore
				if(!optSpesa.isPresent()) {
					logger.warn(
							"****** Metodo  updSpesa - spesa non presente " + idSpesa + "*******");
					throw new SpesaNotFoundException("Codice Spesa Errato o non esistente");
					
				}
				
				spesaService.delSpesa(optSpesa.get());
				
		
				return new ResponseEntity<Spesa>(optSpesa.get(), HttpStatus.OK);
		
	}

}
