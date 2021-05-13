package com.ycodify.springboot.app.item.models.service;

import java.util.List;

import com.ycodify.springboot.app.item.models.Item;

public interface ItemService {
	public List<Item> findAll();
	public Item findByID(Long id, Integer qty);
}
