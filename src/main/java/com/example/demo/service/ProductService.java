package com.example.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.demo.models.Products;
import com.example.demo.models.Users;
import com.example.demo.repository.ProductRepository;
import com.example.demo.tools.ApacheBeanUtils;

@Service
public class ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Products> getAllProducts() {
		return productRepository.findAll();
	}

	public Products addProducts(Products product) {
		return productRepository.save(product);
	}
	
	public Products findByIdProduct(int id) {
		return productRepository.findById(id);
	}
	public List<Products> gettingFour(){
		return productRepository.getFourNFT();
	}
	
	@Transactional
	public String delete(int id){
		 productRepository.deleteById(id);
	return "";
	}

	@Transactional
	public Products uploadFile(Products product) throws IllegalAccessException, InvocationTargetException, IOException {
		
			if(!product.getFile().isEmpty()){//if it wasn"t empty mean we should save a new product
				String path = ResourceUtils.getFile("classpath:static/img").getAbsolutePath();
				System.out.println("the place of img saving in ->"+path);
				byte[] bytes = product.getFile().getBytes();
				System.out.println(bytes);
		//		String name = UUID.randomUUID() + "."
		//				+ Objects.requireNonNull(product.getFile().getContentType()).split("/")[1];
				String name = product.getFile().getOriginalFilename();
				System.out.println(name);
		//		String name = product.getFile().getOriginalFilename();
				Files.write(Paths.get(path + File.separator + name), bytes);
				product.setImg(name);
			}else{// if it was empty mean we should update	
				if(product.getId() != 0){//if th
					Products exist = productRepository.findById(product.getId());
					ApacheBeanUtils abu = new ApacheBeanUtils();
					abu.copyProperties(exist, product);
					return productRepository.save(exist);
				}
			}
		return this.productRepository.save(product);
	}
}
