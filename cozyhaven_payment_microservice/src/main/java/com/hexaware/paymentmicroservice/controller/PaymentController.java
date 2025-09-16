package com.hexaware.paymentmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.paymentmicroservice.dto.PaymentDTO;
import com.hexaware.paymentmicroservice.entity.Payment;
import com.hexaware.paymentmicroservice.exception.PaymentIdNotFoundException;
import com.hexaware.paymentmicroservice.service.PaymentServiceImpl;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentServiceImpl paymentService;
	
	@PostMapping("/processpayment")
	public Payment processPayment(@RequestBody PaymentDTO paymentDTO) {
		
		return paymentService.processPayment(paymentDTO);
	}

	@GetMapping("/getpaymentdetails/{paymentId}")
	public Payment getPaymentDetails(@PathVariable int paymentId) throws PaymentIdNotFoundException {
		
		return paymentService.getPaymentDetails(paymentId);
		
	}
	
	@PutMapping("/updatepayment/{paymentId}")
	public Payment updatePayment(@PathVariable Integer paymentId,@RequestBody PaymentDTO paymentDTO) throws PaymentIdNotFoundException {
		
		return paymentService.updatePayment(paymentId, paymentDTO);
	}
	
	@DeleteMapping("/deletepayment/{paymentId}")
	public String deletePayment(@PathVariable Integer paymentId) throws PaymentIdNotFoundException{
		
		return paymentService.deletePayment(paymentId);
	}
}
