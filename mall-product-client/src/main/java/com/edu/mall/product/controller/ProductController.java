package com.edu.mall.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.mall.product.bean.Product;
import com.edu.mall.product.mapper.ProductMapper;
import com.edu.mall.product.web.Response;

@RestController
public class ProductController {
	@Autowired
	private ProductMapper productMapper;
	
	@PostMapping("/soa/product/add")
	public Object add(Product p){
		Integer ret = productMapper.addProduct(p);
		return new Response("200", "ok", null);
	}
	
	@GetMapping("/soa/product/{id}")
	public Object getById(Integer id){
		Product p = productMapper.getById(id);
		if(p != null){
			return new Response("200", p.getPname(), null);
		}
		return new Response("200", "null", null);
	}
	
}
