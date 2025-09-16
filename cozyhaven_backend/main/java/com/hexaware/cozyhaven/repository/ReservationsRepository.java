package com.hexaware.cozyhaven.repository;

import java.util.List;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.cozyhaven.dto.ReservationsDTO;
import com.hexaware.cozyhaven.entity.Reservations;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Integer> {
	
	@Query("SELECT r FROM Reservations r WHERE r.user IS NOT NULL AND r.hotels IS NOT NULL AND r.rooms IS NOT NULL AND r.paymentId IS NOT NULL")
	List<Reservations> getAllReservations();
	
	@Query("SELECT r FROM Reservations r WHERE r.reservationId = :reservationId")
	Reservations getReservationById(@Param("reservationId") Integer reservationId);

	
	@Query("select count(r) from Reservations r")
	long countTotalBookings();
	
	@Query("select sum(r.totalPrice) from Reservations r where r.status = 'BOOKED'")
	Double getTotalRevenue();
	

}
