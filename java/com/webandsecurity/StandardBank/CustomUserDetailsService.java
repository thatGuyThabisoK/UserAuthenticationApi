package com.webandsecurity.StandardBank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private LoginRepository repo;
	
	@Autowired
	public CustomUserDetailsService(LoginRepository repo) {
		this.repo = repo;
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = null;
		try{
			repo.getUserCredentials();
			
			user = User.builder()
					.username(repo.userCredentials)
					.password(repo.pass)
					.build();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
