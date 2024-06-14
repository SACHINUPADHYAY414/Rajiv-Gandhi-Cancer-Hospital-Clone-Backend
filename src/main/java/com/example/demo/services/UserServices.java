package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.User;

public interface UserServices {
	public User saveUser(User user);
	public List<User> findActiveUser(String userId);
	public User modifyUserDetails(User user);
	public User modifyUserStatus(String userId);
	public String updatePassword(String username, String password);
	public Integer getUserIdWithUP(String uid);
	public List<String> getPasswordWithUsername(String username);
}