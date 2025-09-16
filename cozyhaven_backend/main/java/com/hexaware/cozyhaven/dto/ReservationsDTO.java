package com.hexaware.cozyhaven.dto;

import java.time.LocalDate;

public class ReservationsDTO {
	
	    private Integer reservationId;
	    private LocalDate checkInDate;
	    private LocalDate checkOutDate;
	    private int numberOfAdults;
	    private int numberOfChildren;
	    private double totalPrice;
	    private String status; 
	    private String refundStatus;
	    
	    private Integer userId;
	    private Integer hotelId;
	    private Integer roomId;
	    
	    private Integer paymentId;
	    

	    public Integer getPaymentId() {
			return paymentId;
		}

		public void setPaymentId(Integer paymentId) {
			this.paymentId = paymentId;
		}

		public ReservationsDTO() {
	    }

	    public ReservationsDTO(Integer reservationId, LocalDate checkInDate, LocalDate checkOutDate,
				int numberOfAdults, int numberOfChildren, double totalPrice, String status, Integer userId,
				Integer hotelId, Integer roomId,Integer paymentId) {
			super();
			this.reservationId = reservationId;
			this.checkInDate = checkInDate;
			this.checkOutDate = checkOutDate;
			this.numberOfAdults = numberOfAdults;
			this.numberOfChildren = numberOfChildren;
			this.totalPrice = totalPrice;
			this.status = status;
			this.userId = userId;
			this.hotelId = hotelId;
			this.roomId = roomId;
			this.paymentId=paymentId;
		}

	    
	    
	    public ReservationsDTO(Integer reservationId, LocalDate checkInDate, LocalDate checkOutDate, int numberOfAdults,
				int numberOfChildren, double totalPrice, String status, String refundStatus, Integer userId,
				Integer hotelId, Integer roomId, Integer paymentId) {
			super();
			this.reservationId = reservationId;
			this.checkInDate = checkInDate;
			this.checkOutDate = checkOutDate;
			this.numberOfAdults = numberOfAdults;
			this.numberOfChildren = numberOfChildren;
			this.totalPrice = totalPrice;
			this.status = status;
			this.refundStatus = refundStatus;
			this.userId = userId;
			this.hotelId = hotelId;
			this.roomId = roomId;
			this.paymentId = paymentId;
		}

		public ReservationsDTO(Integer reservationId, LocalDate checkInDate, LocalDate checkOutDate, int numberOfAdults,  int numberOfChildren,
	    	    double totalPrice,String status, Integer userId, Integer hotelId,Integer roomId	) {
	    	    this.reservationId = reservationId;
	    	    this.checkInDate = checkInDate;
	    	    this.checkOutDate = checkOutDate;
	    	    this.numberOfAdults = numberOfAdults;
	    	    this.numberOfChildren = numberOfChildren;
	    	    this.totalPrice = totalPrice;
	    	    this.status = status;
	    	    this.userId = userId;
	    	    this.hotelId = hotelId;
	    	    this.roomId = roomId;
	    	}

	    


		public ReservationsDTO(LocalDate checkInDate, LocalDate checkOutDate, int numberOfAdults,
				int numberOfChildren, double totalPrice, String status, Integer userId, Integer hotelId,
				Integer roomId) {
			super();
			this.checkInDate = checkInDate;
			this.checkOutDate = checkOutDate;
			
			this.numberOfAdults = numberOfAdults;
			this.numberOfChildren = numberOfChildren;
			this.totalPrice = totalPrice;
			this.status = status;
			this.userId = userId;
			this.hotelId = hotelId;
			this.roomId = roomId;
		}
		
		

		public ReservationsDTO(LocalDate checkInDate, LocalDate checkOutDate, 
				int numberOfAdults, int numberOfChildren, double totalPrice, String status, Integer userId,
				Integer hotelId, Integer roomId, Integer paymentId) {
			super();
			
			this.checkInDate = checkInDate;
			this.checkOutDate = checkOutDate;
			
			this.numberOfAdults = numberOfAdults;
			this.numberOfChildren = numberOfChildren;
			this.totalPrice = totalPrice;
			this.status = status;
			this.userId = userId;
			this.hotelId = hotelId;
			this.roomId = roomId;
			this.paymentId = paymentId;
		}

		public Integer getReservationId() {
			return reservationId;
		}

		public void setReservationId(Integer reservationId) {
			this.reservationId = reservationId;
		}

		public LocalDate getCheckInDate() {
			return checkInDate;
		}

		public void setCheckInDate(LocalDate checkInDate) {
			this.checkInDate = checkInDate;
		}

		
		public String getRefundStatus() {
			return refundStatus;
		}

		public void setRefundStatus(String refundStatus) {
			this.refundStatus = refundStatus;
		}

		public LocalDate getCheckOutDate() {
			return checkOutDate;
		}

		public void setCheckOutDate(LocalDate checkOutDate) {
			this.checkOutDate = checkOutDate;
		}

		

		public int getNumberOfAdults() {
			return numberOfAdults;
		}

		public void setNumberOfAdults(int numberOfAdults) {
			this.numberOfAdults = numberOfAdults;
		}

		public int getNumberOfChildren() {
			return numberOfChildren;
		}

		public void setNumberOfChildren(int numberOfChildren) {
			this.numberOfChildren = numberOfChildren;
		}

		public double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	    
		public Integer getUserId() {
			return userId;
		}



		public void setUserId(Integer userId) {
			this.userId = userId;
		}



		public Integer getHotelId() {
			return hotelId;
		}



		public void setHotelId(Integer hotelId) {
			this.hotelId = hotelId;
		}



		public Integer getRoomId() {
			return roomId;
		}



		public void setRoomId(Integer roomId) {
			this.roomId = roomId;
		}


}
