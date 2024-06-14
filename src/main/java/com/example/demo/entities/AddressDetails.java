package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="addressdetails")
public class AddressDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String addressableId;
	private String addressableType;
	private String addressableLine1;
	private String addressableLine2;
	private String cityId;
	private String stateId;
	private String countryId;
	private Integer pincode;
	private Long residentialPhoneNumber;
	private Long mobileNumber;
	private Long officePhoneNumber;
	private String addressType;
}
