package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniallio.webapp.entities.Tipi;
import com.daniallio.webapp.repository.TipiRepository;

@Service
@Transactional
public class TipiServiceImp implements TipiService{

	@Autowired
	TipiRepository repo;
	
	@Override
	public List<Tipi> sellTipi() {
		
		return repo.findAll();
		
	}

	@Override
	public Optional<Tipi> selTipoById(String tipo) {
		
		
		return  repo.findById(tipo);
		
	}

	@Override
	public void insTipo(Tipi tipo) {
		
		repo.save(tipo);
		
	}

}
