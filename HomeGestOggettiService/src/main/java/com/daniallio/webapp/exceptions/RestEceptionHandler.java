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
	public ResponseEntity<Object> stanzaNotFoundHandler(StanzaNotFoundException ex){ //nel generico dell'handler inserisco la classe con messaggio ed ID
		
		
		//dati che verranno ritornati dall'eccezione
		ErrorResponse error = new ErrorResponse(ex.getMessage(),				
				HttpStatus.BAD_REQUEST,
				ZonedDateTime.now());
	
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	
	//Stanza esistente
	@ExceptionHandler(StanzaExistException.class) //inserisco la classe creata che estende exception
	public ResponseEntity<Object> stanzaExistHandler(StanzaExistException ex){ //nel generico dell'handler inserisco la classe con messaggio ed ID
		
		
		//dati che verranno ritornati dall'eccezione
		ErrorResponse error = new ErrorResponse(ex.getMessage(),				
				HttpStatus.BAD_REQUEST,
				ZonedDateTime.now());
	
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}
	

	//Tipo già esistente
	@ExceptionHandler(TipoExistException.class) //inserisco la classe creata che estende exception
	public ResponseEntity<Object> tipoExistHandler(TipoExistException ex){ //nel generico dell'handler inserisco la classe con messaggio ed ID
		
		
		//dati che verranno ritornati dall'eccezione
		ErrorResponse error = new ErrorResponse(ex.getMessage(),				
				HttpStatus.BAD_REQUEST,
				ZonedDateTime.now());
	
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	//Tipo non esistente
		@ExceptionHandler(TipoNotFoundException.class) //inserisco la classe creata che estende exception
		public ResponseEntity<Object> tipoNotFoundHandler(TipoNotFoundException ex){ //nel generico dell'handler inserisco la classe con messaggio ed ID
			
			
			//dati che verranno ritornati dall'eccezione
			ErrorResponse error = new ErrorResponse(ex.getMessage(),				
					HttpStatus.BAD_REQUEST,
					ZonedDateTime.now());
		
			
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
			
		}
		
		
		//oggetto esistente 
		@ExceptionHandler(OggettoExistException.class) //inserisco la classe creata che estende exception
		public ResponseEntity<Object> oggettoExistHandler(OggettoExistException ex){ //nel generico dell'handler inserisco la classe con messaggio ed ID
			
			
			//dati che verranno ritornati dall'eccezione
			ErrorResponse error = new ErrorResponse(ex.getMessage(),				
					HttpStatus.BAD_REQUEST,
					ZonedDateTime.now());
		
			
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
			
		}

		
		
		//oggetto non trovato exc
		@ExceptionHandler(OggettoNotFoundException.class) //inserisco la classe creata che estende exception
		public ResponseEntity<Object> oggettoExistHandler(OggettoNotFoundException ex){ //nel generico dell'handler inserisco la classe con messaggio ed ID
			
			
			//dati che verranno ritornati dall'eccezione
			ErrorResponse error = new ErrorResponse(ex.getMessage(),				
					HttpStatus.BAD_REQUEST,
					ZonedDateTime.now());
		
			
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
			
		}
	
}
