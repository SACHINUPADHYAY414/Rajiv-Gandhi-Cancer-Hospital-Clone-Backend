package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{
	List<State> findAllByCountryId(String countryId);

}
