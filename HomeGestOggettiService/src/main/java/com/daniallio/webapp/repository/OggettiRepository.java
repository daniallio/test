package com.daniallio.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.daniallio.webapp.entities.Oggetti;
@Repository

public interface OggettiRepository extends JpaRepository<Oggetti, String>{

}
