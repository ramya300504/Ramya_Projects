  package com.hexaware.cozyhaven.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.cozyhaven.dto.HotelsDTO;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class HotelServiceImplTest {
	
	@Autowired
	IHotelService hotelService;
	
	@Autowired
	IOwnerService ownerService;
	
	@Autowired
	IAdminService adminService;



	
	@Test
	void testSearchHotels() {
	    HotelsDTO hotelDto = new HotelsDTO("Palm Leaf Hotel", "Mumbai", "123 Street", "9876543210", "Nice stay", "", "");
	    HotelsDTO addedHotel = adminService.addHotel(hotelDto);

	    LocalDate checkInDate = LocalDate.now().plusDays(1);
	    LocalDate checkOutDate = checkInDate.plusDays(2);
	    
	    List<HotelsDTO> result = hotelService.searchHotels(
	        "Mumbai", checkInDate, checkOutDate, 1, 2, 0
	    );

	    assertFalse(result.isEmpty());
	    assertEquals("Mumbai", result.get(0).getLocation());
	}
	
	@Test
	void testGetAllLocations() {
	    HotelsDTO hotelDto = new HotelsDTO("Palm Leaf Hotel", "Mumbai", "123 Street", "9876543210", "Nice stay", "", "");
	    adminService.addHotel(hotelDto);

	    List<String> locations = hotelService.getAllLocations();
	    assertTrue(locations.contains("Mumbai"));
	}


}
