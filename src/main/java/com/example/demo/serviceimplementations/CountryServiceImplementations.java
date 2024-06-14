package com.example.demo.serviceimplementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Country;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.services.CountryServices;

@Service
public class CountryServiceImplementations implements CountryServices{

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Autowired
	private CountryRepository countryRepository;

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Override
	public List<Country> addCountriesList(List<Country> countries) {
		List<Country> inputCountries = new ArrayList<>();
		for (Country c : countries) {
			countryRepository.save(c);
			inputCountries.add(c);
		}
		return inputCountries;
	}

	@Override
	public List<Country> getCountriesList() {
		return countryRepository.findAll();
	}

//<---------------------------------------------------------------------------------------------------------------------------------->

}
