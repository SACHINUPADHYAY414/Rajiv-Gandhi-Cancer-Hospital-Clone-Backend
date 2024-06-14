package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender,Integer>{

	public List<Gender> findAllByGenderId(int genderId);

}
