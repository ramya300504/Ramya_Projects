package com.hexaware.cozyhaven.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.cozyhaven.dto.HotelsDTO;
import com.hexaware.cozyhaven.dto.RefundDTO;
import com.hexaware.cozyhaven.dto.RoomsDTO;
import com.hexaware.cozyhaven.entity.Hotels;
import com.hexaware.cozyhaven.entity.Refund;
import com.hexaware.cozyhaven.entity.Rooms;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.exceptions.InvalidReservationIdException;
import com.hexaware.cozyhaven.exceptions.InvalidRoomIdException;
import com.hexaware.cozyhaven.exceptions.RefundIDNotFoundException;

@Service
public interface IOwnerService {

	
    RoomsDTO addRoom(RoomsDTO roomdto) throws InvalidHotelIdException;
	
    RoomsDTO updateRoom(Integer roomId, RoomsDTO roomdto)throws InvalidRoomIdException;
    
    String deleteRoom(Integer roomId) throws InvalidRoomIdException;
   
    
    String deleteReservation(Integer reservationId) throws InvalidReservationIdException;

    public RefundDTO approveRefundRequest(Integer refundId);
    
    public RefundDTO rejectRefundRequest(Integer refundId) throws RefundIDNotFoundException;

	List<RefundDTO> getAllRefunds();
}
