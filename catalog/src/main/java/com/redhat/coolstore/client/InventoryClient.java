package com.redhat.coolstore.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.redhat.coolstore.model.Inventory;

import feign.hystrix.FallbackFactory;

@FeignClient(name="inventory",fallbackFactory = InventoryClient.InventoryClientFallbackFactory.class)
public interface InventoryClient {
	
	@RequestMapping(method = RequestMethod.GET , value = "/services/inventory/{itemId}" , consumes = {MediaType.APPLICATION_JSON_VALUE} )
	Inventory getInventoryStatus(@PathVariable("itemId") String itemId);

	//TODO: Add Fallback factory here
	@Component
	public static class InventoryClientFallbackFactory implements FallbackFactory<InventoryClient> {

		@Override
		public InventoryClient create(Throwable arg0) {
			// TODO Auto-generated method stub
			return new InventoryClient(){

				@Override
				public Inventory getInventoryStatus(@PathVariable("itemId") String itemId) {
					// TODO Auto-generated method stub
					return new Inventory(itemId,-1);
				}
				
			};
		}
	}
}