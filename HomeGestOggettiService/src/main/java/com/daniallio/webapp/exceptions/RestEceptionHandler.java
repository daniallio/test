package com.daniallio.webapp.exceptions;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class RestEceptionHandler {

	//Stanza non trovata
	@ExceptionHandler(StanzaNotFoundException.class) //inserisco la classe creata che estende exception
	public ResponseEntity<Object> userNotFoundHandler(StanzaNotFoundException ex){ //nel generico dell'handler inserisco la classe con messaggio ed ID
		
		
		//dati che verranno ritornati dall'eccezione
		ErrorResponse error = new ErrorResponse(ex.getMessage(),				
				HttpStatus.BAD_REQUEST,
				ZonedDateTime.now());
	
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	
	//Stanza esistente
	@ExceptionHandler(StanzaExistException.class) //inserisco la classe creata che estende exception
	public ResponseEntity<Object> userNotFoundHandler(StanzaExistException ex){ //nel generico dell'handler inserisco la classe con messaggio ed ID
		
		
		//dati che verranno ritornati dall'eccezione
		ErrorResponse error = new ErrorResponse(ex.getMessage(),				
				HttpStatus.BAD_REQUEST,
				ZonedDateTime.now());
	
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}
	

	
}
