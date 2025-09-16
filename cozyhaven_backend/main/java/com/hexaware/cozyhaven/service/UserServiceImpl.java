 package com.hexaware.cozyhaven.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.cozyhaven.dto.RefundDTO;
import com.hexaware.cozyhaven.dto.ReservationsDTO;
import com.hexaware.cozyhaven.dto.UserConverter;
import com.hexaware.cozyhaven.dto.UserDTO;
import com.hexaware.cozyhaven.entity.Refund;
import com.hexaware.cozyhaven.entity.Reservations;
import com.hexaware.cozyhaven.entity.User;
import com.hexaware.cozyhaven.entity.User.Role;
import com.hexaware.cozyhaven.exceptions.UserNotFoundException;
import com.hexaware.cozyhaven.repository.RefundRepository;
import com.hexaware.cozyhaven.repository.ReservationsRepository;
import com.hexaware.cozyhaven.repository.UserRepository;

/*
 * user service provides option to create new user,update existing user details and user can calculate total price 
 * according to noofadults and noofchildren and basefare and bedtype
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RefundRepository refundRepository;
	
	@Autowired
	ReservationsRepository reservationsRepository;
	

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserDTO userdto) {
		
		User user=new User();
		
		user.setFirstName(userdto.getFirstName());
		user.setLastName(userdto.getLastName());
		user.setEmail(userdto.getEmail());
		user.setPassword(passwordEncoder.encode(userdto.getPassword()));
		user.setAddress(userdto.getAddress());
		user.setContactNumber(userdto.getContactNumber());
		user.setRole(User.Role.valueOf(userdto.getRole()));
		
		
		return userRepository.save(user);
		
		
	}

	@Override
	public UserDTO updateUser(UserDTO userdto,Integer userId) throws UserNotFoundException {
		
		User user= userRepository.findById(userId).orElseThrow(()->new UserNotFoundException());
		
		user.setFirstName(userdto.getFirstName());
		user.setLastName(userdto.getLastName());
		user.setContactNumber(userdto.getContactNumber());
		user.setAddress(userdto.getAddress());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setRole(Role.valueOf(userdto.getRole()) );
		user.setUserId(userId);
		
		 User updated = userRepository.save(user);
		
		return UserConverter.toDTO(updated);		
	}
	

	@Override
	public Double calculateTotalPrice(String bedType, double baseFare, int numberOfAdults, int numberOfChildren) {
	    int allowedGuests = 0;

	    switch (bedType.toUpperCase()) {
	        case "SINGLE":
	            allowedGuests = 1;
	            break;
	        case "DOUBLE":
	            allowedGuests = 2;
	            break;
	        case "KING":
	            allowedGuests = 4;
	            break;
	        default:
	            return 0.0; 
	    }

	    int totalGuests = numberOfAdults + numberOfChildren;
	    int extraGuests = Math.max(0, totalGuests - allowedGuests);

	    double totalPrice = baseFare;

	  
	    for (int i = 0; i < extraGuests; i++) {
	        if (i < numberOfAdults) {
	            totalPrice += baseFare * 0.4; // adult
	        } else {
	            totalPrice += baseFare * 0.2; // child
	        }
	    }

	    return totalPrice;
	}


	@Override
	public Refund processRefund(RefundDTO refunddto) {
		
		Refund refund=new Refund();
		refund.setRefundAmount(refunddto.getRefundAmount());
		refund.setRefundDate(refunddto.getRefundDate());
		refund.setRefundReason(refunddto.getRefundReason());
		refund.setRefundStatus(refunddto.getRefundStatus());
		refund.setReservations(reservationsRepository.findById(refunddto.getReservationId()).orElse(null));
		refund.setUser(userRepository.findById(refunddto.getUserId()).orElse(null));
		
		return refundRepository.save(refund);
		
	}

	
	  @Override
      public List<ReservationsDTO>getReservatiosByUser(Integer userId){
    	  
		
           List<Reservations> reservationList = userRepository.getReservatiosByUser(userId);

		    
		    List<ReservationsDTO> dtoList = new ArrayList<>();

		   
		    for (Reservations reservation : reservationList) {
		        ReservationsDTO dto = convertToDTO(reservation);
		        dtoList.add(dto);
		    }

		    return dtoList;
    	  
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
		  
		    if (reservations.getRefund() != null) {
		        dto.setRefundStatus(reservations.getRefund().getRefundStatus());
		    } else {
		        dto.setRefundStatus("N/A");
		    }
		    if (reservations.getUser() != null)
		        dto.setUserId(reservations.getUser().getUserId());

		    if (reservations.getHotel() != null)
		        dto.setHotelId(reservations.getHotel().getHotelId());

		    if (reservations.getRoom() != null)
		        dto.setRoomId(reservations.getRoom().getRoomId());

		    
		        dto.setPaymentId(reservations.getPaymentId());

		    return dto;
		}




	  
	  
	  
	  @Override
	    public UserDTO findByEmail(String email) throws UserNotFoundException {

	       
	        User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new UserNotFoundException());

	       
	        return UserConverter.toDTO(user);
	    }


	
	
	
	

}
