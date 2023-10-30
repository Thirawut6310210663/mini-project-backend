package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoryId;	
	private String categoryName;
	@Lob
	@Column(length = 3048576)
	private byte[] categoryPicture;
	
	public Category() {
		super();
	}

	public Category(Integer categoryId, String categoryName, byte[] categoryPicture) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryPicture = categoryPicture;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public byte[] getCategoryPicture() {
		return categoryPicture;
	}

	public void setCategoryPicture(byte[] categoryPicture) {
		this.categoryPicture = categoryPicture;
	}

	

	
	
}