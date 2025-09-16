package com.hexaware.cozyhaven.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.cozyhaven.dto.HotelsDTO;
import com.hexaware.cozyhaven.dto.ReservationsDTO;
import com.hexaware.cozyhaven.dto.UserConverter;
import com.hexaware.cozyhaven.dto.UserDTO;
import com.hexaware.cozyhaven.entity.Hotels;
import com.hexaware.cozyhaven.entity.Reservations;
import com.hexaware.cozyhaven.entity.User;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.exceptions.InvalidReservationIdException;
import com.hexaware.cozyhaven.exceptions.UserNotFoundException;
import com.hexaware.cozyhaven.repository.HotelsRepository;
import com.hexaware.cozyhaven.repository.ReservationsRepository;
import com.hexaware.cozyhaven.repository.UserRepository;

/*
 * Admin Services includes search user by id and owner by id, and view all users and owners
 * and can edit and delete user,owner accounts
 * Inaddition to that Admin can view all reservations and he/she can edit their profile
 */
@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ReservationsRepository reservationsRepository;
	
	@Autowired
	HotelsRepository hotelsRepository;
	
	 private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);


	//to get user by id
	@Override
	public UserDTO getUserById(Integer userId) throws UserNotFoundException {
		
		log.info("Fetching user with ID: {}", userId);

		
		 User user = userRepository.getUserById(userId);
	      
	     return convertToDTO(user);
			  
	}

	@Override
	public UserDTO getOwnerById(Integer userId) throws UserNotFoundException {
		
		User user = userRepository.findById(userId)
		        .orElseThrow(() -> new UserNotFoundException());

		    return UserConverter.toDTO(user);
		
	}
	
	@Override
	public String deleteUser(Integer userId) {
		
		userRepository.deleteById(userId);
		
		return "User Deleted Successfully";
		
	}
	
	@Override
	public List<UserDTO> getallUsers() {
		
		List<User> users = userRepository.getAllUsers();
	    return UserConverter.toDTOList(users);
	}
	
	@Override
	public List<UserDTO> getallOwners() {
		
		List<User> users = userRepository.getAllOwners();
	    return UserConverter.toDTOList(users);
	}

	private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setContactNumber(user.getContactNumber());
        dto.setAddress(user.getAddress());

        if (user.getRole() != null) {
            dto.setRole(user.getRole().name());
        }

        return dto;
    }
	
	@Override
	public HotelsDTO addHotel(HotelsDTO hoteldto) {
		
		Hotels hotels=new Hotels();
		
		hotels.setHotelName(hoteldto.getHotelName());
		hotels.setLocation(hoteldto.getLocation());
		hotels.setAddress(hoteldto.getAddress());
		hotels.setContactNumber(hoteldto.getContactNumber());
        hotels.setDescription(hoteldto.getDescription());
        hotels.setImageUrl(hoteldto.getImageUrl());
        hotels.setAmenities(hoteldto.getAmenities());
        Hotels added= hotelsRepository.save(hotels);
        
        return convertToDTO(added);
	}
	

	@Override
		public List<HotelsDTO> getAllHotels() {
		    List<Hotels> hotelsList = hotelsRepository.findAll();

		    List<HotelsDTO> dtoList = new ArrayList<>();
		    for (Hotels hotel : hotelsList) {
		        dtoList.add(convertToDTO(hotel));
		    }

		    return dtoList;
		}
		

	@Override
	public String  deleteHotel(Integer hotelId) throws InvalidHotelIdException {
		
		Hotels hotels=hotelsRepository.findById(hotelId).orElseThrow(()->new InvalidHotelIdException());
		
		hotelsRepository.deleteById(hotelId);

		return "Hotel Deleted Successfully";
	}

	@Override
	public HotelsDTO updateHotel(Integer HotelId ,HotelsDTO hoteldto)throws InvalidHotelIdException {
		
        Hotels hotels=hotelsRepository.findById(HotelId).orElseThrow(()->new InvalidHotelIdException());
		
       
		hotels.setHotelName(hoteldto.getHotelName());
		hotels.setLocation(hoteldto.getLocation());
		hotels.setAddress(hoteldto.getAddress());
		hotels.setContactNumber(hoteldto.getContactNumber());
        hotels.setDescription(hoteldto.getDescription());
        hotels.setAmenities(hoteldto.getAmenities());
        hotels.setImageUrl(hoteldto.getImageUrl());
        
        Hotels added=  hotelsRepository.save(hotels);
        
        return convertToDTO(added);
		

	}
	
     private HotelsDTO convertToDTO(Hotels hotel) {
		
	    HotelsDTO dto = new HotelsDTO();
	    dto.setHotelId(hotel.getHotelId());
	    dto.setHotelName(hotel.getHotelName());
	    dto.setLocation(hotel.getLocation());
	    dto.setAddress(hotel.getAddress());
	    dto.setContactNumber(hotel.getContactNumber());
	    dto.setDescription(hotel.getDescription());
	    dto.setImageUrl(hotel.getImageUrl()); 
	    dto.setAmenities(hotel.getAmenities());
	    
	    return dto;
	}
	
	//to get all reservations
	@Override
	public List<ReservationsDTO> getAllReservations() {
		
		 log.info("Fetching all Reservations");
		 List<Reservations> reservations = reservationsRepository.getAllReservations();
		 List<ReservationsDTO> reservationDTOs = reservations.stream().map(r -> new ReservationsDTO(r.getReservationId(), r.getCheckInDate(), r.getCheckOutDate(), r.getNumberOfAdults(),
				 r.getNumberOfChildren(), r.getTotalPrice(), r.getStatus(), r.getUser().getUserId(), r.getHotel().getHotelId(), r.getRoom().getRoomId(),r.getPaymentId())).collect(Collectors.toList());

		 return reservationDTOs;
	}

	//to get reservation by id
	@Override
	public ReservationsDTO getReservationById(Integer reservationId) throws InvalidReservationIdException {
		
		log.info("Fetching reservation with ID:", reservationId);
		
		Reservations reservations= reservationsRepository.findById(reservationId).orElse(null);
		
		if(reservations==null)
		{
			throw new InvalidReservationIdException();
		}
		
		return convertToDTO(reservations);
	}
	
	 private ReservationsDTO convertToDTO(Reservations reservations) {
		    ReservationsDTO dto = new ReservationsDTO();

		    dto.setReservationId(reservations.getReservationId());
		    dto.setCheckInDate(reservations.getCheckInDate());
		    dto.setCheckOutDate(reservations.getCheckOutDate());
		    dto.setNumberOfAdults(reservations.getNumberOfAdults());
		    dto.setNumberOfChildren(reservations.getNumberOfChildren());
		    dto.setTotalPrice(reservations.getTotalPrice());
		    dto.setStatus(reservations.getStatus());

		    if (reservations.getUser() != null)
		        dto.setUserId(reservations.getUser().getUserId());

		    if (reservations.getHotel() != null)
		        dto.setHotelId(reservations.getHotel().getHotelId());

		    if (reservations.getRoom() != null)
		        dto.setRoomId(reservations.getRoom().getRoomId());

		    
		        dto.setPaymentId(reservations.getPaymentId());

		    return dto;
		}

	//to count total users
	@Override
	public long countTotalUsers() {
		
		log.info("Counting total users.");
		return userRepository.countTotalUsers();
	}

	//to count totl bookings
	@Override
	public long countTotalBookings() {
		
		log.info("Counting total bookings.");
		return reservationsRepository.countTotalBookings();
	}

	//to get total revenue
	@Override
	public double getTotalRevenue() {
		
		 log.info("Calculating total revenue.");
		return reservationsRepository.getTotalRevenue();
	}

	@Override
	public void deleteAllHotels() {
		
		hotelsRepository.deleteAll();
		
	}


	

	

	

}
