package com.example.demo.entities;

import java.beans.JavaBean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JavaBean
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientDetails {
	private String userId;
	private String name;
	private String gender;
	private String email;
	private String mobile;
	private String address;
}
