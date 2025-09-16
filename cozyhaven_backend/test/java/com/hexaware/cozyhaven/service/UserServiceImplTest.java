package com.hexaware.cozyhaven.service;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.cozyhaven.dto.UserDTO;

import com.hexaware.cozyhaven.entity.User;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class UserServiceImplTest {

	@Autowired
	UserServiceImpl userService;
	
	@Test
	void testCreateUser() {
	    
		UserDTO userDTO=new UserDTO("Ramya", "Suresh", "ramya@example.com", "password123", "123 Main Street", "9876543210", "USER");

	    User user = userService.createUser(userDTO);

	    assertEquals("Ramya", user.getFirstName());
	}

	@Test
	void testcalculateTotalPrice() {
		
		String bedType = "DOUBLE";
	    double baseFare = 3000.0;
	    int numberOfAdults = 1;
	    int numberOfChildren = 1; // Total = 2, allowed = 2 for DOUBLE bed

	    double expectedPrice = 3000.0;
	    double actualPrice = userService.calculateTotalPrice(bedType, baseFare, numberOfAdults, numberOfChildren);

	    assertEquals(expectedPrice, actualPrice);
		
	}


}
