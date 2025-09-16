/**
 * 
 */
package com.hexaware.cozyhaven.entity;

import java.util.ArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/*author: Ramya R
 * description: This project focuses on hotel booking by user and other main modules are admin and hotelwowner
 *
 */
@Entity
@Table(name="User")
public class User {
	
	public enum Role{
		USER,
		ADMIN,
		HOTELOWNER
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@Column(name = "first_name")
	@Pattern(regexp = "^[A-Za-z]{1,30}$",message = "First Name should consist of only Letters and Spaces")
	private String firstName;

	@Column(name = "last_name")
	@Pattern(regexp = "^[A-Za-z]{1,30}$",message = "Last Name should consist of only Letters and Spaces")
	private String lastName;

	@Email(message = "Example format: ramya@gmail.com")
	@NotNull(message = "Email Should not be Null")
	private String email;

	@Size(min = 6)
    @NotNull(message = "Password should not be null")
	private String password;



	@Column(name = "contact_number")
	@Pattern(regexp = "^\\d{10}$",message = "Contact Number should conist od only Digits of size 10")
	private String contactNumber;

	
	private String address;

	@Enumerated(EnumType.STRING) 
	private Role role;

	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Reservations> reservations = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Reviews> reviews = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Refund> refunds;
	
	
	    
	
	public User() {}
	
	

	public User(String firstName, String lastName, String email, String password, String contactNumber, String address,
			Role role, List<Reservations> reservations, List<Reviews> reviews, List<Refund> refunds) {
		
	
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.contactNumber = contactNumber;
		this.address = address;
		this.role = role;
		this.reservations = reservations;
		this.reviews = reviews;
		this.refunds = refunds;

	}
	
	



	public User(Integer userId, String firstName, String lastName, String email,String password,String contactNumber,String address, Role role) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.contactNumber = contactNumber;
		this.address = address;
		this.role = role;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
	
	
	
	

}
