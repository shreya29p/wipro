package com.abc.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.order.model.Medicine;


@FeignClient(name = "medicine", fallbackFactory = MedicineServiceFallbackFactory.class)
public interface MedicineServiceConsumer {

	@GetMapping("/medicine/{id}")
	Medicine getMedicineById(@PathVariable("id") int medieId);

	
	@GetMapping("/medicine/data") 
	String getMedicineData();
	 
}
