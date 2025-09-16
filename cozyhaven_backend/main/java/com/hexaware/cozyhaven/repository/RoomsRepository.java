package com.hexaware.cozyhaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.cozyhaven.dto.RoomsDTO;
import com.hexaware.cozyhaven.entity.Rooms;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Integer> {
	
	@Query(value = "select * from rooms where hotel_id = :hotelId", nativeQuery = true)
	List<Rooms> getRoomsByHotel(@Param("hotelId") Integer hotelId);

	@Query("SELECT new com.hexaware.cozyhaven.dto.RoomsDTO(r.roomId, r.roomSize, r.bedType, r.baseFare, r.maxPersons, r.ac, r.available, r.imageUrl, r.hotels.hotelId) " +
		       "FROM Rooms r WHERE r.hotels.hotelId = :hotelId AND r.ac = true")
		List<RoomsDTO> getAcRooms(@Param("hotelId") Integer hotelId);

	@Query("SELECT new com.hexaware.cozyhaven.dto.RoomsDTO(r.roomId, r.roomSize, r.bedType, r.baseFare, r.maxPersons, r.ac, r.available, r.imageUrl, r.hotels.hotelId) " +
		       "FROM Rooms r WHERE r.hotels.hotelId = :hotelId AND r.available = true")
		List<RoomsDTO> getAvailableRooms(@Param("hotelId") Integer hotelId);
	
	@Query("SELECT r FROM Rooms r WHERE r.baseFare < :baseFare AND r.hotels.hotelId = :hotelId")
    List<Rooms> findByBaseFareLessThan(@Param("baseFare") double baseFare,@Param("hotelId") Integer hotelId);
	
	
	@Query("SELECT r FROM Rooms r WHERE r.bedType = :bedType AND r.hotels.hotelId = :hotelId")
    List<Rooms> getRoomsByBedType(@Param("bedType") String bedType,@Param("hotelId") Integer hotelId); 
	
    @Query("SELECT new com.hexaware.cozyhaven.dto.RoomsDTO(r.roomId, r.roomSize, r.bedType, r.baseFare, r.ac, r.available, r.maxPersons,r.hotels.hotelId,r.imageUrl) FROM Rooms r")
 
    List<RoomsDTO> getallRooms();
	
	

}
