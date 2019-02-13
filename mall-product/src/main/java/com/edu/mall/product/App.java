package com.edu.mall.product;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.edu.mall.product.bean.Product;
import com.edu.mall.product.mapper.ProductMapper;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		
		/*ProductMapper pm = context.getBean(ProductMapper.class);
		Product p = new Product();
		p.setPname("耳机");
		p.setType("phone");
		p.setPrice(16.8);
		p.setCreateTime(new Timestamp(new Date().getTime()));
		pm.addProduct(p);*/
		
		//context.close();
	}
}
