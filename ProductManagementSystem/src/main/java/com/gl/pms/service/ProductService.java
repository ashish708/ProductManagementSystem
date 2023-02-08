package com.gl.pms.service;

import org.springframework.stereotype.Service;

import com.gl.pms.dto.ProductDto;

@Service
public interface ProductService {

	public Object addproduct(ProductDto productDto);

	public Object deleteProductById(Long id);

	public Object getAllProduct();

	public Object getAllCategory();

	public Object getProductById(Long id);

	public Object updateProduct(ProductDto productDto);

}
