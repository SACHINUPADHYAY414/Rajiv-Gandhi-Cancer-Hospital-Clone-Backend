package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Title;

public interface TitleRepository  extends JpaRepository<Title,Integer>{
	public List<Title> findAllByTitleId(int titleId);
}
