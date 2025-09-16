package com.hexaware.cozyhaven.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.cozyhaven.dto.HotelsDTO;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;

import jakarta.transaction.Transactional;
@SpringBootTest
@Transactional
class AdminServiceImplTest {

	@Autowired
	IAdminService adminService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IBookingService bookingService;
	

	@Test
	void testAddHotel() {
	    HotelsDTO hotelDto = new HotelsDTO("Palm Leaf Hotel", "Mumbai", "123 Street", "9876543210", "Nice stay", "", "");
	    HotelsDTO addedHotel = adminService.addHotel(hotelDto);

	    assertNotNull(addedHotel.getHotelId());
	    assertEquals("Palm Leaf Hotel", addedHotel.getHotelName());
	}
	
	@Test
	void testUpdateHotel() throws InvalidHotelIdException{
	    HotelsDTO hotelDto = new HotelsDTO("Palm Leaf Hotel", "Mumbai", "123 Street", "9876543210", "Nice stay", "", "");
	    HotelsDTO addedHotel = adminService.addHotel(hotelDto);

	    HotelsDTO updatedDto = new HotelsDTO("Updated Hotel", "Mumbai", "New Address", "9999999999", "Updated description", "", "");
	    HotelsDTO updatedHotel = adminService.updateHotel(addedHotel.getHotelId(), updatedDto);

	    assertEquals("Updated Hotel", updatedHotel.getHotelName());
	    assertEquals("9999999999", updatedHotel.getContactNumber());
	}

	@Test
	void testDeleteHotel() throws InvalidHotelIdException {
	    HotelsDTO hotelDto = new HotelsDTO("Delete Hotel", "Goa", "Beach Road", "8888888888", "Sea view", "", "");
	    HotelsDTO addedHotel = adminService.addHotel(hotelDto);

	    String result = adminService.deleteHotel(addedHotel.getHotelId());
	    assertEquals("Hotel Deleted Successfully", result);
	}
	
	@Test
	void testGetAllHotels() {
	    HotelsDTO hotelDto = new HotelsDTO("Hotel A", "Delhi", "Address A", "7777777777", "Good stay", "", "");
	    adminService.addHotel(hotelDto);

	    List<HotelsDTO> hotels = adminService.getAllHotels();
	    assertFalse(hotels.isEmpty());
	}


	@Test
	void testCountTotalUsers() {
		long count = adminService.countTotalUsers();
        assertNotNull(count);
	}

	@Test
	void testCountTotalBookings()  {
		
		long count=adminService.countTotalBookings();
		assertNotNull(count);
	}

	@Test
	void testGetTotalRevenue() {
		
		 double revenue = adminService.getTotalRevenue();
	     assertNotNull(revenue);
	}

}
