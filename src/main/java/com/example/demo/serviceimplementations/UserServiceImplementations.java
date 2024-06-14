package com.example.demo.serviceimplementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserServices;

@Service
public class UserServiceImplementations implements UserServices{

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsManager userDetailsManager;

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Override
	public User saveUser(User user) {
		try{
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setEnabled(true);
			userDetailsManager.updateUser(user);
			return user;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Integer getUserIdWithUP(String uid) {
		return userRepository.getIdByUsernameAndPassword(uid);
	}

	@Override
	public List<User> findActiveUser(String userId) {
		return userRepository.findByUserId(userId);
	}

	@Override
	public User modifyUserDetails(User user) {
		List<User> usrs = userRepository.findByUserId(user.getUserId());
		if(usrs.isEmpty()) {
			System.out.println("No user found with id "+user.getUserId());
			return null;
		}else {
			try {
				userRepository.save(user);
				return user;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
	}

	@Override
	public User modifyUserStatus(String userId) {
		List<User> usrs = userRepository.findByUserId(userId);
		if(usrs.isEmpty()) {
			System.out.println("No user found with id "+userId);
			return null;
		}else {
			try {
				for (User u : usrs) {
					u.setEnabled(false);
					userRepository.save(u);
				}
				return usrs.getFirst();
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
	}

	@Override
	public String updatePassword(String username, String password) {
	    List<User> users = userRepository.findByUsername(username);
	    if (users.isEmpty()) {
	        return "No user found with username: " + username;
	    } else {
	        try {
	            for (User user : users) {
	                user.setPassword(passwordEncoder.encode(password));
	                userDetailsManager.updateUser(user);
	            }
	            return "Password updated successfully.";
	        } catch (DataAccessException e) {
	            return "Error updating password: " + e.getMessage();
	        }
	    }
	}

	@Override
	public List<String> getPasswordWithUsername(String username) {
		return userRepository.getPasswordByUsername(username);
	}

//<---------------------------------------------------------------------------------------------------------------------------------->

}
