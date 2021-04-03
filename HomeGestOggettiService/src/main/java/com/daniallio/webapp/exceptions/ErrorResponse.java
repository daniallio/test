package com.daniallio.webapp.exceptions;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
	public class ErrorResponse {

		
		private final String messaggio;	
		private final HttpStatus status;
		private final ZonedDateTime time;
	}

	
	

