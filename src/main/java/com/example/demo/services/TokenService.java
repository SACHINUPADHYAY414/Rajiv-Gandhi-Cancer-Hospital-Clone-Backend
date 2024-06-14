package com.example.demo.services;

import java.util.Optional;

import com.example.demo.entities.Token;

public interface TokenService {
	public Void saveToken(String token);
	public Void revokeAllTokens(Integer userId,String token);
	public Integer getUserIdFromToken(String token);
	public Optional<Token> findWithToken(String token);
}
