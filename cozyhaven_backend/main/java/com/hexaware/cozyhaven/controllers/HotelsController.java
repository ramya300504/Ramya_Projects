package com.hexaware.cozyhaven.controllers;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.cozyhaven.dto.HotelsDTO;
import com.hexaware.cozyhaven.entity.Hotels;
import com.hexaware.cozyhaven.exceptions.HotelNamenotFoundException;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.service.IHotelService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/hotels")
public class HotelsController {
	
	@Autowired
	IHotelService hotelService;
	
	 private static final Logger log = LoggerFactory.getLogger(HotelsController.class);
	
	@GetMapping("/getByLocation/{location}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<Hotels> getHotelsByLocation(@PathVariable String location)
	{
		
		return hotelService.getHotelsByLocation(location);
	}
	
	
	@GetMapping("/locations")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<String> getAllLocations() {
	    return hotelService.getAllLocations();
	}
	
	@GetMapping("/getByName/{hotelName}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Hotels getHotelByName(@PathVariable  String hotelName) throws HotelNamenotFoundException 
	{
		
		return hotelService.getHotelByName(hotelName);
	}

	
	@GetMapping("/getById/{hotelId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Hotels getHotelById( @PathVariable Integer hotelId) throws InvalidHotelIdException 
	{
		
		return hotelService.getHotelById(hotelId);
	}
	
	@GetMapping("/searchAvailable/{location}/{checkInDate}/{checkOutDate}/{noOfRooms}/{noOfAdults}/{noOfChildren}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
    public List<HotelsDTO> searchHotels(@PathVariable String location,
                                    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
                                    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
                                    @PathVariable int noOfRooms,
                                    @PathVariable int noOfAdults,
                                    @PathVariable int noOfChildren) {
		    
		    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    System.out.println("User: " + auth.getName());
		    System.out.println("Authorities: " + auth.getAuthorities());
		    log.info("Reached searchAvailable endpoint");
		log.info("searching hotels");
        return hotelService.searchHotels(location, checkInDate, checkOutDate, noOfRooms, noOfAdults, noOfChildren);
    }
}
