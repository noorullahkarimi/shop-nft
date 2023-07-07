package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO authorities(username,authorities) VALUES (:username , :authorities)", nativeQuery = true)
	void addDataTwo(@Param("username") String username, @Param("authorities") String authorities);
	
	void deleteById(int id);

	Authorities findById(int id);
}
