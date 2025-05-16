package com.webandsecurity.StandardBank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	
	private LoginService service;
	
	public LoginController(LoginService service) {
		this.service = service;
	}
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> loginUser(@RequestBody User user){
		
		return service.login(user);
	}

}