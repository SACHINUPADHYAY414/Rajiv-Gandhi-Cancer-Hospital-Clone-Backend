package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.AddressDetails;

public interface AddressDetailsServices {
	public List<AddressDetails> getAddressDetails(String addressableId);
	public String saveAddressDetails(AddressDetails addressDetails);
	public String modifyAddressDetails(AddressDetails addressDetails);
}