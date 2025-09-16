package com.hexaware.cozyhaven.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.cozyhaven.dto.ReviewDTO;
import com.hexaware.cozyhaven.entity.Reviews;

import jakarta.transaction.Transactional;
@SpringBootTest
@Transactional
class ReviewServiceImplTest {
	
	@Autowired
	IReviewService reviewService;
	
	@Autowired
	IOwnerService ownerService;
	
	@Test
	void testAddReview() {
		
		ReviewDTO reviewDTO=new ReviewDTO(4, "Good Customer Service", 1);
		Reviews savedReview=reviewService.addReview(reviewDTO);
		assertEquals(4, savedReview.getRating());
		
	}



}
