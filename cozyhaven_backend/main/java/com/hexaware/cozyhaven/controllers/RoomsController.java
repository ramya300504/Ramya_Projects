package com.hexaware.cozyhaven.controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.cozyhaven.dto.RoomsDTO;
import com.hexaware.cozyhaven.entity.Rooms;
import com.hexaware.cozyhaven.exceptions.InvalidBedTypeException;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.exceptions.InvalidRoomIdException;
import com.hexaware.cozyhaven.service.IRoomService;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/rooms")
public class RoomsController {
	
	@Autowired
	IRoomService roomService;
	
	private static final Logger log = LoggerFactory.getLogger(RoomsController.class);
	
	@GetMapping("/getroomsbyid/{roomsId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Rooms getRoomsById(@PathVariable Integer roomId) throws InvalidRoomIdException {
		
		log.info("Getting rooms by ID using GET Method");
		return roomService.getRoomsById(roomId);
		
	}
	
	@GetMapping("/getroomsbyhotel/{hotelId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<RoomsDTO> getRoomsByHotel(@PathVariable Integer hotelId) throws InvalidHotelIdException{
		
		log.info("Getting rooms by Hotel using GET Method");
		return roomService.getRoomsByHotel(hotelId);
	}

	@GetMapping("/getavailablerooms/{hotelId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<RoomsDTO> getAvailableRooms(@PathVariable Integer hotelId) throws InvalidHotelIdException{
		
		log.info("Getting  Available rooms by Hotel ID using GET Method");
		return roomService.getAvailableRooms(hotelId);
	}
	
	@GetMapping("/getacrooms/{hotelId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<RoomsDTO> getAcRooms(@PathVariable Integer hotelId) throws InvalidHotelIdException{
		
		log.info("Getting AC rooms by ID using GET Method");
		return roomService.getAcRooms(hotelId);
		
	}
	
	@GetMapping("/getroomslessthanbasefare/{baseFare}/{hotelId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<RoomsDTO> getRoomsLessThanBaseFare(@PathVariable double baseFare,@PathVariable Integer hotelId){
		
		log.info("Getting rooms less than Base Fare using GET Method");
		return roomService.getRoomsLessThanBaseFare(baseFare,hotelId);
	}
	
	
	
	@GetMapping("/getroomsbybedtype/{bedType}/{hotelId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<RoomsDTO> getRoomsByBedType(@PathVariable String bedType,@PathVariable Integer hotelId) throws InvalidBedTypeException{
		
		log.info("Getting rooms by Bed Type using GET Method");
		return roomService.getRoomsByBedType(bedType,hotelId);
	}
	
	
	
}
