package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniallio.webapp.entities.Stanza;
import com.daniallio.webapp.repository.StanzaRepository;

@Service
@Transactional
public class StanzaServiceImp implements StanzaService{

	
	@Autowired
	StanzaRepository repo;
	
	@Override
	public Optional<Stanza> selStanzaById(String codStanza) {

		return repo.findById(codStanza);
		
	}

	@Override
	public List<Stanza> selAllStanze() {
		
		return repo.findAll();
		
	}

	@Override
	public void insStanza(Stanza stanza) {
		
		repo.save(stanza);
		
	}

	@Override
	public void updStanza(Stanza stanza) {
		// TODO Auto-generated method stub
		
	}

	

}
