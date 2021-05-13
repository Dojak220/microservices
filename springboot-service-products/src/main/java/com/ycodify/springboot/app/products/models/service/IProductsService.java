package com.ycodify.springboot.app.products.models.service;

import java.util.List;

import com.ycodify.springboot.app.products.models.entity.Product;

public interface IProductsService {
	public List<Product> findAll();
	public Product findById(Long id);
}
