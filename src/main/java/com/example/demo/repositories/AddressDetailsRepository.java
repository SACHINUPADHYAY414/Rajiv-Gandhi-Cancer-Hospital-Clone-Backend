package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.AddressDetails;

@Repository
public interface AddressDetailsRepository extends JpaRepository<AddressDetails, Integer>{
	public List<AddressDetails> findByAddressableId(String addressablId);
}