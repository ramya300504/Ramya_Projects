package com.hexaware.cozyhaven.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.cozyhaven.dto.HotelsDTO;
import com.hexaware.cozyhaven.entity.Hotels;
import com.hexaware.cozyhaven.exceptions.HotelNamenotFoundException;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.repository.HotelsRepository;

import lombok.extern.slf4j.Slf4j;

/*
 * Hotel service includes services like getting all locations for dropdown option in search bar and and search service to get hotels
 * by giving inputs like location,checkindate,checkoutdate,noodadults,noofchildren,noofrooms
 */
@Service
public class HotelServiceImpl implements IHotelService {

	@Autowired
	HotelsRepository hotelsRepository;
	
	 private static final Logger log = LoggerFactory.getLogger(HotelServiceImpl.class);

	@Override
	public List<Hotels> getHotelsByLocation(String Location) {
		
		log.info("Fetching hotels at location:", Location);
		return hotelsRepository.findByLocation(Location);
	}

	@Override
	public Hotels getHotelByName(String hotelName) throws HotelNamenotFoundException {
		
		 log.info("Searching hotel by name:", hotelName);
		Hotels hotels= hotelsRepository.findByHotelName(hotelName);
		if(hotels==null)
        {
			log.warn("Hotel not found with name:", hotelName);
			throw new HotelNamenotFoundException();
        }
		return hotels;
	}
	
	@Override
	public List<String> getAllLocations() {
	    return hotelsRepository.findAllHotelsLocations();
	}

	@Override
	public Hotels getHotelById(Integer hotelId) throws InvalidHotelIdException {
		
		Hotels hotels= hotelsRepository.findById(hotelId).orElse(null);
		if(hotels==null)
        {
        	throw new InvalidHotelIdException();
        }
		
		return hotels;
	}

	@Override
	public List<HotelsDTO> searchHotels(String location, LocalDate checkInDate, LocalDate checkOutDate,
	                                    int noOfRooms, int noOfAdults, int noOfChildren) {
	    int totalGuests = noOfAdults + noOfChildren;
	    log.info("Inside hotel search service");

	    
	    List<Hotels> hotels = hotelsRepository.searchHotels(location, checkInDate, checkOutDate, totalGuests, noOfRooms);

	    
	    List<HotelsDTO> hotelDTOlist = new ArrayList<>();

	    for (Hotels hotel : hotels) {
	        HotelsDTO dto = new HotelsDTO();
	        dto.setHotelId(hotel.getHotelId());
	        dto.setHotelName(hotel.getHotelName());
	        dto.setLocation(hotel.getLocation());
	        dto.setAddress(hotel.getAddress());
	        dto.setContactNumber(hotel.getContactNumber());
	        dto.setDescription(hotel.getDescription());
	        dto.setImageUrl(hotel.getImageUrl());
	        dto.setAmenities(hotel.getAmenities());

	        hotelDTOlist.add(dto);
	    }

	    // Step 3: Return the DTO list
	    return hotelDTOlist;
	}

	

	
} 
