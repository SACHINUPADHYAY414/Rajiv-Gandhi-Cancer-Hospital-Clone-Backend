package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Patient;

public interface PatientServices {
	public String savePatient(Patient patient);
	public String modifyPatientDetails(Patient patient);
	public List<Patient> getActivePatients();
}