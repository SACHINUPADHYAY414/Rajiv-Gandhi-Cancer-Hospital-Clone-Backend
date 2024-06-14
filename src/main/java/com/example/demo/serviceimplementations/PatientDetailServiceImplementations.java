package com.example.demo.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.PatientDetails;
import com.example.demo.entities.RegisterPatient;
import com.example.demo.repositories.GenderRepository;
import com.example.demo.repositories.RegisterPatientRepository;
import com.example.demo.services.PatientDetailServices;

@Service
public class PatientDetailServiceImplementations implements PatientDetailServices{

	@Autowired
	private RegisterPatientRepository registerPatientRepository;

	@Autowired
	private GenderRepository genderRepository;

	@Override
	public PatientDetails getDataFromUserId(String userId) {
		RegisterPatient rp = registerPatientRepository.getListFromUserId(userId);
		PatientDetails pd = new PatientDetails();
		pd.setAddress(rp.getAddress());
		pd.setEmail(rp.getEmail());
		pd.setGender(genderRepository.findAllByGenderId(rp.getGenderId()).getFirst().getGender());
		pd.setMobile(String.valueOf(rp.getMobileNumber()));
		pd.setName(rp.getFirstName()+" "+rp.getLastName());
		pd.setUserId(userId);
		return pd;
	}

}
