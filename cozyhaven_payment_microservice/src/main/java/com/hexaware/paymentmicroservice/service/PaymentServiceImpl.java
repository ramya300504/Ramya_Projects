package com.hexaware.paymentmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.paymentmicroservice.dto.PaymentDTO;
import com.hexaware.paymentmicroservice.entity.Payment;
import com.hexaware.paymentmicroservice.exception.PaymentIdNotFoundException;
import com.hexaware.paymentmicroservice.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements IPaymentService{

	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public Payment processPayment(PaymentDTO paymentDTO) {
		
		Payment payment=new Payment();
		
		payment.setPaymentId(paymentDTO.getPaymentId());
		payment.setAmount(paymentDTO.getAmount());
		payment.setPaymentDate(paymentDTO.getPaymentDate());
		payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setPaymentStatus("SUCCESS");
		
		return paymentRepository.save(payment);		
	}

	@Override
	public Payment getPaymentDetails(int paymentId) throws PaymentIdNotFoundException {
		
		return paymentRepository.findById(paymentId).orElseThrow(()-> new  PaymentIdNotFoundException());
	}

	@Override
	public Payment updatePayment(Integer paymentId, PaymentDTO paymentDTO) throws PaymentIdNotFoundException {
		
		Payment payment=paymentRepository.findById(paymentId).orElseThrow(()-> new PaymentIdNotFoundException());
		
		payment.setAmount(paymentDTO.getAmount());
		payment.setPaymentDate(paymentDTO.getPaymentDate());
		payment.setPaymentMethod(paymentDTO.getPaymentMethod());
		payment.setPaymentStatus(paymentDTO.getPaymentStatus());
		
		return paymentRepository.save(payment);
		
		
	}

	@Override
	public String deletePayment(Integer paymentId) throws PaymentIdNotFoundException {
		
		Payment payment=paymentRepository.findById(paymentId).orElseThrow(()->new PaymentIdNotFoundException());
		
		paymentRepository.deleteById(paymentId);
		
		return "Payment Deleted Successfully";
		
	}
	
	
}
