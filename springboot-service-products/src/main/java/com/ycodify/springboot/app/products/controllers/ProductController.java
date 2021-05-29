package com.ycodify.springboot.app.products.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ycodify.springboot.app.products.models.entity.Product;
import com.ycodify.springboot.app.products.models.service.IProductsService;

@RestController
public class ProductController {
  
//	@Autowired
//	public Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	public IProductsService productService;
	
	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.findAll().stream().map(product -> {
//			product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			product.setPort(port);
			return product;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/products/{id}")
	public Product getProductDetail(@PathVariable Long id) {// throws Exception {
		Product product = productService.findById(id);
//		product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		product.setPort(port);

//		try {
//			Thread.sleep(2000L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		boolean ok = false;
//		if(!ok) {
//			throw new Exception("Error");
//		}
		return product;
	}
}
