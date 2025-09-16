package com.hexaware.cozyhaven.service;

import java.util.List;

import com.hexaware.cozyhaven.dto.ReviewDTO;
import com.hexaware.cozyhaven.entity.Reviews;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.exceptions.ReviewIDNotFoundException;

public interface IReviewService {
	
	 Reviews addReview(ReviewDTO reviewdto);
	 
	 Reviews updateReview(Integer reviewId,ReviewDTO reviewdto) throws InvalidHotelIdException, ReviewIDNotFoundException;
	 
	 String deleteReview(int reviewId);
	 
	 List<Reviews> getReviewsForHotel(int hotelId);

	Double getRatingByHotel(int hotelId);

}
