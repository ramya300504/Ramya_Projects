package com.hexaware.cozyhaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.cozyhaven.entity.Reviews;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Integer> {

	
	@Query("select r from Reviews r where r.hotels.hotelId=:hotelId")
	List<Reviews> findByHotels(@Param("hotelId") Integer hotelId);
	
	@Query(value = "SELECT AVG(rating) FROM reviews WHERE hotel_id = :hotelId", nativeQuery = true)
	Double getRatingByHotelId(@Param("hotelId") int hotelId);
}
