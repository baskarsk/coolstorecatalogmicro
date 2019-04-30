package com.redhat.coolstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.redhat.coolstore.client.InventoryClient;
import com.redhat.coolstore.model.Inventory;
import com.redhat.coolstore.model.Product;

import feign.hystrix.FallbackFactory;

@Service
public class CatalogService {

	@Autowired
	private ProductRepository repository;
	
	//TODO: Autowire Inventory Client
	@Autowired
	private InventoryClient inventoryClient;
	
	public Product read(String Id){
		
		Product product = repository.findById(Id);
	    //TODO: Update the quantity for the product by calling the Inventory service -DONE
		product.setQuantity(inventoryClient.getInventoryStatus(Id).getQuantity());
		return product;
	}
	
	public List<Product> readAll(){
		List<Product> productList = repository.readAll();
	     //TODO: Update the quantity for the products by calling the Inventory service -DONE
		productList.parallelStream()
			.forEach(product ->{ product.setQuantity(inventoryClient.getInventoryStatus(product.getItemId()).getQuantity()); });
		return productList;
		
	}
	
	
		
	
	
}
