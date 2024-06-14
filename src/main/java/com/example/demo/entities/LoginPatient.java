package com.example.demo.entities;

import java.beans.JavaBean;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class LoginPatient {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String userid;
	private String name;

}
