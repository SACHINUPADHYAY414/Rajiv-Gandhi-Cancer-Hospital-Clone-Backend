package com.example.demo.serviceimplementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.City;
import com.example.demo.repositories.CityRepository;
import com.example.demo.services.CityServices;

@Service
public class CityServiceImplementations implements CityServices {

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Autowired
	private CityRepository cityRepository;

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Override
	public List<City> addCity(List<City> cities) {
		List<City> inputCities = new ArrayList<>();
		for (City c : cities) {
			cityRepository.save(c);
			inputCities.add(c);
		}
		return inputCities;
	}

	@Override
	public List<City> getCities(String stateId) {
		return cityRepository.findAllByStateId(stateId);
	}

//<---------------------------------------------------------------------------------------------------------------------------------->

}
