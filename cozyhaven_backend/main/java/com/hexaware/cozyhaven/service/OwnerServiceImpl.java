package com.hexaware.cozyhaven.service;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.cozyhaven.dto.HotelsDTO;
import com.hexaware.cozyhaven.dto.RefundDTO;
import com.hexaware.cozyhaven.dto.RoomsDTO;
import com.hexaware.cozyhaven.entity.Hotels;
import com.hexaware.cozyhaven.entity.Refund;
import com.hexaware.cozyhaven.entity.Reservations;
import com.hexaware.cozyhaven.entity.Rooms;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.exceptions.InvalidReservationIdException;
import com.hexaware.cozyhaven.exceptions.InvalidRoomIdException;
import com.hexaware.cozyhaven.repository.HotelsRepository;
import com.hexaware.cozyhaven.repository.RefundRepository;
import com.hexaware.cozyhaven.repository.ReservationsRepository;
import com.hexaware.cozyhaven.repository.RoomsRepository;
import com.hexaware.cozyhaven.repository.UserRepository;

/*
 * owner service provides option for owner to add new room,edit and delete existing room and also manage refund requests by approving 
 * and rejecting requests
 */

@Service
public class OwnerServiceImpl implements IOwnerService {
	
	@Autowired
	HotelsRepository hotelsRepository;
	
	@Autowired
	RoomsRepository roomsRepository;
	
	@Autowired
	ReservationsRepository reservationsRepository;
	
	@Autowired
	RefundRepository refundRepository;
	
	@Autowired
	UserRepository userRepository;
	
	

	

	@Override
	public RoomsDTO addRoom(RoomsDTO roomdto) throws InvalidHotelIdException {
		
		Rooms rooms=new Rooms();
		
		rooms.setAc(roomdto.isAc());
		rooms.setAvailable(roomdto.isAvailable());
		rooms.setBaseFare(roomdto.getBaseFare());
		rooms.setBedType(roomdto.getBedType());
		rooms.setMaxPersons(roomdto.getMaxPersons());
		rooms.setRoomSize(roomdto.getRoomSize());
		rooms.setImageUrl(roomdto.getImageUrl());
		Hotels hotel = hotelsRepository.findById(roomdto.getHotelId())
                .orElseThrow(() -> new InvalidHotelIdException());
		
		rooms.setHotel(hotel);
		 Rooms added = roomsRepository.save(rooms);

		    
			return convertToDTO(added);

	}

	@Override
	public RoomsDTO updateRoom(Integer roomId, RoomsDTO roomdto) throws InvalidRoomIdException {
		
        Rooms rooms=roomsRepository.findById(roomId).orElse(null);
        
        if(rooms==null)
        {
        	throw new InvalidRoomIdException();
        }
		
      
		rooms.setAc(roomdto.isAc());
		rooms.setAvailable(roomdto.isAvailable());
		rooms.setBaseFare(roomdto.getBaseFare());
		rooms.setBedType(roomdto.getBedType());
		rooms.setMaxPersons(roomdto.getMaxPersons());
		rooms.setRoomSize(roomdto.getRoomSize());
		rooms.setImageUrl(roomdto.getImageUrl());
		 Rooms updated = roomsRepository.save(rooms);

		    
		return convertToDTO(updated); 
		

	}
	
	//to prevent infinite json objects
	private RoomsDTO convertToDTO(Rooms room) {
		
	    RoomsDTO dto = new RoomsDTO();
	    dto.setRoomId(room.getRoomId());
	    dto.setRoomSize(room.getRoomSize());
	    dto.setBedType(room.getBedType());
	    dto.setBaseFare(room.getBaseFare());
	    dto.setMaxPersons(room.getMaxPersons());
	    dto.setAc(room.isAc());
	    dto.setAvailable(room.isAvailable());
	    dto.setHotelId(room.getHotel().getHotelId());
	    dto.setImageUrl(room.getImageUrl());
	    
	    return dto;
	}
	
	

	@Override
	public String deleteRoom(Integer roomId) throws InvalidRoomIdException {
		
		roomsRepository.findById(roomId).orElseThrow(()->new InvalidRoomIdException());
		roomsRepository.deleteById(roomId);
		
		return "Room Deleted Successfully";

	}

	

	@Override
	public List<RefundDTO> getAllRefunds() {
	    List<Refund> refunds = refundRepository.findAll();
	    List<RefundDTO> dtoList = new ArrayList<>();

	    for (Refund refund : refunds) {
	        RefundDTO dto = new RefundDTO();
	        dto.setRefundId(refund.getRefundId());
	        dto.setRefundAmount(refund.getRefundAmount());
	        dto.setRefundReason(refund.getRefundReason());
	        dto.setRefundDate(refund.getRefundDate());
	        dto.setRefundStatus(refund.getRefundStatus());
	        
	        if (refund.getReservations() != null) {
	            dto.setReservationId(refund.getReservations().getReservationId());
	        }

	        if (refund.getUser() != null) {
	            dto.setUserId(refund.getUser().getUserId());
	        }

	        dtoList.add(dto);
	    }

	    return dtoList;
	}

	
	@Override
	public RefundDTO approveRefundRequest(Integer refundId) {
	    Refund refund = refundRepository.findById(refundId).orElse(null);

	    refund.setRefundStatus("APPROVED");
	    refund.setRefundDate(LocalDate.now());

	    Reservations reservation = refund.getReservations();
	    reservation.setStatus("REFUNDED");
	    reservationsRepository.save(reservation);

	    return toDTO(refundRepository.save(refund));
	}

	@Override
	public RefundDTO rejectRefundRequest(Integer refundId) {
	    Refund refund = refundRepository.findById(refundId).orElse(null);

	    refund.setRefundStatus("REJECTED");
	    
	    

	    return toDTO(refundRepository.save(refund));
	}
	
	
	  public static RefundDTO toDTO(Refund refund) {
	        RefundDTO dto = new RefundDTO();
	        dto.setRefundId(refund.getRefundId());
	        dto.setRefundAmount(refund.getRefundAmount());
	        dto.setRefundReason(refund.getRefundReason());
	        dto.setRefundStatus(refund.getRefundStatus());
	        dto.setRefundDate(refund.getRefundDate());

	        if (refund.getReservations() != null)
	            dto.setReservationId(refund.getReservations().getReservationId());

	        if (refund.getUser() != null)
	            dto.setUserId(refund.getUser().getUserId());

	        return dto;
	    }
	
	
	@Override
	public String deleteReservation(Integer reservationId) throws InvalidReservationIdException {
		
		Reservations reservations=reservationsRepository.findById(reservationId).orElseThrow(()->new InvalidReservationIdException());
		reservationsRepository.deleteById(reservationId);
		 
		 return "Reservation Deleted Successfully";
	}

	

}
