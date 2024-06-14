package com.example.demo.configurations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Token;
import com.example.demo.repositories.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutConfiguration implements LogoutHandler {

	@Autowired
	private TokenRepository tokenRepository;

//<---------------------------------------------------------------------------------------------------------------------------------->
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String requestHeader = request.getHeader("Authorization");
		Token token;
		Optional<Token> storedToken;
		try {
			if (requestHeader!=null && requestHeader.startsWith("Bearer")) {
				storedToken = tokenRepository.findAllByToken(requestHeader.substring(7));
				if (storedToken.isPresent()) {
					token = storedToken.get();
					token.setRevoked(true);
					token.setExpired(true);
					tokenRepository.save(token);
					return;
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
//<---------------------------------------------------------------------------------------------------------------------------------->
}