package com.burger.java.burger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.burger.java.burger.fixtures.UserFixture;



@SpringBootApplication
public class BurgerApplication implements CommandLineRunner{

	@Autowired
	private UserFixture userFixture;
	
	public static void main(String[] args) {

		
		SpringApplication.run(BurgerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	    /* userFixture.loadDefaultAdmin();
		userFixture.loadDefaultUser();    
		 */
	}


}
