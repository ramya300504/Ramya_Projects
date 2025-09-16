package com.hexaware.cozyhaven.controllers;

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

import com.hexaware.cozyhaven.dto.PaymentDTO;
import com.hexaware.cozyhaven.dto.ReservationPaymentVO;
import com.hexaware.cozyhaven.dto.ReservationsDTO;
import com.hexaware.cozyhaven.exceptions.InvalidReservationIdException;
import com.hexaware.cozyhaven.service.IBookingService;

@RestController
@RequestMapping("/booking")
@CrossOrigin("http://localhost:4200")
public class BookingController {

	@Autowired
	IBookingService bookingService;
	
	@PostMapping("/create")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ReservationsDTO createReservation(@RequestBody ReservationsDTO reservationsdto)  {
		
		return bookingService.createReservation(reservationsdto);
	}
	
	@PutMapping("/cancel/{reservationId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public void cancelReservation(@PathVariable Integer reservationId) throws InvalidReservationIdException {
		
		bookingService.cancelReservation(reservationId);
	}
	
	
	//microservice method controllers
	
	@PutMapping("/updatepayment/{paymentId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String updatePaymentByReservationService(@PathVariable Integer paymentId,@RequestBody PaymentDTO paymentDTO) {
		
		return bookingService.updatePaymentByReservationService(paymentId, paymentDTO);
		
	}
	
	@GetMapping("getreservation-payment/{reservationId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ReservationPaymentVO getReservationPaymentByResID(@PathVariable Integer reservationId) throws InvalidReservationIdException {
		
		return bookingService.getReservationPaymentByResID(reservationId);
	}
	
	@PostMapping("/addpayment")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public PaymentDTO addPaymentByReservationService(@RequestBody PaymentDTO paymentDTO) {
		
		return bookingService.addPaymentByReservationService(paymentDTO);
	}
	
	@DeleteMapping("/deletepayment/{paymentId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String deletePaymentByReservationService(@PathVariable  Integer paymentId) {
		
		return bookingService.deletePaymentByReservationService(paymentId);
	}
}
