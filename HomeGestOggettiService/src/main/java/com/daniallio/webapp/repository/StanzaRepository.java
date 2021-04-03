package com.daniallio.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.daniallio.webapp.entities.Stanza;

@Repository
public interface StanzaRepository extends JpaRepository<Stanza, String>{

}
