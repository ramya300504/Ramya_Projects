package com.hexaware.cozyhaven.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="reservations")
public class Reservations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reservationId;
	
	@Column(name="check_in_date")
	@NotNull(message = "Check-in date is required")
    @FutureOrPresent(message = "Check-in date should not be PAst")
	private LocalDate checkInDate;
	
	@Column(name="check_out_date")
	@NotNull(message = "Check-out date is required")
    @Future(message = "Check-out date must be future")
	private LocalDate checkOutDate;
	
	@Column(name="number_of_adults")
	@Min(value = 1, message = "At least one adult is required")
    private int numberOfAdults;
	
	@Column(name="number_of_children")
    private int numberOfChildren;
	
	@Column(name="total_price")
    private double totalPrice;
	
	@Pattern(regexp = "BOOKED|CANCELLED|REFUNDED", message = "Status must be BOOKED, CANCELLED, REFUNDED")
	private String  status;

	
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_id")
    private User user;  
    
	
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="hotel_id")
    private Hotels hotels; 
    
	
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="room_id")
    private Rooms rooms;
    
    @OneToOne(mappedBy = "reservations", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Refund refund;
    
    @Column(name = "payment_id")
    private Integer paymentId;//foreign key reference for payment microservice
    
    
    


	@OneToMany(mappedBy = "reservations",cascade =CascadeType.ALL)
    @JsonManagedReference
    private List<Reservation_Guests> reservation_Guests =new ArrayList<>();
    
    
	public Reservations() {
		
	}


	public Reservations(LocalDate checkInDate, LocalDate checkOutDate, 
			int numberOfAdults, int numberOfChildren, double totalPrice, String status,
			User user, Hotels hotels, Rooms rooms,Integer paymentId) {
		
		
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		
		this.numberOfAdults = numberOfAdults;
		this.numberOfChildren = numberOfChildren;
		this.totalPrice = totalPrice;
		this.status = status;
		this.user = user;
		this.hotels = hotels;
		this.rooms = rooms;
		this.paymentId=paymentId;
	}
	
	


	public int getReservationId() {
		return reservationId;
	}


	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}


	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public Integer getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
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


	public Refund getRefund() {
		return refund;
	}


	public void setRefund(Refund refund) {
		this.refund=refund;
	}
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Hotels getHotel() {
		return hotels;
	}


	public void setHotel(Hotels hotels) {
		this.hotels = hotels;
	}


	public Rooms getRoom() {
		return rooms;
	}


	public void setRoom(Rooms rooms) {
		this.rooms = rooms;
	}
	
	
	
	

}
