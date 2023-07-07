package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.demo.models.Collection;
import com.example.demo.models.Products;
import com.example.demo.models.Users;
import com.example.demo.repository.CollectionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.tools.ApacheBeanUtils;

@Service
public class CollectionService {

	private CollectionRepository collectionRepository;
	private UserService userService;
	
	@Autowired
	public CollectionService(CollectionRepository collectionRepository, UserService userServices) {
		super();
		this.collectionRepository = collectionRepository;
		this.userService = userServices;
	}
	
	public List<Collection> getThreeCollection(){
//		List<Collection> collecttionList = collectionRepository.getThreeCollection();
//		Integer newArr[] = new Integer[3];
//		for(int i =0; i<collecttionList.size();i++){
//			System.out.println(collecttionList.get(i).getCreator());
//			newArr[i] = collecttionList.get(i).getCreator();
//		}
//		System.out.println(newArr[0]);
//		List<Users> usersList = userService.getThreeCreatorCollections(newArr);
//		List<Object> newList = Stream.concat(collecttionList.stream(), usersList.stream())
//                .collect(Collectors.toList());
//		return newList;
		return collectionRepository.getThreeCollection();
	}
	public List<Collection> getAllcollection(){
		return collectionRepository.findAll();
	}
	
	public Collection getCollection(int id){
		return collectionRepository.findById(id);
	}
	
	@Transactional
	public String delete(int id){
		 collectionRepository.deleteById(id);
	return "";
	}
	
//	public Collection addCollection(Collection collection){
//		return collectionRepository.save(collection);
//	}
	@Transactional
	public Collection addCollection(Collection collection) throws IOException, IllegalAccessException, InvocationTargetException {
		
		if(!collection.getFile().isEmpty()){
		String path = ResourceUtils.getFile("classpath:static/img").getAbsolutePath();
//		
		System.out.println("the place of img saving in ->"+path);
		byte[] bytes = collection.getFile().getBytes();
		System.out.println(bytes);
//		String name = UUID.randomUUID() + "."
//				+ Objects.requireNonNull(product.getFile().getContentType()).split("/")[1];
		String name = collection.getFile().getOriginalFilename();
		System.out.println(name);
		
//		String name = product.getFile().getOriginalFilename();
		Files.write(Paths.get(path + File.separator + name), bytes);
		collection.setCover(name);
		}else{
			if(collection.getId() != 0){//if th
				Collection exist = collectionRepository.findById(collection.getId());
				ApacheBeanUtils abu = new ApacheBeanUtils();
				abu.copyProperties(exist, collection);
				return collectionRepository.save(exist);
			}
		}
		return this.collectionRepository.save(collection);
	}

}
