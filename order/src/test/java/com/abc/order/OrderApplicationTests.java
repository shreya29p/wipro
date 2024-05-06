package com.abc.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "eureka.client.enabled=false")
class OrderApplicationTests {

	@Test
	void contextLoads() {
	}

}
