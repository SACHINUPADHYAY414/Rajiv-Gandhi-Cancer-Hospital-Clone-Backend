package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer>{

	@Query("""
			SELECT t FROM Token t INNER JOIN user u on t.user.id = u.id
			where u.id = :userId and (t.expired = false or t.revoked = false) and t.token!= :token
			""")
	public List<Token> findAllValidTokenByUser(Integer userId, String token);

	public Optional<Token> findByToken(String token);

	public Optional<Token> findAllByToken(String token);

	@Query("""
			SELECT t.user.id FROM Token t WHERE t.token = :token
			""")
	public Integer getUserIdFromToken(@Param("token") String token);
}
