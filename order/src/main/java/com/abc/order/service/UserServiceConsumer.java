package com.abc.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.order.model.User;

@FeignClient(name = "user", fallback = UserServiceFallback.class)
public interface UserServiceConsumer {

    @GetMapping("/customer/{id}")
    User getUserDetailsById(@PathVariable("id") int userId);
}
