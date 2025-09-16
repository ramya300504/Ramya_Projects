package com.hexaware.cozyhaven.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.cozyhaven.dto.ReviewDTO;
import com.hexaware.cozyhaven.entity.Reviews;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.exceptions.ReviewIDNotFoundException;
import com.hexaware.cozyhaven.service.IReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
	
	@Autowired
	IReviewService reviewService;

	@PostMapping("/addReview")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Reviews addReview(@RequestBody ReviewDTO reviewdto) {
		
	 return	reviewService.addReview(reviewdto);
	}
	
	@PutMapping("/updateReview/{reviewId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Reviews updateReview(@PathVariable Integer reviewId,@RequestBody ReviewDTO reviewdto) throws InvalidHotelIdException, ReviewIDNotFoundException{
		
		return reviewService.updateReview(reviewId,reviewdto);
		
	}
	
	@GetMapping("/getratingbyhotel/{hotelId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Double getRatingByHotel(@PathVariable("hotelId") Integer hotelId) {
		
		return reviewService.getRatingByHotel(hotelId);
	}
	
	@DeleteMapping("/deleteReview/{reviewId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public void deleteReview(int reviewId) {
		
		 reviewService.deleteReview(reviewId);
		
	}
	
	@GetMapping("/getReviews/{hotelid}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<Reviews> getReviewsForHotel(@PathVariable int hotelId){
		
	    return	reviewService.getReviewsForHotel(hotelId);
	}
}
