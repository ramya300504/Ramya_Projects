package com.hexaware.cozyhaven.service;

import org.springframework.stereotype.Service;

import com.hexaware.cozyhaven.dto.PaymentDTO;
import com.hexaware.cozyhaven.dto.ReservationPaymentVO;
import com.hexaware.cozyhaven.dto.ReservationsDTO;
import com.hexaware.cozyhaven.entity.Reservations;

import com.hexaware.cozyhaven.exceptions.InvalidReservationIdException;


@Service
public interface IBookingService {
     
	ReservationsDTO createReservation(ReservationsDTO reservationdto) ;
	
	String cancelReservation(Integer reservationId) throws InvalidReservationIdException;


	
	//microservices based methods
	
	String updatePaymentByReservationService(Integer paymentId,PaymentDTO paymentDTO);
	
	ReservationPaymentVO getReservationPaymentByResID(Integer reservationId) throws InvalidReservationIdException;
	
	PaymentDTO addPaymentByReservationService(PaymentDTO paymentDTO) ;
	
	String deletePaymentByReservationService(Integer paymentId);
	


}
