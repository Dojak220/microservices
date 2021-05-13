package com.ycodify.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ycodify.springboot.app.item.clients.ProductClientRest;
import com.ycodify.springboot.app.item.models.Item;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements ItemService {

	private ProductClientRest clientFeign;
	
	@Override
	public List<Item> findAll() {
		return clientFeign.getProducts().stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findByID(Long id, Integer qty) {
		return new Item(clientFeign.getProductDetail(id), qty);
	}

}
