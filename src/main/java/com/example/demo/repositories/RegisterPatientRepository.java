package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.RegisterPatient;

public interface RegisterPatientRepository extends JpaRepository<RegisterPatient, Integer>{
	@Query("""
		    SELECT t FROM RegisterPatient t WHERE t.mobileNumber = :mobileNumber
		    """)
		    List<RegisterPatient> getListFromMobileNumber(Long mobileNumber);

	@Query("""
		    SELECT t FROM RegisterPatient t WHERE t.userId = :userId
		    """)
	RegisterPatient getListFromUserId(String userId);
}
