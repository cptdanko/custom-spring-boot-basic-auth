package com.mdt.security.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomSecurityApplicationTests {

	@Test
	void contextLoads() {
		int val = 3;
		Assertions.assertEquals(val, 3);
	}

}
