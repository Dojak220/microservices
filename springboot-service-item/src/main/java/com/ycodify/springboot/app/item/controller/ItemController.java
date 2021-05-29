package com.ycodify.springboot.app.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ycodify.springboot.app.item.models.Item;
import com.ycodify.springboot.app.item.models.Product;
import com.ycodify.springboot.app.item.models.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("serviceFeign")
//	@Qualifier("serviceRestTemplate")
	private ItemService itemService;
	@GetMapping("/items")
	public List<Item> getItems() {
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "alterMethod")
	@GetMapping("/items/{id}/qty/{qty}")
	public Item getItem(@PathVariable Long id, @PathVariable Integer qty) {
		return itemService.findByID(id, qty);
	}
	
	public Item alterMethod(Long id, Integer qty) {
		Item item = new Item();
		Product product = new Product();
		
		item.setQty(qty);
		product.setId(id);
		product.setName("Camêra Sony");
		product.setPrice(500.00);
		item.setProduct(product);
		return item;
	}
}
