package com.example.demo.serviceimplementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Title;
import com.example.demo.repositories.TitleRepository;
import com.example.demo.services.TitleServices;

@Service
public class TitleServiceImplementations implements TitleServices{

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Autowired
	private TitleRepository titleRepository;

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Override
	public String addTitle(Title title) {
	try {
		titleRepository.save(title);
		return "New title saved";
	}catch(Exception e) {
		return e.getMessage();
	}
	}

	@Override
	public List<Title> getTitles() {
		return titleRepository.findAll();
	}

//<---------------------------------------------------------------------------------------------------------------------------------->

}
