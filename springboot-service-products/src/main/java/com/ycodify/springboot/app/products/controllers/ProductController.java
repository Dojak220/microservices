package com.ycodify.springboot.app.products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ycodify.springboot.app.products.models.entity.Product;
import com.ycodify.springboot.app.products.models.service.IProductsService;

@RestController
public class ProductController {
  
	@Autowired
	public IProductsService productService;
	
	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.findAll();
	}
	
	@GetMapping("/products/{id}")
	public Product getProductDetail(@PathVariable Long id) {
		return productService.findById(id);
	}
}
