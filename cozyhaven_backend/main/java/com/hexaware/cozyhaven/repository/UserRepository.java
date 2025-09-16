package com.hexaware.cozyhaven.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.cozyhaven.dto.ReservationsDTO;
import com.hexaware.cozyhaven.dto.RoomsDTO;
import com.hexaware.cozyhaven.dto.UserDTO;
import com.hexaware.cozyhaven.entity.Reservations;
import com.hexaware.cozyhaven.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u where u.role='USER'")
    List<User> getAllUsers();

    @Query("SELECT u FROM User u WHERE u.role = 'HOTELOWNER'")
    List<User> getAllOwners();

    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    User getUserById(@Param("userId") Integer userId);

    @Query("SELECT u FROM User u WHERE u.userId = :userId AND u.role = 'OWNER'")
    User getOwnerById(@Param("userId") Integer userId);
	
	@Query("select count(u) from User u")
	long countTotalUsers();
	
	@Query("SELECT r FROM Reservations r LEFT JOIN FETCH r.refund WHERE r.user.userId = :userId")
	List<Reservations> getReservatiosByUser(@Param("userId") Integer userId);
	
	Optional<User> findByEmail(String email);

}
