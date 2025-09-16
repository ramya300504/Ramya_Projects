package com.hexaware.cozyhaven.service;


import java.util.List;

import com.hexaware.cozyhaven.dto.RefundDTO;
import com.hexaware.cozyhaven.dto.ReservationsDTO;
import com.hexaware.cozyhaven.dto.UserDTO;
import com.hexaware.cozyhaven.entity.Refund;
import com.hexaware.cozyhaven.entity.User;
import com.hexaware.cozyhaven.exceptions.UserNotFoundException;


public interface IUserService {
	
	
	
    User createUser(UserDTO userdto);
    
    UserDTO updateUser(UserDTO userdto,Integer userId) throws UserNotFoundException;
    
    UserDTO findByEmail(String email) throws UserNotFoundException;
	
    List<ReservationsDTO>getReservatiosByUser(Integer userId);
	

	Double calculateTotalPrice(String bedType, double baseFare, int numberOfAdults, int numberOfChildren);
	
	public Refund processRefund(RefundDTO refunddto);
	
    
}
