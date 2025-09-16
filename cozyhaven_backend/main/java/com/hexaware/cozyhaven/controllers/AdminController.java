package com.hexaware.cozyhaven.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.cozyhaven.dto.HotelsDTO;
import com.hexaware.cozyhaven.dto.ReservationsDTO;
import com.hexaware.cozyhaven.dto.UserDTO;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.exceptions.InvalidReservationIdException;
import com.hexaware.cozyhaven.exceptions.UserNotFoundException;
import com.hexaware.cozyhaven.service.IAdminService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:4200")
public class AdminController {

	@Autowired
	IAdminService adminService;
	
	
	@GetMapping("/getuserbyid/{userId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public UserDTO getUserById(@PathVariable Integer userId) throws UserNotFoundException {
		
		return adminService.getUserById(userId);
	}
	
	@DeleteMapping("/deleteuserbyid/{userId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteUser(@PathVariable Integer userId) {
	
		return adminService.deleteUser(userId);
	}
	
	@GetMapping("/getallusers")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<UserDTO> getallUsers(){
		
		return adminService.getallUsers();
	}
	

	@PostMapping("/addHotel")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public HotelsDTO addHotel(@RequestBody @Valid HotelsDTO hoteldto) {
		
		return adminService.addHotel(hoteldto);
		
	}
	
	@GetMapping("/getallhotels")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<HotelsDTO> getallHotels() {
		
		return adminService.getAllHotels();
		
	}
	
	@DeleteMapping("/deleteHotel/{hotelId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void deleteHotel(@PathVariable Integer hotelId) throws InvalidHotelIdException {
		
		adminService.deleteHotel(hotelId);
    }
	
	@PutMapping("/updateHotel/{hotelId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public HotelsDTO updateHotel(@PathVariable Integer hotelId,@RequestBody @Valid HotelsDTO hoteldto) throws InvalidHotelIdException {
		

		return adminService.updateHotel(hotelId,hoteldto);
	}
	
	@GetMapping("/getallreservations")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<ReservationsDTO> getAllReservations(){
		
		return adminService.getAllReservations();
	}
	
	@GetMapping("/getreservationbyid/{reservationId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ReservationsDTO getReservationById(@PathVariable Integer reservationId) throws InvalidReservationIdException {
		
		return adminService.getReservationById(reservationId);
	}
	
	@GetMapping("/getallowners")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<UserDTO> getallOwners(){
		
		return adminService.getallOwners();
	}
	

	@GetMapping("/getownerbyid/{ownerId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public UserDTO getOwnerById(@PathVariable Integer ownerId) throws UserNotFoundException {
		
		return adminService.getOwnerById(ownerId);
	}
	
	
	
	@GetMapping("/counttotalusers")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public long countTotalUsers() {
		
		return adminService.countTotalUsers();
	}
	
	@GetMapping("counttotalbookings")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public long countTotalBookings() {
		
		return adminService.countTotalBookings();
	}
	
	@GetMapping("gettotalrevenue")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public double getTotalRevenue() {
		
		return adminService.getTotalRevenue();
	}
	
	@DeleteMapping("/deleteAll")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void deleteAllHotels() {
		
		adminService.deleteAllHotels();
	}
	

	
	
	
	
}
