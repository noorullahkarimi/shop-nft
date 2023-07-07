package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.demo.models.Products;
import com.example.demo.models.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.tools.ApacheBeanUtils;

@Service
public class UserService {
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepo){
		this.userRepository = userRepo;
	}
	
	public List<Users> getThree(){
		return userRepository.getThreeUser();
	}
	
	@Transactional
	public Users addUser(Users user) throws IllegalAccessException, InvocationTargetException, IOException {
		
			if(!user.getFile().isEmpty()){//if it wasn"t empty mean we should save a new product
				String path = ResourceUtils.getFile("classpath:static/img").getAbsolutePath();
				System.out.println("the place of img saving in ->"+path);
				byte[] bytes = user.getFile().getBytes();
				System.out.println(bytes);
		//		String name = UUID.randomUUID() + "."
		//				+ Objects.requireNonNull(product.getFile().getContentType()).split("/")[1];
				String name = user.getFile().getOriginalFilename();
				System.out.println(name);
		//		String name = product.getFile().getOriginalFilename();
				Files.write(Paths.get(path + File.separator + name), bytes);
				user.setImgUser(name);
			}else{// if it was empty mean we should update	
				if(user.getId() != 0){//if th
					Users exist = userRepository.findByid(user.getId());
					ApacheBeanUtils abu = new ApacheBeanUtils();
					abu.copyProperties(exist, user);
					return userRepository.save(exist);
				}
			}
		return this.userRepository.save(user);
	}

	public List<Users> getAllUsers(){
		return userRepository.findAll();
	}
	
	public Users findByID(int id){
		return userRepository.findByid(id);
	}
	@Transactional
	public String delete(int id){
		 userRepository.deleteById(id);
	return "";
	}
	
	public List<Users> getThreeCreatorCollections(Integer newArr[]){
		return userRepository.getThreeCreatorCollections(newArr[0], newArr[1], newArr[2]);
	} 
	
	public Users updateUsers(Users users) throws IllegalAccessException, InvocationTargetException{
		if(users.getUsername() != null){
			Users exist = userRepository.findByid(users.getId());
			ApacheBeanUtils abu = new ApacheBeanUtils();
			abu.copyProperties(exist, users);
			return userRepository.save(exist);
		}
		return userRepository.save(users);
	}
}
