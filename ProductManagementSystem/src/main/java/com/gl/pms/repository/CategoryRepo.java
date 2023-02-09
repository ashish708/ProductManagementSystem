package com.gl.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.pms.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{
	
	@Query(value = "select * from category where name=:specificProduct",nativeQuery = true)
	Category findByCategoryName(String specificProduct);

}
