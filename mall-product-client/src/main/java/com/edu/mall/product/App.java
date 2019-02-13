package com.edu.mall.product;

import java.util.Collection;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.springframework.web.client.RestTemplate;

import com.edu.mall.product.web.LoadBalance;

//@SpringBootApplication
public class App {
	public static void main(String[] args) throws Exception {
		//ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		
		/**
		 * REST客户端
		 */
		/*String baseUrl = "http://127.0.0.1:8080";
		RestTemplate t = new RestTemplate();
		String resp = t.getForObject(baseUrl+"/soa/product/1", String.class);
		System.out.println(resp);*/
		
		String service = LoadBalance.choose();
		RestTemplate t = new RestTemplate();
		String resp = t.getForObject(service, String.class);
		System.out.println(resp);
		
		//context.close();
	}
}
