package com.hexaware.cozyhaven.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.cozyhaven.dto.ReviewDTO;
import com.hexaware.cozyhaven.entity.Hotels;
import com.hexaware.cozyhaven.entity.Reviews;
import com.hexaware.cozyhaven.entity.User;import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.exceptions.ReviewIDNotFoundException;
import com.hexaware.cozyhaven.repository.HotelsRepository;
import com.hexaware.cozyhaven.repository.ReviewRepository;
import com.hexaware.cozyhaven.repository.UserRepository;
/*
 * review service provides option for user to add new reviews to the hotels they have booked
 */
@Service
public class ReviewServiceImpl implements IReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	HotelsRepository hotelsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	

	@Override
	public Reviews addReview(ReviewDTO reviewdto) {
		
		Reviews reviews=new Reviews();
		reviews.setRating(reviewdto.getRating());
		reviews.setComment(reviewdto.getComment());
		
		//getting hotel object by hotel id to reduce difficulties in input
		Hotels hotels=hotelsRepository.findById(reviewdto.getHotelId()).orElse(null);
        reviews.setHotels(hotels);
        
        return reviewRepository.save(reviews);
		
		
	}

	@Override
	public Reviews updateReview(Integer reviewId,ReviewDTO reviewdto) throws InvalidHotelIdException, ReviewIDNotFoundException {
		

		Reviews reviews=reviewRepository.findById(reviewId).orElseThrow(()->new ReviewIDNotFoundException());
		reviews.setRating(reviewdto.getRating());
		reviews.setComment(reviewdto.getComment());
		
		//getting hotel object by hotel id to reduce difficulties in input
		Hotels hotels=hotelsRepository.findById(reviewdto.getHotelId()).orElseThrow(()->new InvalidHotelIdException());
        reviews.setHotels(hotels);
       
        return reviewRepository.save(reviews);
		
		
		
	}
	
	@Override
	public Double getRatingByHotel(int hotelId) {
		
	    return reviewRepository.getRatingByHotelId(hotelId);
	}

	@Override
	public String deleteReview(int reviewId) {

		reviewRepository.deleteById(reviewId);
		
		return "Review Deleted Successfully";

	}

	@Override
	public List<Reviews> getReviewsForHotel(int hotelId) {
		
		return reviewRepository.findByHotels(hotelId);
	}

}
