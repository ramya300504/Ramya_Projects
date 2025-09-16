package com.hexaware.cozyhaven.dto;

public class ReservationPaymentVO {

	private ReservationsDTO reservationsDTO;
	private PaymentDTO paymentDTO;
	
	
	
	
	public ReservationPaymentVO(ReservationsDTO reservationsDTO, PaymentDTO paymentDTO) {
		super();
		this.reservationsDTO = reservationsDTO;
		this.paymentDTO = paymentDTO;
	}
	
	public ReservationPaymentVO() {
		super();
	}

	public ReservationsDTO getReservationsDTO() {
		return reservationsDTO;
	}
	public void setReservationsDTO(ReservationsDTO reservationsDTO) {
		this.reservationsDTO = reservationsDTO;
	}
	public PaymentDTO getPaymentDTO() {
		return paymentDTO;
	}
	public void setPaymentDTO(PaymentDTO paymentDTO) {
		this.paymentDTO = paymentDTO;
	}
	
	
}
