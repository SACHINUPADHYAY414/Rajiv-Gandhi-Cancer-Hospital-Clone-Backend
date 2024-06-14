package com.example.demo.serviceimplementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ExtId;
import com.example.demo.entities.Patient;
import com.example.demo.entities.Roles;
import com.example.demo.entities.User;
import com.example.demo.repositories.ExtIdRepository;
import com.example.demo.repositories.PatientRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.PatientServices;

@Service
public class PatientServiceImplementations implements PatientServices{

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ExtIdRepository idRepository;

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Override
	public String savePatient(Patient patient) {
		try{
			String euid = getExtId();
			User user = new User();
			user.setEnabled(true);
			user.setUsername(String.valueOf(patient.getMobileNumber()));
			user.setUserType("Patient");
			user.setRole(Roles.USER);
			user.setUserId(euid);
			userRepository.save(user);

			patient.setStatus("Active");
			patient.setExtId(euid);
			patientRepository.save(patient);
			return "Patient Saved";
		}catch(Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String modifyPatientDetails(Patient patient) {
		List<Patient> patients = patientRepository.findByExtId(patient.getExtId());
		if(patients.isEmpty()) {
			return "No user found with id "+patient.getId();
		}else {
			try {
				Patient fPatient = patients.get(0);
				fPatient.setFirstName(patient.getFirstName());
				fPatient.setLastName(patient.getLastName());
				fPatient.setMiddleName(patient.getMiddleName());
				fPatient.setGenderId(patient.getGenderId());
				fPatient.setTitleId(patient.getTitleId());
				fPatient.setRegDate(patient.getRegDate());
				fPatient.setStatus(patient.getStatus());
				fPatient.setGmail(patient.getGmail());
				fPatient.setMobileNumber(patient.getMobileNumber());
				patientRepository.save(fPatient);
				return "User updated";
			}catch(Exception e) {
				return e.getMessage();
			}
		}
	}

	@Override
	public List<Patient> getActivePatients() {
		return patientRepository.findAll();
	}

//<---------------------------------------------------------------------------------------------------------------------------------->

	public String getExtId() {
		try {
			int lastid = Integer.parseInt(idRepository.getExtId().substring(4))+1;

			String eid;
			if(lastid>0 && lastid<10) {eid="TEMP000"+lastid;}
			else if(lastid>9 && lastid<100) {eid="TEMP00"+lastid;}
			else {eid="TEMP0"+lastid;}

			ExtId newPid = new ExtId();
			newPid.setExtId(eid);
			newPid.setRegistration("Registration");
			System.out.println(eid);
			idRepository.save(newPid);
			return eid;
		}catch(Exception e) {
			return e.getMessage();
		}
	}

//<---------------------------------------------------------------------------------------------------------------------------------->

}
