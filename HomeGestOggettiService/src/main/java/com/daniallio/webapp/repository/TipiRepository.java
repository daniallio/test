package com.daniallio.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.daniallio.webapp.entities.Tipi;
@Repository

public interface TipiRepository extends JpaRepository<Tipi, String>{

}
