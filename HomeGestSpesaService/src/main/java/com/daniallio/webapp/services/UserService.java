package com.daniallio.webapp.services;

import java.util.List;
import java.util.Optional;

import com.daniallio.webapp.entities.User;

public interface UserService {

	
	
	public List<User> selAllUsers();
	
	public Optional<User> selByCod (String usr_codice);
	
	public User selByEmail (String email);
}
