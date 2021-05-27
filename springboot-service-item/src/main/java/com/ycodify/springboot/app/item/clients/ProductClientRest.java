package com.ycodify.springboot.app.item.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ycodify.springboot.app.item.models.Product;

@FeignClient(name = "products-service")
public interface ProductClientRest {

	@GetMapping("/products")
	public List<Product> getProducts();

	@GetMapping("/products/{id}")
	public Product getProductDetail(@PathVariable Long id);
}
