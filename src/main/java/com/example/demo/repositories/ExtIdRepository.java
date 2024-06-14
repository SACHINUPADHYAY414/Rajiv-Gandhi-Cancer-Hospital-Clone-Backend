package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.ExtId;

@Repository
public interface ExtIdRepository extends JpaRepository<ExtId, Integer>{

	@Query("SELECT u.extId FROM ExtId u ORDER BY u.extId DESC LIMIT 1")
	public String getExtId();

}
