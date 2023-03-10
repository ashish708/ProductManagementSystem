package com.gl.pms.serviceImpl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gl.pms.dto.ProductDto;
import com.gl.pms.dto.Response;
import com.gl.pms.exception.ProductException;
import com.gl.pms.model.Category;
import com.gl.pms.model.Product;
import com.gl.pms.repository.CategoryRepo;
import com.gl.pms.repository.ProductRepo;
import com.gl.pms.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Object addproduct(ProductDto productDto) {

		try {
			logger.info("Inside addUser of ProductServiceImpl");

			Product product = new Product();
			Optional<Category> optional = Optional.empty(); // added for category ;list in product list
			product.setName(productDto.getName());
			product.setDescription(productDto.getDescription());
			product.setPrice(productDto.getPrice());
			optional = categoryRepo.findById(productDto.getCategory().getId());
			if (optional.isPresent()) {
				Category category = optional.get();
				product.setCategory(categoryRepo.findById(productDto.getCategory().getId()).orElseThrow(null));

			} else {
				Category category2 = new Category();
				category2.setName(productDto.getCategory().getName());
				categoryRepo.save(category2);
				product.setCategory(categoryRepo.findById(category2.getId()).orElseThrow(null));

			}
			Product saveProduct = productRepo.save(product);
			productDto.setId(saveProduct.getId());

			return new Response<Object>("ProductDetails Added Successfully", "1");

		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in addUser of ProductServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new ProductException(e.getMessage());

		}
	}

	@Override
	public Object deleteProductById(Long id) {
		try {
			logger.info("Inside addUser of ProductServiceImpl");
			Optional<Product> product = productRepo.findById(id);
			if (product.isEmpty()) {
				throw new ProductException("Product does not exist");
			}
			productRepo.deleteById(id);
			return new Response<Object>("Product Deleted Successfully", "1");

		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in deleteProductById of ProductServiceImpl : {0}",
					e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new ProductException(e.getMessage());
		}
	}

	@Override
	public Object getAllProduct() {

		try {
			logger.info("Inside addUser of ProductServiceImpl");
			List<Product> productFromRepo = productRepo.findAll();
			if (productFromRepo.size() == 0) {
				throw new ProductException("No Product Found");
			}
			return new Response<Object>("ProductDetails Fetched Successfully", "1", productFromRepo);
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in getAllProduct of ProductServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new ProductException(e.getMessage());
		}
	}

	@Override
	public Object getProductById(Long id) {
		try {
			logger.info("Inside addUser of ProductServiceImpl");
			Optional<Product> productFromRepo = productRepo.findById(id);
			if (productFromRepo.isEmpty()) {
				throw new ProductException("Product Does Not Exist");
			}
			ProductDto productDto = new ProductDto();
			Product product = productFromRepo.get();
			productDto.setId(product.getId());
			productDto.setName(product.getName());
			productDto.setPrice(product.getPrice());

			Category category = new Category();
			if (product.getCategory() != null) {
				category.setId(product.getCategory().getId());
				category.setName(product.getCategory().getName());
//				category.setProducts(product.getCategory().getProducts());
			}

			productDto.setCategory(category);
			return new Response<Object>("Product Feteched Successfully", "1", productFromRepo);

		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in getProductById of ProductServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new ProductException(e.getMessage());
		}
	}

	@Override
	public Object updateProduct(ProductDto productDto) {

		try {
			logger.info("Inside addUser of ProductServiceImpl");
			Product productFromRepo = productRepo.findById(productDto.getId()).orElseThrow();
			if (productFromRepo == null) {
				throw new ProductException("Product Doesn't exist");
			}
			productFromRepo.setId(productDto.getId());
			productFromRepo.setName(productDto.getName());
			productFromRepo.setPrice(productDto.getPrice());
			productFromRepo.setDescription(productDto.getDescription());

			Product savedProductDetails = productRepo.save(productFromRepo);
			Category category = categoryRepo.findById(productDto.getCategory().getId()).orElseThrow();
			category.setName(productDto.getCategory().getName());

			Category category2 = categoryRepo.save(category);

			productFromRepo.setCategory(category2);

			return new Response<Object>("product Updated", "1");
		}

		catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in addUser of UserDetailsServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new ProductException(e.getMessage());
		}
	}

	@Override
	public Object getCategoryDetails(String name) {
		try {
			logger.info("Inside addUser of ProductServiceImpl");
			Category category = new Category();
			Product product = productRepo.findByName(name);
			if (product != null) {
				category = categoryRepo.findById(product.getCategory().getId()).orElseThrow();
			} else {
				throw new ProductException("No Category Found");
			}
			return new Response<Object>("Category Fetched Successfully", "1", category);
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in getAllCategory of ProductServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new ProductException(e.getMessage());
		}
	}

	@Override
	public Object getSearchResults(String search) {

		try {
			logger.info("Inside addUser of ProductServiceImpl");
			// dynamic search products
			String like = "%" + search + "%";
			List<Product> product = productRepo.findBySearchName(like);
			if (!product.isEmpty()) {
				return new Response<Object>("Search result found", "1", product);
			} else {
				throw new ProductException("Product not found");
			}
		}

		catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in addUser of UserDetailsServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new ProductException(e.getMessage());
		}
	}

	@Override
	public Object getspecificProduct(String specificProduct) {
		try {
			logger.info("Inside addUser of ProductServiceImpl");
			Category category = categoryRepo.findByCategoryName(specificProduct);
			if (category != null) {
				List<Product> productList = productRepo.findByCategoryId(category.getId());
				return new Response<Object>("Products found", "1", productList);
			} else {
				throw new ProductException("Products not found");
			}

		}

		catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in addUser of UserDetailsServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new ProductException(e.getMessage());
		}
	}

}
