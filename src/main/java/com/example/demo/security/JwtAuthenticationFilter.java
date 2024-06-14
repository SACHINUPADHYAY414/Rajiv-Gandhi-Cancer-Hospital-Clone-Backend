package com.example.demo.security;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.entities.Token;
import com.example.demo.repositories.TokenRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class) ;

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenRepository tokenRepository;


//<------------------------------------------------------------------------------------------------------------------------------------>


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestHeader = request.getHeader("Authorization");
		//for logging token
		logger.info(" Header : {}",requestHeader);
		String username = null;
		String token = null;

		if (requestHeader!=null && requestHeader.startsWith("Bearer")) {
			token = requestHeader.substring(7) ;

			try {
				username= this.jwtHelper.getUsernameFromToken(token);
			}catch (Exception e) {
				logger.info (e.getMessage());
				e. printStackTrace() ;
			}
		}else {
			logger.info("Invalid Header Value");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetaits= this.userDetailsService.loadUserByUsername(username) ;
			Optional<Token> toks = tokenRepository.findByToken(token);
			Token tt = toks.get();
			Boolean flag;
			if (toks.isEmpty()) {flag = false;}
			else if (!tt.getExpired() && !tt.getRevoked()) {flag =  true;}
			else {flag =  false;}
			Boolean validateToken = this.jwtHelper.validateToken(token, userDetaits);
			if (validateToken && flag) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetaits,null,userDetaits.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}else {
				logger.info("Validation fails");
			}
		}

		filterChain.doFilter(request, response);
	}


//<------------------------------------------------------------------------------------------------------------------------------------>

}
