package com.hexaware.cozyhaven.service;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.cozyhaven.dto.HotelsDTO;
import com.hexaware.cozyhaven.dto.PaymentDTO;

import com.hexaware.cozyhaven.dto.ReservationsDTO;
import com.hexaware.cozyhaven.dto.RoomsDTO;

import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.exceptions.InvalidReservationIdException;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class BookingServiceImplTest {

	@Autowired
	IBookingService bookingService;
	
	@Autowired
	IOwnerService ownerService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IAdminService adminService;
	
	@Test
	void testCreateReservation() throws InvalidHotelIdException {
	    HotelsDTO hotelDto = new HotelsDTO("Palm Leaf Hotel", "Mumbai", "123 Street", "9876543210", "Nice stay", "", "");
	    HotelsDTO hotel = adminService.addHotel(hotelDto);

	    RoomsDTO roomDto = new RoomsDTO(0, "Deluxe", "DOUBLE", 3000.0, 2, true, true, "", hotel.getHotelId());
	    RoomsDTO room = ownerService.addRoom(roomDto);

	  

	    ReservationsDTO reservationDto = new ReservationsDTO(
	        LocalDate.now().plusDays(1),
	        LocalDate.now().plusDays(3),
	        2,
	        1,
	        9000.0,
	        "CONFIRMED",
	        102,
	        hotel.getHotelId(),
	        room.getRoomId(),
	        101 // Dummy payment ID
	    );

	    ReservationsDTO created = bookingService.createReservation(reservationDto);
	    assertNotNull(created.getReservationId());
	    assertEquals("CONFIRMED", created.getStatus());
	}


	@Test
	void testCancelReservation() throws InvalidReservationIdException, InvalidHotelIdException {
		
	    HotelsDTO hotelDto = new HotelsDTO("Palm Leaf Hotel", "Mumbai", "123 Street", "9876543210", "Nice stay", "", "");
	    HotelsDTO hotel = adminService.addHotel(hotelDto);

	    RoomsDTO roomDto = new RoomsDTO(0, "Deluxe", "DOUBLE", 3000.0, 2, true, true, "", hotel.getHotelId());
	    RoomsDTO room = ownerService.addRoom(roomDto);

	    ReservationsDTO reservationDto = new ReservationsDTO(
		        LocalDate.now().plusDays(1),
		        LocalDate.now().plusDays(3),
		        2,
		        1,
		        9000.0,
		        "CONFIRMED",
		        102,
		        hotel.getHotelId(),
		        room.getRoomId(),
		        101 // Dummy payment ID
		    );

		    ReservationsDTO created = bookingService.createReservation(reservationDto);
		
		
		String result=bookingService.cancelReservation(created.getReservationId());
		
		assertEquals("Reservation Cancelled", result);
  
		
	}

	
	
	
	
	@Test
	void testaddPaymentByReservationService() {
		
		PaymentDTO newDTO = new PaymentDTO(null, LocalDate.now(), 4500.75, "UPI", "SUCCESS");
	    PaymentDTO savedDTO = bookingService.addPaymentByReservationService(newDTO);
	    
	    assertEquals("UPI", savedDTO.getPaymentMethod());
	}
	

	
	
}
