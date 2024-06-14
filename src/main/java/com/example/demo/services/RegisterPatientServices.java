package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.LoginPatient;
import com.example.demo.entities.RegisterPatient;

public interface RegisterPatientServices {
	public Boolean regPatient(RegisterPatient registerPatient);

	List<LoginPatient> getLoginPatients(Long mobileNumber);
}