package com.daniallio.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daniallio.webapp.entities.User;
import com.daniallio.webapp.entities.UserDto;
import com.daniallio.webapp.services.UserService;


@Controller
@RequestMapping("api/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	
	//ritorno tutti gli utenti
	@GetMapping(value = "/all/amministratore", produces = "application/json")
	public ResponseEntity<List<User>>listAllUsersAMM(){
		
		logger.info("****** Metodo  listAllUsersAMM *******");
		
		List<User> users = userService.selAllUsers();
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		
	}
	
	
	//ritorno tutti gli utenti dto
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<UserDto>> listAllUsers(){
		
		logger.info("****** Metodo  listAllUsers *******");
		
		List <User> users = userService.selAllUsers();
		
		//converto gli elementi della lista in dto
		List <UserDto> usersDto = new ArrayList<UserDto>();
		users.forEach(user -> usersDto.add(user.convertToDto()));
		
		return new ResponseEntity<List<UserDto>>(usersDto,HttpStatus.OK);	
		
		
	}
	
	
	
	
}
