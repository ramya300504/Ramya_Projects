package com.hexaware.cozyhaven.dto;

import java.time.LocalDate;

public class RefundDTO {
	
	private Integer refundId;
	private double refundAmount;
	private String refundReason;
	private LocalDate refundDate;
	private String refundStatus;
	private Integer reservationId;
	private Integer userId;
	
	
	
	public RefundDTO() {
		super();
	}

	public RefundDTO(Integer refundId,double refundAmount, String refundReason, LocalDate refundDate,String refundStatus, Integer reservationId,
			Integer userId) {
		super();
		this.refundId=refundId;
		this.refundAmount = refundAmount;
		this.refundReason = refundReason;
		this.refundDate = refundDate;
		this.refundStatus=refundStatus;
		this.reservationId = reservationId;
		this.userId = userId;
	}
	
	public double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	
	public Integer getRefundId() {
		return refundId;
	}

	public void setRefundId(Integer refundId) {
		this.refundId = refundId;
	}

	public LocalDate getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(LocalDate refundDate) {
		this.refundDate = refundDate;
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	

}
