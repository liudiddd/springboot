package com.edu.mall.product.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;

public class LoadBalance {
	private static int index = 0;
	private static List<String> services;
	
	public LoadBalance(List<String> services){
		this.services = services;
	}
	
	{
		try {
			servicesDiscovery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String choose() throws Exception{
		if(services == null || services.size() == 0){
			servicesDiscovery();
		}
		String service = services.get(index);
		index++;
		if(index >= services.size()){
			index = 0;
		}
		return service;
	}
	
	private static void servicesDiscovery() throws Exception{
		/**
		 * zk服务发现
		 */
		CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.0.2:2181", new RetryOneTime(1000));
		client.start();
		client.blockUntilConnected();
		
		ServiceInstance<Object> instance = ServiceInstance.builder().name("product").address("192.168.0.2").port(2181).build();
		ServiceDiscovery<Object> serviceDiscovery = ServiceDiscoveryBuilder.builder(Object.class).client(client).basePath("/soa").build();
		Collection<ServiceInstance<Object>> list = serviceDiscovery.queryForInstances("product");
		if (services == null) services = new ArrayList<>();
		list.forEach((inst) -> {
			System.out.println(inst.getName() + " - " + inst.getAddress() + ":" + inst.getPort());
			String s = "http://" + inst.getAddress() + ":" + inst.getPort() + "/soa/product/1";
			services.add(s);
			System.out.println("=== " + s);
		});
	}
}
