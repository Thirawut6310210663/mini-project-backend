package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@PostMapping(value = "/product", consumes = { "multipart/form-data" })
	public ResponseEntity<Object> createPhoto(@RequestParam("body") String ProductIdjson,
			@RequestParam("photo") MultipartFile photo) throws IOException {

		try {
			Product body = new ObjectMapper().readValue(ProductIdjson, Product.class);

				body.setProductPicture(photo.getBytes());
		
				Product newProduct = productRepository.save(body);
			return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error processing the photo.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product")
	public ResponseEntity<Object> getProduct() {

		try {
			List<Product> ProductList = productRepository.findAllProduct();
			List<Product> Product = new ArrayList<>();

			for (Product row : ProductList) {
				Integer productId = row.getProductId();
				String productName = row.getProductName();
				String productDescription = row.getProductDescription();
				byte[] productPicture = row.getProductPicture();
				
				Product newProduct = new Product(productId, productName, productDescription,productPicture,null);

				Product.add(newProduct);
			}
			return new ResponseEntity<>(Product, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<Object> getProductById(@PathVariable("productId") Integer productId) {

		try {

			Optional<Product> productFind = productRepository.findById(productId);
			if (productFind.isPresent()) {
				Product product = productFind.get();


				return new ResponseEntity<>( product, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Product Not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@DeleteMapping("/product/{productId}")
	public ResponseEntity<Object> deleteProductById(@PathVariable("productId") Integer productId) {

		try {

			Optional<Product> productFind = productRepository.findById(productId);

			if (productFind.isPresent()) {


				productRepository.delete(productFind.get());

				return new ResponseEntity<>("Delete Product Success.", HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Product not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PutMapping(value = "/product/{productId}", consumes = { "multipart/form-data" })
	public ResponseEntity<Object> updateProduct(@PathVariable("productId") Integer productId,
			@RequestParam("body") String ProductIdjson, @RequestParam("photo") MultipartFile photo) throws IOException {

		try {

			Optional<Product>productFind = productRepository.findById(productId);

			if (productFind.isPresent()) {
				Product body = new ObjectMapper().readValue(ProductIdjson, Product.class);
				Product productUpdate = productFind.get();

				if (!photo.isEmpty()) {
					body.setProductPicture(photo.getBytes());
				}

				productUpdate.setProductName(body.getProductName());
				productUpdate.setProductDescription(body.getProductDescription());
				productUpdate.setProductPicture(body.getProductPicture());
				productUpdate.setCategory(body.getCategory());

				productRepository.save(productUpdate);

				return new ResponseEntity<>(productUpdate, HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Category Not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

		
	
}
