package com.hexaware.cozyhaven.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.cozyhaven.dto.RefundDTO;
import com.hexaware.cozyhaven.dto.RoomsDTO;
import com.hexaware.cozyhaven.exceptions.InvalidHotelIdException;
import com.hexaware.cozyhaven.exceptions.InvalidReservationIdException;
import com.hexaware.cozyhaven.exceptions.InvalidRoomIdException;
import com.hexaware.cozyhaven.exceptions.RefundIDNotFoundException;
import com.hexaware.cozyhaven.service.IOwnerService;
import com.hexaware.cozyhaven.service.RoomServiceImpl;

import jakarta.validation.Valid;



@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/owner")
public class OwnerController {
	
	@Autowired
	IOwnerService onwerService;
	
	@Autowired
	RoomServiceImpl roomService;
	
	
	
	
	@PutMapping("/approveRefund/{refundId}")
	@PreAuthorize("hasAuthority('ROLE_HOTELOWNER')")
	public RefundDTO approveRefundRequest(@PathVariable Integer refundId) {
	    return onwerService.approveRefundRequest(refundId);
	}

	@PutMapping("/rejectRefund/{refundId}")
	@PreAuthorize("hasAuthority('ROLE_HOTELOWNER')")
	public RefundDTO rejectRefundRequest(@PathVariable Integer refundId) throws RefundIDNotFoundException {
	    return onwerService.rejectRefundRequest(refundId);
	}
	
	@PostMapping("/addRoom")
	@PreAuthorize("hasAuthority('ROLE_HOTELOWNER')")
	public RoomsDTO addRoom(@RequestBody @Valid RoomsDTO roomdto) throws InvalidHotelIdException {
		
		return onwerService.addRoom(roomdto);
	}
	
	@PutMapping("/updateRoom/{roomId}")
	@PreAuthorize("hasAuthority('ROLE_HOTELOWNER')")
	public RoomsDTO updateRoom(@PathVariable Integer roomId, @RequestBody @Valid RoomsDTO roomdto) throws InvalidRoomIdException {
		
		return onwerService.updateRoom(roomId,roomdto);
	}
	
	@DeleteMapping("/deleteRoom/{roomId}")
	@PreAuthorize("hasAuthority('ROLE_HOTELOWNER')")
	public void deleteRoom(@PathVariable Integer roomId) throws InvalidRoomIdException {
		
		onwerService.deleteRoom(roomId);
	}
	
	@DeleteMapping("/deletereservation/{reservationId}")
	public String deleteReservation(@PathVariable Integer reservationId) throws InvalidReservationIdException{
		
		return onwerService.deleteReservation(reservationId);
		
		
	}
	
	@GetMapping("/getallrooms")
	@PreAuthorize("hasAuthority('ROLE_HOTELOWNER')")
	public List<RoomsDTO> getallRooms(){
		
		return roomService.getallRooms();
	}
	
	@GetMapping("/getallrefunds")
	@PreAuthorize("hasAuthority('ROLE_HOTELOWNER')")
	public List<RefundDTO> getAllRefunds(){
		
		return onwerService.getAllRefunds();
	}
}
