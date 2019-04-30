package com.redhat.coolstore.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.redhat.coolstore.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class CatalogServiceTest {
	
	@Autowired
	private CatalogService catalogService;
	
	@Test
	public void read() {
		Product p1 = catalogService.read("329299");
		assertThat(p1).isNotNull();
		assertThat(p1.getName()).as("Verify Product Name").isEqualTo("Red Fedora");
		assertThat(p1.getQuantity()).as("Verify the quantity").isEqualTo(-1);
		
	}
	
	@Test
	public void readAll(){
		List<Product> productList = catalogService.readAll();
		assertThat(productList).isNotEmpty();
		assertThat(productList).isNotNull();
		List<String> names = productList.stream().map(Product::getName).collect(Collectors.toList());
		assertThat(names).contains("Red Fedora","Pebble Smart Watch","Ogio Caliber Polo");
	}
}
