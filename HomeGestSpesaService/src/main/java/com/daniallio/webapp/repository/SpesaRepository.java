package com.daniallio.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniallio.webapp.entities.Spesa;

@Repository
public interface SpesaRepository extends JpaRepository<Spesa, Integer>{

	
	public List<Spesa> findByUtente_Codice(String codice);
	
}
