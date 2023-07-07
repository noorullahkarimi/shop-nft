package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="collection")
public class Collection implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int creator;
	private String name_collection;
	private String cover;
	
    public Users getUsersDetails() {
		return usersDetails;
	}

	public void setUsersDetails(Users usersDetails) {
		this.usersDetails = usersDetails;
	}

	@ManyToOne
    @JoinColumn(name="users")//
    private Users usersDetails;
	
	@JsonIgnore
	@Transient
	private MultipartFile file;
	 
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Collection() {
		super();
	}
	
	public Collection(int id, int creator, String name_collection, String cover, Users usersDetails, MultipartFile file) {
		super();
		this.id = id;
		this.creator = creator;
		this.name_collection = name_collection;
		this.cover = cover;
		this.usersDetails = usersDetails;
		this.file = file;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}

	public String getName_collection() {
		return name_collection;
	}

	public void setName_collection(String name_collection) {
		this.name_collection = name_collection;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
	
	
}
