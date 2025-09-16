package com.hexaware.cozyhaven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.cozyhaven.entity.Reservation_Guests;

@Repository
public interface ReservationGuestRerpository extends JpaRepository<Reservation_Guests, Integer> {

}
