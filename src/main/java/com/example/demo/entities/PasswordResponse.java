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
public class PasswordResponse {
	private Boolean userExists;
}
