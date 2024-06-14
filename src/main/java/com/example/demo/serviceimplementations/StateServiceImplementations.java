package com.example.demo.serviceimplementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.State;
import com.example.demo.repositories.StateRepository;
import com.example.demo.services.StateServices;

@Service
public class StateServiceImplementations implements StateServices{

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Autowired
	private StateRepository repository;

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Override
	public List<State> saveState(List<State> entState) {
		List<State> eState = new ArrayList<>();
		for (State s : entState) {
			eState.add(s);
		}
		repository.saveAll(eState);
		return eState;
	}

	@Override
	public List<State> getState(String countryId) {
		return repository.findAllByCountryId(countryId);
	}

//<---------------------------------------------------------------------------------------------------------------------------------->

}
