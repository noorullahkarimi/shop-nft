package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long>{

	 @Modifying
//	@Query(nativeQuery = true, value = "SELECT u.id, u.name, u.username, c.name_collection, c.creator, c.cover FROM Users AS u  inner join collection AS c on u.id = c.creator limit 3 ; ")
	@Query(nativeQuery = true, value = "SELECT * FROM collection  limit 3 ; ")
	List<Collection> getThreeCollection();
//	 public List<Object[]> getTransictionsAndInstruments();
	
	Collection findById(int id);
	
	void deleteById(int id);
}
