package com.ycodify.springboot.app.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ycodify.springboot.app.item.models.Item;
import com.ycodify.springboot.app.item.models.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;

	@GetMapping("/items")
	public List<Item> getItems() {
		return itemService.findAll();
	}
	
	@GetMapping("/items/{id}/qty/{qty}")
	public Item getItem(@PathVariable Long id, @PathVariable Integer qty) {
		return itemService.findByID(id, qty);
	}
}
