package com.webandsecurity.StandardBank;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {
	
	String userCredentials;
	String pass;
	
	public void getUserCredentials() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("userDetails.txt"));
		for(int i = 0; i < 2; ++i) {
			String x = reader.readLine();
			if(i == 0) userCredentials = x;
			else pass = x ;
			
		}
		reader.close();	
	}

	

}
