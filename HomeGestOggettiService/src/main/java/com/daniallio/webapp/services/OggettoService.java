package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import com.daniallio.webapp.entities.Oggetti;

public interface OggettoService {

	
	public List<Oggetti> sellAllOggetti ();
	
	public Optional<Oggetti> selOggettoById (String oggetto);
	
	public void insOggetto(Oggetti oggetto); 
	
	public void updOggetto(Oggetti oggetto); 
	
}
