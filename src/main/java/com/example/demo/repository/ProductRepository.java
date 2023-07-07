package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {

Products findById(int id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM Products p ORDER BY likes DESC LIMIT 3 ")
	List<Products> getFourNFT();
	
	void deleteById(int id);
}
