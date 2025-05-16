package com.webandsecurity.StandardBank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private JwtAuthenticationFilter jwt; 
	private CustomAuthentication authProvider;
	
	public SecurityConfig(JwtAuthenticationFilter jwt, CustomAuthentication auth) {
		authProvider = auth;
		this.jwt = jwt;
	}
	
	@Bean
	public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
		
		return http
				.csrf(crfs-> crfs.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth-> auth
						.requestMatchers("/authenticate").permitAll()
						.anyRequest().authenticated())
				.addFilterBefore(jwt,UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
	
	@Autowired
    private void CustomAuthProvider(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

}
