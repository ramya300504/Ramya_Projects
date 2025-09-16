package com.hexaware.cozyhaven.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.cozyhaven.dto.HotelsDTO;
import com.hexaware.cozyhaven.dto.RoomsDTO;

import com.hexaware.cozyhaven.exceptions.InvalidBedTypeException;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class RoomServiceImplTest {


	@Autowired
	IRoomService roomService;
	
	@Autowired
	IOwnerService ownerService;
	
	@Autowired
	IAdminService adminService;

	 
	
	@Test
	void testGetRoomsByHotel() throws InvalidHotelIdException {
	    HotelsDTO hotelDto = new HotelsDTO("Palm Leaf Hotel", "Mumbai", "123 Street", "9876543210", "Nice stay", "", "");
	    HotelsDTO hotel = adminService.addHotel(hotelDto);

	    RoomsDTO roomDto = new RoomsDTO(0,"Deluxe", "SINGLE", 3000.0, 2, true, true, "",hotel.getHotelId() );
	    ownerService.addRoom(roomDto);

	    List<RoomsDTO> rooms = roomService.getRoomsByHotel(hotel.getHotelId());
	    assertFalse(rooms.isEmpty());
	}

	
	
	@Test
	void testGetAvailableRooms() throws InvalidHotelIdException {
	    HotelsDTO hotelDto = new HotelsDTO("Beach Breeze", "Goa", "456 Beach Road", "9123456789", "Relax by the sea", "", "");
	    HotelsDTO hotel = adminService.addHotel(hotelDto);

	    RoomsDTO roomDto = new RoomsDTO(0, "Suite", "DOUBLE", 3500.0, 2, true, true, "", hotel.getHotelId());
	    ownerService.addRoom(roomDto);

	    List<RoomsDTO> rooms = roomService.getAvailableRooms(hotel.getHotelId());
	    assertFalse(rooms.isEmpty());
	    assertTrue(rooms.get(0).isAvailable());
	}


	
	@Test
	void testGetAcRooms() throws InvalidHotelIdException {
	    HotelsDTO hotelDto = new HotelsDTO("Cool Stay", "Shimla", "789 Hill Top", "9876543211", "Chilly retreat", "", "");
	    HotelsDTO hotel = adminService.addHotel(hotelDto);

	    RoomsDTO roomDto = new RoomsDTO(0, "AC Suite", "KING", 4500.0, 3, true, true, "", hotel.getHotelId());
	    ownerService.addRoom(roomDto);

	    List<RoomsDTO> acRooms = roomService.getAcRooms(hotel.getHotelId());
	    assertFalse(acRooms.isEmpty());
	    assertTrue(acRooms.get(0).isAc());
	}



	@Test
	void testGetRoomsLessThanBaseFare() throws InvalidHotelIdException {
	    HotelsDTO hotelDto = new HotelsDTO("Budget Stay", "Chennai", "101 Main Street", "9999999999", "Affordable option", "", "");
	    HotelsDTO hotel = adminService.addHotel(hotelDto);

	    RoomsDTO roomDto = new RoomsDTO(0, "Standard", "DOUBLE", 2800.0, 2, false, true, "", hotel.getHotelId());
	    ownerService.addRoom(roomDto);

	    List<RoomsDTO> rooms = roomService.getRoomsLessThanBaseFare(3000, hotel.getHotelId());
	    assertFalse(rooms.isEmpty());
	    assertTrue(rooms.get(0).getBaseFare() < 3000);
	}

	

	@Test
	void testGetRoomsByBedType() throws InvalidHotelIdException, InvalidBedTypeException {
	    HotelsDTO hotelDto = new HotelsDTO("Luxury Inn", "Bangalore", "222 Tech Park", "8888888888", "Business class", "", "");
	    HotelsDTO hotel = adminService.addHotel(hotelDto);

	    RoomsDTO roomDto = new RoomsDTO(0, "Executive", "SINGLE", 3200.0, 1, true, true, "", hotel.getHotelId());
	    ownerService.addRoom(roomDto);

	    List<RoomsDTO> rooms = roomService.getRoomsByBedType("SINGLE", hotel.getHotelId());
	    assertFalse(rooms.isEmpty());
	    assertEquals("SINGLE", rooms.get(0).getBedType());
	}



}
