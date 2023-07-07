package com.example.demo.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Authorities;
import com.example.demo.repository.AuthoritiesRepository;

@Service
public class AuthoritiesService {

	private AuthoritiesRepository authoritiesRepository;
	public AuthoritiesService(AuthoritiesRepository authoritiesRepositori) {
	this.authoritiesRepository = authoritiesRepositori;
	}
	
	public List<Authorities> getAllAuthorities(){
		return authoritiesRepository.findAll();
	}
	
	@Transactional
	public String delete(int id){
		 authoritiesRepository.deleteById(id);
	return "";
	}
	
//	@Transactional
	public void add(String username, String authorities){
		System.out.println("-----------------------at service---------------------");
		  authoritiesRepository.addDataTwo(username, authorities);
	}
	
	public Authorities findByIDAuthorities(int id){
		return authoritiesRepository.findById(id);
	}
	
	public Authorities save(Authorities authorities){
		return authoritiesRepository.save(authorities);
	}
}
