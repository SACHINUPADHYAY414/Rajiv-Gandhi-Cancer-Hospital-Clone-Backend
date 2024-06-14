package com.example.demo.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
@Table(name="RegisterPatient")
public class RegisterPatient {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private Integer titleId;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private Integer genderId;

	@NotNull
	private Date dob;

	@NotNull
	@Column(length=10)
	private Long mobileNumber;

	@NotNull
	@Email
	private String email;

	@NotNull
	private String address;

	@NotNull
	private String cityId;

	@NotNull
	private String stateId;

	@NotNull
	private String countryId;

	@NotNull
	private Integer pincode;

	private String userId;

	private String status;

}
