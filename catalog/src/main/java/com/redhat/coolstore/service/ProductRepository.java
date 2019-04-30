package com.redhat.coolstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.redhat.coolstore.model.Product;

@Repository
public class ProductRepository {

	//TODO: Autowire the jdbcTemplate here
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//TODO: Add row mapper here
	/*
	 *  Lambda expresssion 
	 */
	private RowMapper<Product> rowMapper = (rs, rowNum) -> new Product(rs.getString("itemId"),
			rs.getString("name"),
	        rs.getString("description"),
	        rs.getDouble("price"));

	//TODO: Create a method for returning all products
	public List<Product> readAll() {
		
		return jdbcTemplate.query("SELECT * from catalog", rowMapper);
		
	}

	//TODO: Create a method for returning one product
	public Product findById(String Id){
		
		return jdbcTemplate.queryForObject("SELECT * from catalog WHERE itemId='" + Id + "'", rowMapper);
	}
}
