package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;	
	private String productName;
	private String productDescription;
	
	@Lob
	@Column(length = 3048576)
	private byte[] productPicture;
	
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;

	
	public Product() {
		super();
	}


	public Product(Integer productId, String productName, String productDescription, byte[] productPicture,
			Category category) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPicture = productPicture;
		this.category = category;
	}


	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public byte[] getProductPicture() {
		return productPicture;
	}


	public void setProductPicture(byte[] productPicture) {
		this.productPicture = productPicture;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}
	
	

}