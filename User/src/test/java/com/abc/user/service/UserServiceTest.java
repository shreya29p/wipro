package com.abc.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.user.entity.Customer;
import com.abc.user.entity.User;
import com.abc.user.exception.ResourceNotFoundException;
import com.abc.user.repository.UserRepository;
import com.abc.user.service.UserServiceImpl;

@SpringBootTest(properties="eureka.client.enabled=false")
public class UserServiceTest {
	
	@InjectMocks
	private UserServiceImpl customerService;
	
	@Mock
	private UserRepository userRepository;
	
	@Test
	public void testViewCustomerDetailsById() {
		User user = new User();
		Customer customer = new Customer();
		user.setUserId(1);
		user.setUsername("subbu");
		user.setPassword("1234");
		customer.setFirstName("subbu");
		customer.setLastName("Konatham");
		customer.setMobile("8688233328");
		customer.setEmail("subbu@gmail.com");
		customer.setAddress("vij");
		
		when(userRepository.findById(1)).thenReturn(Optional.of(customer));
		Customer actualObj = customerService.getCustomerById(1);
		assertEquals("subbu", actualObj.getFirstName());
	}
	
	@Test
	public void testViewCustomerDetailsByIdWithException() {
		when(userRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerById(1));
	}
	
	@Test
	public void testViewAllCustomers() {
		User user = new User();
		Customer customer = new Customer();
		user.setUserId(1);
		user.setUsername("subbu");
		user.setPassword("1234");
		customer.setFirstName("Subbu");
		customer.setLastName("Konatham");
		customer.setMobile("8688233328");
		customer.setEmail("subbu@gmail.com");
		customer.setAddress("vij");
		
		User user1 = new User();
		Customer customer1 = new Customer();
		user1.setUserId(2);
		user1.setUsername("sai");
		user1.setPassword("1234");
		customer1.setFirstName("sai");
		customer1.setLastName("bula");
		customer1.setMobile("7093624909");
		customer1.setEmail("sai@gmail.com");
		customer1.setAddress("vyu");
		
		User user2 = new User();
		Customer customer2 = new Customer();
		user2.setUserId(3);
		user2.setUsername("vasu");
		user2.setPassword("1234");
		customer2.setFirstName("vasu");
		customer2.setLastName("bula");
		customer2.setMobile("8897935936");
		customer2.setEmail("vasu@gmail.com");
		customer2.setAddress("vyu");
		
		List<User> users = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();
		users.add(user);
		users.add(user1);
		users.add(user2);
		customers.add(customer);
		customers.add(customer1);
		customers.add(customer2);
		 
		when(userRepository.findAll()).thenReturn(customers);
		
		List<Customer> customerList = customerService.getAllCustomers();
		assertEquals(3, customerList.size());
	}
	
	@Test
	public void testSaveCustomer() {
		User user3 = new User();
		Customer customer3 = new Customer();
		user3.setUserId(4);
		user3.setUsername("John");
		user3.setPassword("1234");
		customer3.setFirstName("John");
		customer3.setLastName("Doe");
		customer3.setMobile("1234567890");
		customer3.setEmail("john.doe@example.com");
		customer3.setAddress("123 Main St");
		
		when(userRepository.save(customer3)).thenReturn(customer3);
		
		Customer savedCustomer = customerService.saveCustomer(customer3);
		
		assertEquals("John", savedCustomer.getFirstName());
		assertEquals("Doe", savedCustomer.getLastName());
		assertEquals("1234567890", savedCustomer.getMobile());
		assertEquals("john.doe@example.com", savedCustomer.getEmail());
		assertEquals("123 Main St", savedCustomer.getAddress());
		
		verify(userRepository, times(1)).save(customer3);
	}
	
	@Test
	public void testSaveCustomerWithException() {
		User user3 = new User();
		Customer customer3 = new Customer();
		user3.setUserId(4);
		user3.setUsername("John");
		user3.setPassword("1234");
		customer3.setFirstName("John");
		customer3.setLastName("Doe");
		customer3.setMobile("1234567890");
		customer3.setEmail("john.doe@example.com");
		customer3.setAddress("123 Main St");
		
		when(userRepository.save(customer3)).thenThrow(new RuntimeException("Failed to save customer"));
		
		assertThrows(RuntimeException.class, () -> customerService.saveCustomer(customer3));
	}
	
	@Test
	public void testUpdateCustomer() {
	    // Create a customer object
	    Customer customer = new Customer();
	    customer.setUserId(1);
	    customer.setUsername("subbu");
		customer.setPassword("1234");
	    customer.setFirstName("Subbu");
	    customer.setLastName("Konatham");
	    customer.setMobile("8688233328");
	    customer.setEmail("subbu@gmail.com");
	    customer.setAddress("vij");

	    // Assume customer exists in the database
	    when(userRepository.findById(1)).thenReturn(Optional.of(customer));

	    // Perform the update
	    Customer updatedCustomer = customerService.updateCustomer(customer);

	    // Assertions
	    assertEquals("Subbu", updatedCustomer.getFirstName());
	    // Add more assertions as needed

	    // Verify that the save method was called once
	    verify(userRepository, times(1)).save(customer);
	}

	@Test
	public void testUpdateCustomerWithException() {
	    // Create a customer object
		
		User user = new User();
	    Customer customer = new Customer();
	    user.setUserId(1);
	    user.setUsername("subbu");
	    user.setPassword("1234");
	    customer.setFirstName("Subbu");
	    customer.setLastName("Konatham");
	    customer.setMobile("8688233328");
	    customer.setEmail("subbu@gmail.com");
	    customer.setAddress("vij");

	    // Assume customer does not exist in the database
	    when(userRepository.findById(1)).thenReturn(Optional.empty());

	    // Perform the update and expect an exception
	    assertThrows(ResourceNotFoundException.class, () -> customerService.updateCustomer(customer));
	}

	@Test
	public void testDeleteCustomerById() {
	    // Create a customer object
		User user = new User();
	    Customer customer = new Customer();
	    user.setUserId(1);
	    user.setUsername("subbu");
	    user.setPassword("1234");
	    customer.setFirstName("Subbu");
	    customer.setLastName("Konatham");
	    customer.setMobile("8688233328");
	    customer.setEmail("subbu@gmail.com");
	    customer.setAddress("vij");

	    // Assume customer with ID 1 exists in the database
	    when(userRepository.findById(1)).thenReturn(Optional.of(customer));

	    // Perform the deletion
	    customerService.deleteCustomer(1);

	    // Verify that the delete method was called once with the correct customer
	    verify(userRepository, times(1)).delete(customer);
	}

	@Test
	public void testDeleteCustomerByIdWithException() {
	    // Assume customer with ID 1 does not exist in the database
	    when(userRepository.findById(1)).thenReturn(Optional.empty());

	    // Perform the deletion and expect an exception
	    assertThrows(ResourceNotFoundException.class, () -> customerService.deleteCustomer(1));
	}

	

}
