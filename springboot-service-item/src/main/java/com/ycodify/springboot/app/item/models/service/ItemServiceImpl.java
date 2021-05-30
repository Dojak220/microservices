package com.ycodify.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ycodify.springboot.app.item.models.Item;
import com.ycodify.springboot.app.item.models.Product;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

	@Autowired
	public RestTemplate clientRest;
	
	@Override
	public List<Item> findAll() {
		List<Product> products = Arrays.asList(clientRest.getForObject("http://service-products/products", Product[].class));
		return products.stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findByID(Long id, Integer qty) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		
		Product product = clientRest.getForObject("http://service-products/products/{id}", Product.class, pathVariables);
		return new Item(product, qty);
	}

}
