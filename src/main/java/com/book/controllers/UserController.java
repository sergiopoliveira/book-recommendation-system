package com.book.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.book.api.model.UserDTO;
import com.book.service.UserService;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

	public static final String BASE_URL = "/api/users";
	
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO createNewUser(@RequestBody UserDTO userDTO) {
		
		return userService.createNewUser(userDTO);
}

}
