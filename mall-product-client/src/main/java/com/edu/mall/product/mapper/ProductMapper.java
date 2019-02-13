package com.edu.mall.product.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.edu.mall.product.bean.Product;

@Mapper
public interface ProductMapper {
	@Insert("insert into product (pname,type,price) values(#{pname},#{type},#{price})")
	public Integer addProduct(Product product);
	
	@Delete("delete from product where pid=#{pid}")
	public Integer deleteById(Integer id);
	
	@Update("update product set pname=#{pname},type=#{type},price=#{price} where pid=#{pid}")
	public Integer update(Product product);
	
	@Select("select pid,pname,type,price from product where pid=#{pid}")
	public Product getById(Integer id);
	
	@Select("select pid,pname,type,price from product ")
	public List<Product> queryByLists();
}
