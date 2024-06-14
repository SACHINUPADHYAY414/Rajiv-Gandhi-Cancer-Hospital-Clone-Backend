package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Gender;

public interface GenderServices {
	public String addGenders(Gender gender);
	public List<Gender> getGenders();
}
