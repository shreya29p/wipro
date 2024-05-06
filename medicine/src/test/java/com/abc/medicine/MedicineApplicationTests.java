package com.abc.medicine;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "eureka.client.enabled=false")
class MedicineApplicationTests {

	@Test
	void contextLoads() {
	}

}
