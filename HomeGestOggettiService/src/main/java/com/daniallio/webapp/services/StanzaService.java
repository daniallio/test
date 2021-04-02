package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import com.daniallio.webapp.entities.Stanza;

public interface StanzaService {

	
	public Optional<Stanza> selStanzaById(String codStanza);
	
	public List<Stanza> selAllStanze(); 
}
