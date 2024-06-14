package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.City;

public interface CityServices {
	public List<City> addCity(List<City> cities);
	public List<City> getCities(String stateId);
}
