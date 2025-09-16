package com.hexaware.paymentmicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PaymentIdNotFoundException.class)
	public ResponseEntity<String> PaymentIdNotFoundExceptionHandler(PaymentIdNotFoundException e)
	{
		return new ResponseEntity<String>("Entered Payment ID is Invalid,Kindly check Again",HttpStatus.BAD_REQUEST);
	}
	
}
