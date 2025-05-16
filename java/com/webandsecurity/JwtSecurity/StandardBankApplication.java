package com.webandsecurity.StandardBank;

import java.io.BufferedWriter;

import java.io.FileWriter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StandardBankApplication {

	public static void main(String[] args) {
		
		
		String name = String.valueOf("Thabiso".hashCode());
		String pass = String.valueOf("qwerty@123456".hashCode());
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("userDetails.txt"));
			writer.write(name,0,name.length());
			writer.newLine();
			writer.write(pass,0,name.length());
			writer.close();
			System.out.println("Wrote to file");
		}catch(Exception e) {
			System.err.println("An error occured while ");
		}
		
		
		SpringApplication.run(StandardBankApplication.class, args);
	}

}
