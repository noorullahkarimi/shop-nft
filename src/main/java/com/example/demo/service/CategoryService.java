package com.example.demo.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Categorys;
import com.example.demo.models.Users;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.tools.ApacheBeanUtils;

@Service
public class CategoryService {
	
	private CategoryRepository categorysRepository;
	
	@Autowired
	public CategoryService(CategoryRepository categoryRepository){
		this.categorysRepository=categoryRepository;
	}

	public List<Categorys> getAllCategorys(){
		return categorysRepository.findAll();
	}
	
	public Categorys addCategory(Categorys category){
		return categorysRepository.save(category);
	}
	
	public Categorys editById(int id){
		return categorysRepository.findById(id);
	}
	
	public Categorys updateCategory(Categorys category) throws IllegalAccessException, InvocationTargetException{
		if(category.getId() != 0){
			Categorys exist = categorysRepository.findById(category.getId());
			ApacheBeanUtils abu = new ApacheBeanUtils();
			abu.copyProperties(exist, category);
			return categorysRepository.save(exist);
		}
		return categorysRepository.save(category);
	}
	
	@Transactional
	public String delete(int id){
		 categorysRepository.deleteById(id);
	return "";
	}
	
}
