package com.hexaware.cozyhaven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.cozyhaven.entity.Refund;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Integer> {

}
