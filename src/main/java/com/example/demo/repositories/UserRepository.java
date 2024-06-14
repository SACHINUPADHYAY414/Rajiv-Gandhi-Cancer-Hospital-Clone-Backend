package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public List<User> findByUsername(String username);
	public List<User> findByUserId(String userId);
	public User getByUsername(String username);
	
//	@Query("SELECT u FROM User u WHERE u.username = :username AND password IS NOT NULL")
	public Optional<User> findAllByUsername(String username);

	@Query("SELECT u.password FROM User u WHERE u.username = :username")
	public List<String> getPasswordByUsername(String username);

	@Query("SELECT u.id FROM User u WHERE u.userId = :uid")
	public Integer getIdByUsernameAndPassword(String uid);

}