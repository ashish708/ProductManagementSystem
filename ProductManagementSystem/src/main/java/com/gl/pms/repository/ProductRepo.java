package com.gl.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.pms.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
	
	@Query(value = "select * from products where name=:name",nativeQuery = true)
	Product findByName(String name);
	
	@Query(value = "select * from products where name like:search", nativeQuery = true)
	List<Product> findBySearchName(String search);
	
	@Query(value = "select * from products where category_id=:id", nativeQuery = true)
	List<Product> findByCategoryId(Long id);
	
}
