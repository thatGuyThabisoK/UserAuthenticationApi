package com.webandsecurity.JwtSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class CustomAuthentication implements AuthenticationProvider {
	
	@Autowired
	private CustomUserDetailsService userdetails;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserDetails user = userdetails.loadUserByUsername(authentication.getName());
		
		return checkPassword(user,authentication.getCredentials().toString());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return  UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	private Authentication checkPassword(UserDetails user, String pass) {
		
		if(user.getPassword().equals(String.valueOf(pass.hashCode()))) {
			return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
	}

}
