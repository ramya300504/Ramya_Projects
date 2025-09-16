package com.hexaware.cozyhaven.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.cozyhaven.dto.RoomsDTO;
import com.hexaware.cozyhaven.entity.Rooms;
import com.hexaware.cozyhaven.exceptions.InvalidBedTypeException;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.exceptions.InvalidRoomIdException;

@Service
public interface IRoomService {

	Rooms getRoomsById(Integer roomId) throws InvalidRoomIdException;
	
    List<RoomsDTO> getRoomsByHotel(Integer hotelId) throws InvalidHotelIdException;
    
    List<RoomsDTO> getAvailableRooms(Integer hotelId) throws InvalidHotelIdException;
    
    List<RoomsDTO> getAcRooms(Integer hotelId) throws InvalidHotelIdException;

    
    List<RoomsDTO> getRoomsLessThanBaseFare(double baseFare,Integer hotelId);
   
    
    List<RoomsDTO> getRoomsByBedType(String bedType,Integer hotelId) throws InvalidBedTypeException;
    
    List<RoomsDTO> getallRooms();
	
}
