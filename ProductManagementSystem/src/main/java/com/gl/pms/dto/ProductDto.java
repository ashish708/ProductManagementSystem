package com.gl.pms.dto;

import com.gl.pms.model.Category;

import lombok.Data;

@Data
public class ProductDto{

	private Long id;
	private String name;
	private Double price;
	private String Description;
	private Category category;
}
