package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Country;

public interface CountryServices {
	public List<Country> addCountriesList(List<Country> countries);
	public List<Country> getCountriesList();
}
