package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.State;

public interface StateServices {
	public List<State> saveState(List<State> entState);
	public List<State> getState(String countryId);
}