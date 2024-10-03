package com.deepanshu.dairydriveapi;

import com.deepanshu.dairydriveapi.services.EmailService;
import com.deepanshu.dairydriveapi.utilities.Helper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@SpringBootTest
class DairydriveapiApplicationTests {

	@Test
	void contextLoads() {

	}

	@Autowired
	private EmailService emailService;
	@Test
	void sendEmailTest(){
		emailService.sendEmailWithHtmlContent(
				"sahu.deeepanshu.united@gmail.com",
				"Testing Email Service for DairyDrive",
				Helper.getBodyForEmailVerification("Deepak",UUID.randomUUID().toString())
		);
	}


	@Test
	void giveEncryptPassword(){
		System.out.println(new BCryptPasswordEncoder().encode("ab34c"));
	}

}
