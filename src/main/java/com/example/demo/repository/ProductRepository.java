package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	 @Query("SELECT l FROM Product l")
	    List<Product> findAllProduct();

}