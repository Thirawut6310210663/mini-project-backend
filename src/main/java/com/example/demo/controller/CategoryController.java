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

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
	
	@Autowired
	CategoryRepository CategoryRepository;
	
	@PostMapping(value = "/category", consumes = { "multipart/form-data" })
	public ResponseEntity<Object> createPhoto(@RequestParam("body") String Categoryjson,
			@RequestParam("photo") MultipartFile photo) throws IOException {

		try {
			Category body = new ObjectMapper().readValue(Categoryjson, Category.class);

				body.setCategoryPicture(photo.getBytes());
		
				Category newCategory = CategoryRepository.save(body);
			return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error processing the photo.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/category")
	public ResponseEntity<Object> getCategory() {

		try {
			List<Category> CategoryList = CategoryRepository.findAllCategory();
			List<Category> Category = new ArrayList<>();

			for (Category row : CategoryList) {
				Integer categoryId = row.getCategoryId();
				String categoryName = row.getCategoryName();
				byte[] categoryPicture = row.getCategoryPicture();
				
				Category newCategory = new Category(categoryId, categoryName, categoryPicture);

				Category.add(newCategory);
			}
			return new ResponseEntity<>(Category, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<Object> getCategoryById(@PathVariable("categoryId") Integer categoryId) {

		try {

			Optional<Category> categoryFind = CategoryRepository.findById(categoryId);
			if (categoryFind.isPresent()) {
				Category category = categoryFind.get();


				return new ResponseEntity<>( category, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Category Not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<Object> deleteCategoryById(@PathVariable("categoryId") Integer categoryId) {

		try {

			Optional<Category> categoryFind = CategoryRepository.findById(categoryId);

			if (categoryFind.isPresent()) {


				CategoryRepository.delete(categoryFind.get());

				return new ResponseEntity<>("Delete Category Success.", HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Category not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PutMapping(value = "/category/{categoryId}", consumes = { "multipart/form-data" })
	public ResponseEntity<Object> updateCategory(@PathVariable("categoryId") Integer categoryId,
			@RequestParam("body") String Categoryjson, @RequestParam("photo") MultipartFile photo) throws IOException {

		try {

			Optional<Category>categoryFind = CategoryRepository.findById(categoryId);

			if (categoryFind.isPresent()) {
				Category body = new ObjectMapper().readValue(Categoryjson, Category.class);
				Category categoryUpdate = categoryFind.get();

				if (!photo.isEmpty()) {
					body.setCategoryPicture(photo.getBytes());
				}

				categoryUpdate.setCategoryName(body.getCategoryName());
				categoryUpdate.setCategoryPicture(body.getCategoryPicture());

				CategoryRepository.save(categoryUpdate);

				return new ResponseEntity<>(categoryUpdate, HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Category Not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
}
	
	




