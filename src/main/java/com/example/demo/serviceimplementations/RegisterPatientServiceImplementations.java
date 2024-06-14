package com.example.demo.serviceimplementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.AddressDetails;
import com.example.demo.entities.ExtId;
import com.example.demo.entities.LoginPatient;
import com.example.demo.entities.RegisterPatient;
import com.example.demo.entities.Roles;
import com.example.demo.entities.Title;
import com.example.demo.entities.User;
import com.example.demo.repositories.AddressDetailsRepository;
import com.example.demo.repositories.ExtIdRepository;
import com.example.demo.repositories.RegisterPatientRepository;
import com.example.demo.repositories.TitleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.RegisterPatientServices;

@Service
public class RegisterPatientServiceImplementations implements RegisterPatientServices{

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Autowired
	private ExtIdRepository idRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TitleRepository titleRepository;

	@Autowired
	private RegisterPatientRepository registerPatientRepository;

	@Autowired
	private AddressDetailsRepository addressDetailsRepository;

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Override
	public Boolean regPatient(RegisterPatient registerPatient) {
		User user = new User();
		String euid = getExtId();
		AddressDetails addressDetails = new AddressDetails();
		try{
			Optional<User> find = userRepository.findAllByUsername(String.valueOf(registerPatient.getMobileNumber()));
			if(find.isEmpty()) {

				user.setUsername(String.valueOf(registerPatient.getMobileNumber()));
				user.setUserType("Patient");
				user.setEnabled(true);
				user.setRole(Roles.USER);
				user.setUserId(euid);
				user.setLocationId(registerPatient.getCityId());
				userRepository.save(user);

				addressDetails.setAddressableId(euid);
				addressDetails.setAddressableLine1(registerPatient.getAddress());
				addressDetails.setCityId(registerPatient.getCityId());
				addressDetails.setCountryId(registerPatient.getCountryId());
				addressDetails.setStateId(registerPatient.getStateId());
				addressDetails.setMobileNumber(registerPatient.getMobileNumber());
				addressDetails.setPincode(registerPatient.getPincode());
				addressDetailsRepository.save(addressDetails);

				registerPatient.setStatus("Active");
				registerPatient.setUserId(euid);
				registerPatientRepository.save(registerPatient);
				return false;

			}else {

				addressDetails.setAddressableId(euid);
				addressDetails.setAddressableLine1(registerPatient.getAddress());
				addressDetails.setCityId(registerPatient.getCityId());
				addressDetails.setCountryId(registerPatient.getCountryId());
				addressDetails.setStateId(registerPatient.getStateId());
				addressDetails.setMobileNumber(registerPatient.getMobileNumber());
				addressDetailsRepository.save(addressDetails);

				registerPatient.setStatus("Active");
				registerPatient.setUserId(euid);
				registerPatientRepository.save(registerPatient);
				return true;

			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<LoginPatient> getLoginPatients(Long mobileNumber) {
		List<RegisterPatient> registerPatientList = registerPatientRepository.getListFromMobileNumber(mobileNumber);
		System.out.print(registerPatientList);
		List<LoginPatient> loginPatient = new ArrayList<>();

		for(int i=0;i<registerPatientList.size();i++) {
			RegisterPatient rp = new RegisterPatient();
			LoginPatient lp = new LoginPatient();
			rp = registerPatientList.get(i);
			lp.setId(rp.getId());
			lp.setName(getTitleID(rp.getTitleId())+" "+ rp.getFirstName()+" "+rp.getLastName());
			lp.setUserid(rp.getUserId());
			loginPatient.add(lp);
		}
		System.out.print(loginPatient);
		return loginPatient;
	}

//<---------------------------------------------------------------------------------------------------------------------------------->

	private String getTitleID(int titleId) {
		try{
			List<Title> list = titleRepository.findAllByTitleId(titleId);
			return list.getFirst().getTitle();
		}catch(Exception e) {
			return e.getMessage();
		}
	}

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
