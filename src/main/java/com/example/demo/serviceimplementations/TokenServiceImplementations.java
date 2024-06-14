package com.example.demo.serviceimplementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Token;
import com.example.demo.entities.TokenType;
import com.example.demo.repositories.TokenRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JwtHelper;
import com.example.demo.services.TokenService;

@Service
public class TokenServiceImplementations implements TokenService{

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenRepository tokenRepository;

//<---------------------------------------------------------------------------------------------------------------------------------->

	@Override
	public Void saveToken(String token) {
		Token saveToken = new Token();
		saveToken.setExpired(false);
		saveToken.setRevoked(false);
		saveToken.setTokenType(TokenType.BEARER);
		saveToken.setUser(userRepository.getByUsername(jwtHelper.getUsernameFromToken(token)));
		saveToken.setToken(token);
		tokenRepository.save(saveToken);
		return null;
	}

	@Override
	public Void revokeAllTokens(Integer userId,String token) {
		List<Token> tokens = tokenRepository.findAllValidTokenByUser(userId,token);
		if(tokens.isEmpty()) {
			return null;
		}else {
			tokens.forEach(t -> {
				t.setExpired(true);
				t.setRevoked(true);
			});
			tokenRepository.saveAll(tokens);
			return null;
		}
	}

	@Override
	public Integer getUserIdFromToken(String token) {
		return tokenRepository.getUserIdFromToken(token);
	}

	@Override
	public Optional<Token> findWithToken(String token) {
		return tokenRepository.findByToken(token);
	}

//<---------------------------------------------------------------------------------------------------------------------------------->

}
