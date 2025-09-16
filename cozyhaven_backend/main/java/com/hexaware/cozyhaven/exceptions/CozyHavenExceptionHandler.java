package com.hexaware.cozyhaven.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class CozyHavenExceptionHandler {
	
	@ExceptionHandler(InvalidHotelIdException.class)
	public ResponseEntity<String> InvalidHotelIdExceptionHandler(InvalidHotelIdException e){
		
		return new ResponseEntity<String>("Entered Hotel ID is Invalid, Kindly Check Again",HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidRoomIdException.class)
	public ResponseEntity<String> InvalidRoomIdExceptionHandler(InvalidRoomIdException e){
		
		return new ResponseEntity<String>("Entered Room  ID is Invalid, Kindly Check Again",HttpStatus.BAD_REQUEST);
		
		
	}
	
	@ExceptionHandler(HotelNamenotFoundException.class)
	public ResponseEntity<String> HotelNameNotFoundException(HotelNamenotFoundException e){
		
		return new ResponseEntity<String>("Hotel Name Not Found, Ensure Spelling",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidReservationIdException.class)
	public ResponseEntity<String> InvalidReservationIdExceptionHandler(){
		
		return new ResponseEntity<String>("Entered Reservation ID is Invalid, Kindly Check Again",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidPaymentStatusException.class)
	public ResponseEntity<String> InvalidPaymentStatusException(InvalidPaymentStatusException e){
		
		return new ResponseEntity<String>("For Booking Payment Status should be SUCCESS,others not Acceptable",HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> UserNotFoundExceptionHandler(UserNotFoundException e)
	{
		return new ResponseEntity<String>("User Not Found for the given UserID, Check Again",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidBedTypeException.class)
	public ResponseEntity<String> InvalidBedTypeExceptionHandler(InvalidBedTypeException e)
	{
		return new ResponseEntity<String>( "Incorrect Bed Type, Choose Correct BedType from Options Given", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RefundIDNotFoundException.class)
	public ResponseEntity<String> RefundIDNotFoundExceptionHandler(RefundIDNotFoundException e)
	{
		return new ResponseEntity<String>("Invalid Refund ID, check Again",HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(ReviewIDNotFoundException.class)
	public ResponseEntity<String> ReviewIDNotFoundExceptionHandler(ReviewIDNotFoundException e)
	{
		return new ResponseEntity<String>("Invalid Review ID, check Again",HttpStatus.EXPECTATION_FAILED);
	}
	
	
}
 