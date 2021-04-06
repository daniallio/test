package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;


import com.daniallio.webapp.entities.Tipi;

public interface TipiService {

	
	
	public List<Tipi> sellTipi ();
	
	public Optional<Tipi> selTipoById (String tipo);
	
	public void insTipo(Tipi tipo); 
	
	
	
	
	
	
}
