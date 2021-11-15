package com.ecommerce.bookstore.service;

import java.util.List;

import com.ecommerce.bookstore.model.Product;

public interface ProductService {
	List<Product> getAllProducts();

	Product getProductById(int productId);

	void deleteProductById (int productId);
	
	void saveProduct(Product product);

	List<Product> getByKeyword(String keyword);
}
