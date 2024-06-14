package com.example.demo.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ExtId;
import com.example.demo.repositories.ExtIdRepository;
import com.example.demo.services.ExtIdServices;

@Service
public class ExtIdServiceImplementations implements ExtIdServices{

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Autowired
	private ExtIdRepository idRepository;

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Override
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

			idRepository.save(newPid);
			return eid;
		}catch(Exception e) {
			return e.getMessage();
		}
	}

//<---------------------------------------------------------------------------------------------------------------------------------->

}
