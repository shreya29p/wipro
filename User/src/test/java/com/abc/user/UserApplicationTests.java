package com.abc.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "eureka.client.enabled=false")
class UserApplicationTests {

	@Test
	void contextLoads() {
	}

}
