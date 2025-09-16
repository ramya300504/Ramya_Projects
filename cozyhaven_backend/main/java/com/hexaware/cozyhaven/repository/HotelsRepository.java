package com.hexaware.cozyhaven.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.cozyhaven.dto.HotelsDTO;
import com.hexaware.cozyhaven.entity.Hotels;
@Repository
public interface HotelsRepository extends JpaRepository<Hotels, Integer> {
	
	
	 List<Hotels> findByLocation(String Location);
	 
	 @Query("SELECT DISTINCT h.location FROM Hotels h")
	 List<String> findAllHotelsLocations();
	
	 Hotels findByHotelName(String hotelName);
 
	 @Query(value = """
			  SELECT DISTINCT h.*
			  FROM hotels h
			  JOIN rooms r ON h.hotel_id = r.hotel_id
			  WHERE h.location = :location
			    AND r.max_persons >= :totalGuests
			    AND r.room_id NOT IN (
			        SELECT res.room_id
			        FROM reservations res
			        WHERE res.check_in_date <= :checkOutDate
			          AND res.check_out_date >= :checkInDate
			    )
			  GROUP BY h.hotel_id
			  HAVING COUNT(r.room_id) >= :noOfRooms
			""", nativeQuery = true)
			List<Hotels> searchHotels(
			    @Param("location") String location,
			    @Param("checkInDate") LocalDate checkInDate,
			    @Param("checkOutDate") LocalDate checkOutDate,
			    @Param("totalGuests") int totalGuests,
			    @Param("noOfRooms") int noOfRooms
			);
			
	

}
