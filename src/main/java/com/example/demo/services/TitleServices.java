package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Title;

public interface TitleServices {
	public String addTitle(Title title);
	public List<Title> getTitles();
}
