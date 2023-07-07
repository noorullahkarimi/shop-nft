package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="products")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String product_name;
	private int price;
	private String description;
	private String img;
	private int category;
	private int creator;
	private int collection;
	private int likes;
	private int showing ;
	private String urlNft;
	
	@Transient
	@JsonIgnore
	private MultipartFile file;
	
	public Products() {
		super();
	}

	public Products(int id, String product_name, int price, String description, String img, int category, int creator,
			int collection, int likes, int showing, String urlNft, MultipartFile file) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.price = price;
		this.description = description;
		this.img = img;
		this.category = category;
		this.creator = creator;
		this.collection = collection;
		this.likes = likes;
		this.showing = showing;
		this.urlNft = urlNft;
		this.file = file;
	}
	
	public String getUrlNft() {
		return urlNft;
	}

	public void setUrlNft(String urlNft) {
		this.urlNft = urlNft;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public int getCollection() {
		return collection;
	}

	public void setCollection(int collection) {
		this.collection = collection;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int like) {
		this.likes = like;
	}

	public int getShowing() {
		return showing;
	}

	public void setShowing(int showing) {
		this.showing = showing;
	}

	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
