package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.example.demo.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT f FROM Category f")
    List<Category> findAllCategory();
}