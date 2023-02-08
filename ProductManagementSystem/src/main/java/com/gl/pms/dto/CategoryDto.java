package com.gl.pms.dto;

import java.util.List;

import com.gl.pms.model.Product;

import lombok.Data;

@Data
public class CategoryDto {
	private Long id;
	private String name;
	private List<Product> products;
}
