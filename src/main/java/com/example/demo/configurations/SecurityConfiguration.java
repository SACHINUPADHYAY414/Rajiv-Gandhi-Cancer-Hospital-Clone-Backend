package com.example.demo.configurations;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.yaml.snakeyaml.internal.Logger;

import com.example.demo.security.JwtAuthenticationEntryPoint;
import com.example.demo.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private JwtAuthenticationEntryPoint entry;

	@Autowired
	private JwtAuthenticationFilter filter;

	@Autowired
	private LogoutConfiguration logoutConfiguration;

//<---------------------------------------------------------------------------------------------------------------------------------->
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors(cors -> cors
	            .configurationSource(request -> {
	                CorsConfiguration config = new CorsConfiguration();
	                try{
	                	config.setAllowedOriginPatterns(Collections.singletonList("*"));
		                config.setAllowedHeaders(Arrays.asList("X-AUTH-TOKEN", "Authorization","Access-Control-Allow-Headers", "Content-Type", "X-Requested-With","Location"));
		                config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS"));
		                config.setMaxAge(3600L);
		                config.setAllowCredentials(true);
		                return config;
	                }
	                catch(Exception e) {
	                	Logger.getLogger(e.getMessage());
	                	return null;
	                }
	            }))
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth ->	auth
				.requestMatchers("/login").permitAll()
				.requestMatchers("/updateUserPassword").permitAll()
				.requestMatchers("/register").permitAll()
				.requestMatchers("/getTitles").permitAll()
				.requestMatchers("/getgenders").permitAll()
				.requestMatchers("/getlistcities").permitAll()
				.requestMatchers("/getlistcountries").permitAll()
				.requestMatchers("/getlistdistricts").permitAll()
				.requestMatchers("/getliststates").permitAll()
				.anyRequest().authenticated())
			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.exceptionHandling(ex->ex.authenticationEntryPoint(entry))
		    .logout((logout) ->
			    logout.logoutUrl("/logout")
			    .addLogoutHandler(logoutConfiguration)
			    .logoutSuccessHandler((request, response, authentication) ->
			    SecurityContextHolder.clearContext()));

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

//<---------------------------------------------------------------------------------------------------------------------------------->
}
