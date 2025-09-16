package com.hexaware.cozyhaven.service;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.hexaware.cozyhaven.dto.HotelsDTO;
import com.hexaware.cozyhaven.dto.ReservationsDTO;
import com.hexaware.cozyhaven.dto.UserDTO;
import com.hexaware.cozyhaven.entity.Reservations;
import com.hexaware.cozyhaven.entity.User;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.exceptions.InvalidReservationIdException;
import com.hexaware.cozyhaven.exceptions.UserNotFoundException;

@Service
public interface IAdminService {
	
    UserDTO getUserById(Integer userId) throws UserNotFoundException;
	
	List<UserDTO> getallUsers();
	
	String deleteUser(Integer userId);
	
	List<UserDTO> getallOwners();
	
	UserDTO getOwnerById(Integer userId) throws UserNotFoundException;
	
	List<ReservationsDTO> getAllReservations();
	 
	ReservationsDTO getReservationById(Integer reservationId) throws InvalidReservationIdException;
	
	long countTotalUsers();
	
    long countTotalBookings();
    
    double getTotalRevenue();
    
    void deleteAllHotels();

	HotelsDTO updateHotel(Integer HotelId, HotelsDTO hoteldto) throws InvalidHotelIdException;

	String deleteHotel(Integer hotelId) throws InvalidHotelIdException;

	HotelsDTO addHotel(HotelsDTO hoteldto);

	List<HotelsDTO> getAllHotels();

	

}
