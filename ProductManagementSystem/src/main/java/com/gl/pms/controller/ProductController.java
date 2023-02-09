package com.gl.pms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.pms.dto.ProductDto;
import com.gl.pms.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@PostMapping("/addProduct")
	public ResponseEntity<Object> addproduct(@RequestBody ProductDto productDto) {
		logger.info("Request for addProductDetails :{}", productDto);
		return new ResponseEntity<Object>(productService.addproduct(productDto), HttpStatus.OK);
	}

	@GetMapping(value = "/getAllProductDetails")
	public ResponseEntity<Object> getAllProduct() {
		logger.info("Request for getAllUserDetails");
		return new ResponseEntity<Object>(productService.getAllProduct(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteById")
	public ResponseEntity<Object> deleteById(@RequestParam Long id) {
		logger.info("Request for deleteProductDetails");
		return new ResponseEntity<Object>(productService.deleteProductById(id), HttpStatus.OK);
	}

	@GetMapping(value = "/findById")
	public ResponseEntity<Object> getProductById(@RequestParam Long id) {
		logger.info("Request for findByIdProductDetails");
		return new ResponseEntity<Object>(productService.getProductById(id), HttpStatus.OK);
	}

	@PutMapping(value = "/updateProduct")
	public ResponseEntity<Object> updateProduct(@RequestBody ProductDto productDto) {
		logger.info("Request for updateProduct");
		return new ResponseEntity<Object>(productService.updateProduct(productDto), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getCategoryDetails")
	public ResponseEntity<Object> getCategoryDetails(@RequestParam String name) {
		logger.info("Request for getCategoryDetails");
		return new ResponseEntity<Object>(productService.getCategoryDetails(name), HttpStatus.OK);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<Object> searchCategory(@RequestParam String allCategory){
		logger.info("Request for searchCategory");
		return new ResponseEntity<Object>(productService.getSearchResults(allCategory), HttpStatus.OK);
	}
	
	@GetMapping(value = "/specificProduct")
	public ResponseEntity<Object> getspecificProduct(@RequestParam String specificProduct){
		logger.info("Request for searchCategory");
		return new ResponseEntity<Object>(productService.getspecificProduct(specificProduct),HttpStatus.OK);
	}

}
