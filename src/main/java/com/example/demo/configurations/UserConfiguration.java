package com.example.demo.configurations;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class UserConfiguration {

//<---------------------------------------------------------------------------------------------------------------------------------->
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}


	@Bean
	UserDetailsManager userDetailsManager() {
		return new JdbcUserDetailsManager(dataSource());
	}

	@Bean
	DataSource dataSource() {
		return new DriverManagerDataSource("jdbc:postgresql://localhost:5432/patient","postgres","password");
	}
	
//<---------------------------------------------------------------------------------------------------------------------------------->
}