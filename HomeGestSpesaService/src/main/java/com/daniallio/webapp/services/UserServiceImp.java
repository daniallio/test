package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniallio.webapp.entities.User;
import com.daniallio.webapp.repository.UserRepository;

@Service
@Transactional
public class UserServiceImp implements UserService{
	
	
	@Autowired
	UserRepository repo;
	
	
	@Override
	public List<User> selAllUsers() {
		
		return repo.findAll();
	}

	@Override
	public Optional<User> selByCod(String codice) {
		
		return repo.findById(codice);
	}

	@Override
	public User selByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
