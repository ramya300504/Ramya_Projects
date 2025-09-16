package com.hexaware.cozyhaven.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.cozyhaven.dto.RoomsDTO;
import com.hexaware.cozyhaven.entity.Hotels;
import com.hexaware.cozyhaven.entity.Rooms;
import com.hexaware.cozyhaven.exceptions.InvalidBedTypeException;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.exceptions.InvalidRoomIdException;
import com.hexaware.cozyhaven.repository.HotelsRepository;
import com.hexaware.cozyhaven.repository.RoomsRepository;
/*
 * room service provide filters option like  acrooms,available rooms,rooms less than base fare,rooms based on bedtype
 */
@Service
public class RoomServiceImpl implements IRoomService {
	
	@Autowired
	RoomsRepository roomsRepository;
	
	@Autowired
	HotelsRepository hotelsRepository;

	@Override
	public List<RoomsDTO> getRoomsByHotel(Integer hotelId) throws InvalidHotelIdException {
		
		Hotels hotels=hotelsRepository.findById(hotelId).orElse(null);
		
		if(hotels==null)
		{
			throw new InvalidHotelIdException();
		}
		
		
		List<Rooms> rooms = roomsRepository.getRoomsByHotel(hotelId);
	    
	    List<RoomsDTO> roomDTOList = new ArrayList<>();
	    for (Rooms room : rooms) {
	        RoomsDTO dto = new RoomsDTO();
	        dto.setRoomId(room.getRoomId());
	        dto.setBedType(room.getBedType());
	        dto.setBaseFare(room.getBaseFare());
	        dto.setMaxPersons(room.getMaxPersons());
	        dto.setAc(room.isAc());
	        dto.setAvailable(room.isAvailable());
	        dto.setHotelId(room.getHotel().getHotelId()); 
	        dto.setImageUrl(room.getImageUrl());
	        roomDTOList.add(dto);
	    }

	    return roomDTOList;
	}

	@Override
	public List<RoomsDTO> getAvailableRooms(Integer hotelId) throws InvalidHotelIdException {
		
		hotelsRepository.findById(hotelId).orElseThrow(()->new InvalidHotelIdException());
		
		return roomsRepository.getAvailableRooms(hotelId);
		
		
		
		
	}

	@Override
	public List<RoomsDTO> getAcRooms(Integer hotelId) throws InvalidHotelIdException {
		
		hotelsRepository.findById(hotelId).orElseThrow(()->new InvalidHotelIdException());
		return  roomsRepository.getAcRooms(hotelId); 
	
		
	}
	@Override
	public List<RoomsDTO> getRoomsLessThanBaseFare(double baseFare,Integer hotelId) {
		
		 List<Rooms> rooms=roomsRepository.findByBaseFareLessThan(baseFare,hotelId);
		 
		  List<RoomsDTO> roomDTOList = new ArrayList<>();
		 for (Rooms room : rooms) {
		        RoomsDTO dto = new RoomsDTO();
		        dto.setRoomId(room.getRoomId());
		        dto.setBedType(room.getBedType());
		        dto.setBaseFare(room.getBaseFare());
		        dto.setMaxPersons(room.getMaxPersons());
		        dto.setAc(room.isAc());
		        dto.setAvailable(room.isAvailable());
		        dto.setHotelId(room.getHotel().getHotelId()); 
		        dto.setImageUrl(room.getImageUrl());
		        roomDTOList.add(dto);
		    }

		    return roomDTOList;
	}
//
//	@Override
//	public List<Rooms> getRoomsinRange(double baseFare1, double baseFare2) {
//		
//		return roomsRepository.getRoomsinRange(baseFare1, baseFare2);
//	}

	@Override
	public List<RoomsDTO> getRoomsByBedType(String bedType,Integer hotelId) throws InvalidBedTypeException {
		
		if(!bedType.equalsIgnoreCase("SINGLE") &&
		        !bedType.equalsIgnoreCase("DOUBLE") &&
		        !bedType.equalsIgnoreCase("KING"))
		{
			throw new InvalidBedTypeException();
		}
		
		 List<Rooms> rooms=  roomsRepository.getRoomsByBedType(bedType,hotelId);
		 
		 List<RoomsDTO> roomDTOList = new ArrayList<>();
		 for (Rooms room : rooms) {
		        RoomsDTO dto = new RoomsDTO();
		        dto.setRoomId(room.getRoomId());
		        dto.setBedType(room.getBedType());
		        dto.setBaseFare(room.getBaseFare());
		        dto.setMaxPersons(room.getMaxPersons());
		        dto.setAc(room.isAc());
		        dto.setAvailable(room.isAvailable());
		        dto.setHotelId(room.getHotel().getHotelId()); 
		        dto.setImageUrl(room.getImageUrl());
		        roomDTOList.add(dto);
		    }

		    return roomDTOList;
		 
	}

	@Override
	public Rooms getRoomsById(Integer roomId) throws InvalidRoomIdException {
		
		Rooms rooms= roomsRepository.findById(roomId).orElseThrow(()-> new InvalidRoomIdException());
		
		return rooms;
	}

	@Override
	public List<RoomsDTO> getallRooms() {
		
		return roomsRepository.getallRooms();
	}

	
}
