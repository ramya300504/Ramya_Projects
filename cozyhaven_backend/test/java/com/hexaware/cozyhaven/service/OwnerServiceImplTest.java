package com.hexaware.cozyhaven.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.cozyhaven.dto.HotelsDTO;
import com.hexaware.cozyhaven.dto.RoomsDTO;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.exceptions.InvalidRoomIdException;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class OwnerServiceImplTest {


	@Autowired
	IOwnerService ownerService;
	
	@Autowired
	IHotelService hotelService;
	
	@Autowired
	IAdminService adminService;
	
	@Autowired
	IRoomService roomService;

	


	@Test
	void testAddRoom() throws InvalidHotelIdException {
	    HotelsDTO hotelDto = new HotelsDTO("The Grand Tamilnadu Inn","Chennai", "123 Marina Beach Road, Chennai, Tamil Nadu 600041", "9876543210",
	        "A luxurious beachfront hotel", "",   "" );
	    HotelsDTO addedHotel = adminService.addHotel(hotelDto);

	    RoomsDTO roomDto = new RoomsDTO(0,"Deluxe", "SINGLE", 3000.0, 2, true, true, "",addedHotel.getHotelId() );

	    RoomsDTO addedRoom = ownerService.addRoom(roomDto);
	    assertTrue(addedRoom.isAvailable());
	    assertEquals("SINGLE", addedRoom.getBedType());
	}

	@Test
	void testUpdateRoom() throws InvalidRoomIdException, InvalidHotelIdException {
	 
	    HotelsDTO hotelDto = new HotelsDTO("The Grand Tamilnadu Inn","Chennai", "123 Marina Beach Road, Chennai, Tamil Nadu 600041", "9876543210",
	    		"A luxurious beachfront hotel", "",   "" );
	    HotelsDTO addedHotel = adminService.addHotel(hotelDto);

	    RoomsDTO roomDto = new RoomsDTO(0, "Standard", "DOUBLE", 4500.0, 2, true, false, "", addedHotel.getHotelId());
	    RoomsDTO savedRoom = ownerService.addRoom(roomDto);

	    RoomsDTO updatedRoom = new RoomsDTO(0, "Standard Updated", "DOUBLE", 5000.0, 2, false, true, "", addedHotel.getHotelId());
	    RoomsDTO result = ownerService.updateRoom(savedRoom.getRoomId(), updatedRoom);

	    assertEquals("DOUBLE", result.getBedType());
	    assertTrue(result.isAvailable());
	    assertEquals(5000.0, result.getBaseFare());
	}


	@Test
	void testDeleteRoom() throws InvalidRoomIdException, InvalidHotelIdException {
	    HotelsDTO hotelDto = new HotelsDTO(
	        "The Grand Tamilnadu Inn", 
	        "Chennai", 
	        "123 Marina Beach Road, Chennai, Tamil Nadu 600041", 
	        "9876543210", 
	        "A luxurious beachfront hotel", 
	        "", 
	        ""
	    );
	    HotelsDTO addedHotel = adminService.addHotel(hotelDto);

	    RoomsDTO roomDto = new RoomsDTO(0, "Standard", "DOUBLE", 4500.0, 2, true, true, "", addedHotel.getHotelId());
	    RoomsDTO savedRoom = ownerService.addRoom(roomDto);

	    String message = ownerService.deleteRoom(savedRoom.getRoomId());
	    assertEquals("Room Deleted Successfully", message);
	}

	



}
