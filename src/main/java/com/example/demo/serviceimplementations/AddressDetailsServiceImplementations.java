package com.example.demo.serviceimplementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.AddressDetails;
import com.example.demo.repositories.AddressDetailsRepository;
import com.example.demo.services.AddressDetailsServices;

@Service
public class AddressDetailsServiceImplementations implements AddressDetailsServices{

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Autowired
	private AddressDetailsRepository addressDetailsRepository;

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Override
	public List<AddressDetails> getAddressDetails(String addressableId) {
		return addressDetailsRepository.findByAddressableId(addressableId);
	}

	@Override
	public String saveAddressDetails(AddressDetails addressDetails) {
		try{
			addressDetailsRepository.save(addressDetails);
			return "Address Saved";
		}catch(Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String modifyAddressDetails(AddressDetails addressDetails) {
		List<AddressDetails> count = addressDetailsRepository.findByAddressableId(addressDetails.getAddressableId());
		if (count.isEmpty()) {
			return "Address not found";
		}else {
			try{
				addressDetails.setId(count.getFirst().getId());
				addressDetailsRepository.save(addressDetails);
				return "Address Updated";
			}catch(Exception e) {
				return e.getMessage();
			}
		}
	}

//<---------------------------------------------------------------------------------------------------------------------------------->

}
