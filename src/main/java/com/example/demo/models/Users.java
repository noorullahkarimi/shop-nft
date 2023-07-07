package com.example.demo.models;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "users")
public class Users implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String password;
	private String username;
	private boolean enabled;
	private String imgUser;
	
	public String getImgUser() {
		return imgUser;
	}

	public void setImgUser(String imgUser) {
		this.imgUser = imgUser;
	}

	@Transient
	@JsonIgnore
	private MultipartFile File;

	public MultipartFile getFile() {
		return File;
	}

	public void setFile(MultipartFile file) {
		File = file;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Users() {
		super();
	}

	public Users(int id, String name, String password, String username, boolean enabled, String imgUser,
			MultipartFile file) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.username = username;
		this.enabled = enabled;
		this.imgUser = imgUser;
		File = file;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
