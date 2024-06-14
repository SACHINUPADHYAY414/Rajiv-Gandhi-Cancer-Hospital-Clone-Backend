package com.example.demo.serviceimplementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Gender;
import com.example.demo.repositories.GenderRepository;
import com.example.demo.services.GenderServices;

@Service
public class GenderServiceImplementations implements GenderServices{

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Autowired
	private GenderRepository genderRepository;

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Override
	public String addGenders(Gender gender) {
	try {
		genderRepository.save(gender);
		return "New gender saved";
	}catch(Exception e) {
		return e.getMessage();
	}
	}

	@Override
	public List<Gender> getGenders() {
		return genderRepository.findAll();
	}

//<---------------------------------------------------------------------------------------------------------------------------------->

}
