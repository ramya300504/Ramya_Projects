package com.hexaware.cozyhaven.dto;

public class ReviewDTO {
	
	    private Integer reviewId;
	    private int rating;
	    private String comment;
	    private Integer hotelId;
	    
	    
	    
		public ReviewDTO() {
			super();
		}
		public ReviewDTO(Integer reviewId, int rating, String comment, Integer hotelId) {
			super();
			this.reviewId = reviewId;
			this.rating = rating;
			this.comment = comment;
			this.hotelId = hotelId;
		}
		
		
		public ReviewDTO(int rating, String comment, Integer hotelId) {
			super();
			this.rating = rating;
			this.comment = comment;
			this.hotelId = hotelId;
		}
		public Integer getReviewId() {
			return reviewId;
		}
		public void setReviewId(Integer reviewId) {
			this.reviewId = reviewId;
		}
		public int getRating() {
			return rating;
		}
		public void setRating(int rating) {
			this.rating = rating;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
	
		public Integer getHotelId() {
			return hotelId;
		}
		public void setHotelId(Integer hotelId) {
			this.hotelId = hotelId;
		}
	    
	    


}
