package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniallio.webapp.entities.Spesa;
import com.daniallio.webapp.entities.User;
import com.daniallio.webapp.repository.SpesaRepository;


@Service
@Transactional
public class SpesaServiceImp implements SpesaService{

	
	@Autowired
	private SpesaRepository repo;
	
	
	@Override
	public List<Spesa> selAllSpese() {
		return repo.findAll();
		
	}

	@Override
	public Optional<Spesa> selById(int id) {		
		return repo.findById(id);
	}

	@Override
	public List<Spesa> selAllByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Spesa> sellAllByUserId(String codice) {
		
		return repo.findByUtente_Codice(codice);
	}

	@Override
	public void insSpesa(Spesa spesa) {
		
		repo.save(spesa);
		
	}

	@Override
	public void delSpesa(Spesa spesa) {
		
		repo.delete(spesa);
		
	}

}
