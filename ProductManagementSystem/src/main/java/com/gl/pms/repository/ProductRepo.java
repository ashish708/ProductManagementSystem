package com.gl.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.pms.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
