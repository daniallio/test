package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import com.daniallio.webapp.entities.Spesa;
import com.daniallio.webapp.entities.User;

public interface SpesaService {

	
	public List<Spesa> selAllSpese();	
	
	public Optional<Spesa> selById(int id);
	
	public List<Spesa> selAllByUser(User user);
	
	public List<Spesa> sellAllByUserId(String codice);
	
	public void insSpesa(Spesa spesa);
	
	public void delSpesa (Spesa spesa);
	
}
