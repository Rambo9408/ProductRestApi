package com.home.practice.productrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.practice.productrest.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	
}
