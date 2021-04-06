package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniallio.webapp.entities.Oggetti;
import com.daniallio.webapp.repository.OggettiRepository;

@Service
@Transactional
public class OggettoServiceImp implements OggettoService{

	
	@Autowired
	OggettiRepository repo;
	
	
	
	@Override
	public List<Oggetti> sellAllOggetti() {
		
		return repo.findAll();
		
	}

	@Override
	public Optional<Oggetti> selOggettoById(String oggetto) {
	
		return repo.findById(oggetto);
		
	}

	@Override
	public void insOggetto(Oggetti oggetto) {
		
		repo.save(oggetto);
		
		
	}

	@Override
	public void updOggetto(Oggetti oggetto) {
		// TODO Auto-generated method stub
		
	}

}
