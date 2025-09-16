package com.hexaware.paymentmicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.paymentmicroservice.dto.PaymentDTO;
import com.hexaware.paymentmicroservice.entity.Payment;
import com.hexaware.paymentmicroservice.exception.PaymentIdNotFoundException;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class PaymentServiceImplTest {

	@Autowired
	PaymentServiceImpl paymentService;
	
	@Test
	void testProcessPayment() {
		
		 PaymentDTO paymentDTO = new PaymentDTO(null,LocalDate.now(),2500.00,"CREDIT_CARD", "SUCCESS");

	     Payment processedPayment = paymentService.processPayment(paymentDTO);
	     
	     assertEquals("CREDIT_CARD", processedPayment.getPaymentMethod());
	}

	@Test
	void testGetPaymentDetails() throws PaymentIdNotFoundException {
		
		PaymentDTO paymentDTO = new PaymentDTO(null,LocalDate.now(),2500.00,"CREDIT_CARD", "SUCCESS");

	    Payment processedPayment = paymentService.processPayment(paymentDTO);
	    
	    Payment detailpayment=paymentService.getPaymentDetails(processedPayment.getPaymentId());
	    
	    assertEquals("CREDIT_CARD", detailpayment.getPaymentMethod());
	}

	@Test
	void testUpdatePayment() throws PaymentIdNotFoundException {
		
		PaymentDTO paymentDTO = new PaymentDTO(null,LocalDate.now(),2500.00,"CREDIT_CARD", "SUCCESS");

	    Payment processedPayment = paymentService.processPayment(paymentDTO);
	    
	    PaymentDTO updatedDTO = new PaymentDTO(processedPayment.getPaymentId(),LocalDate.now(), 3000.00,"DEBIT_CARD","FAILED");
	    
	    Payment updatedPayment=paymentService.updatePayment(updatedDTO.getPaymentId(), updatedDTO);
	    
	    assertEquals("DEBIT_CARD", updatedPayment.getPaymentMethod());
	}

	@Test
	void testDeletePayment() throws PaymentIdNotFoundException {
		
		PaymentDTO paymentDTO = new PaymentDTO(null,LocalDate.now(),2500.00,"CREDIT_CARD", "SUCCESS");

	    Payment processedPayment = paymentService.processPayment(paymentDTO);
	    
	    String result=paymentService.deletePayment(processedPayment.getPaymentId());
	    
	    assertEquals("Payment Deleted Successfully", result);
	}

}
