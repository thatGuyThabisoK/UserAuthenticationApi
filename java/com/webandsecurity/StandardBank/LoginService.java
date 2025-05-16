package com.webandsecurity.StandardBank;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	private JwtUtil jwt;
	private AuthenticationManager auth;
	private CustomUserDetailsService customDetails;
	
	
	public LoginService(AuthenticationManager auth, JwtUtil jwt, CustomUserDetailsService customDetails) {
		this.auth = auth;
		this.jwt = jwt;
		this.customDetails = customDetails;
	}

	public ResponseEntity<?> login(User user) {
		Authentication userToAuth = auth.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
				);
		
		if(userToAuth.isAuthenticated()) {
			UserDetails authenticated = customDetails.loadUserByUsername(userToAuth.getName());
			String token = jwt.generateToken(authenticated);
			Map<String, String> response = new HashMap<>();
			response.put("token", token);
			return ResponseEntity.ok(response);
		}
		
		return ResponseEntity.badRequest().body("User could not be authenticated");
	}

}
