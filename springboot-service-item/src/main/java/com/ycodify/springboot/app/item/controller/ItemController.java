package com.ycodify.springboot.app.item.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ycodify.springboot.app.item.models.Item;
import com.ycodify.springboot.app.item.models.Product;
import com.ycodify.springboot.app.item.models.service.ItemService;

@RefreshScope
@RestController
public class ItemController {

	private static Logger log = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
//	@Qualifier("serviceRestTemplate")
	private ItemService itemService;

	@Value("${configuration.text}")
	private String text;

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

	@GetMapping("/getConfig")
	public ResponseEntity<?> getConfig(@Value("${server.port}") String port) {
		log.info(text);

		Map<String, String> json = new HashMap<>();
		json.put("text", text);
		json.put("port", port);

		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("author.name", env.getProperty("config.author.name"));
			json.put("author.email", env.getProperty("config.author.email"));

		}

		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
}
