package com.hexaware.paymentmicroservice.service;

import com.hexaware.paymentmicroservice.dto.PaymentDTO;
import com.hexaware.paymentmicroservice.entity.Payment;
import com.hexaware.paymentmicroservice.exception.PaymentIdNotFoundException;

public interface IPaymentService {

    Payment processPayment(PaymentDTO paymentDTO);  //addpayment
    
    Payment updatePayment(Integer paymentId,PaymentDTO paymentDTO) throws PaymentIdNotFoundException;
    
    String deletePayment(Integer paymentId) throws PaymentIdNotFoundException;
	
    Payment getPaymentDetails(int paymentId) throws PaymentIdNotFoundException;
}
