package com.hexaware.cozyhaven.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="reservation_guests")
public class Reservation_Guests {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer guestId;

    @Column(name="guest_name")
    @Pattern(regexp = "^[A-Za-z]{1-30}$",message = "Guest Name should consist of only Letters and Spaces")
    private String guestName;

    @Pattern(regexp = "^//d{2}$",message = "Age must be a valid Number")
    private Integer age;

    @Pattern(regexp = "FEMALE|MALE|TRANSGENDER",message = "Gender should be one of FEMALE,MALE OR TRANSGENDER")
    private String gender;

    @Column(name = "is_primary_guest")
    private boolean isPrimaryGuest;
    
   
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="reservation_id")
    private Reservations reservations;
    
    
    
    
    

	public Reservation_Guests(String guestName, Integer age, String gender, boolean isPrimaryGuest,
			Reservations reservations) {
		
		
		this.guestName = guestName;
		this.age = age;
		this.gender = gender;
		this.isPrimaryGuest = isPrimaryGuest;
		this.reservations = reservations;
	}
	
	

	public Reservation_Guests(String guestName,Integer age,String gender,boolean isPrimaryGuest) {
		super();
		this.guestName = guestName;
		this.age = age;
		this.gender = gender;
		this.isPrimaryGuest = isPrimaryGuest;
	}



	public int getGuestId() {
		return guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isPrimaryGuest() {
		return isPrimaryGuest;
	}

	public void setPrimaryGuest(boolean isPrimaryGuest) {
		this.isPrimaryGuest = isPrimaryGuest;
	}

	public Reservations getReservations() {
		return reservations;
	}

	public void setReservations(Reservations reservations) {
		this.reservations = reservations;
	}
    
    

}
