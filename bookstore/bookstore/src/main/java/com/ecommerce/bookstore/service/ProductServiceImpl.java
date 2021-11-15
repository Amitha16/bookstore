package com.ecommerce.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.bookstore.model.Product;
import com.ecommerce.bookstore.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
    private ProductRepository productRepository;
	

    @Override
    public List <Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @Override
    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }
    

	@Override
    public Product getProductById(int productId) {
        Optional < Product > optional = productRepository.findById(productId);
        Product product = null;
        if (optional.isPresent()) {
            product = optional.get();
        } else {
            throw new RuntimeException(" Product not found for id :: " + productId);
        }
        return product;
    }
    @Override
    public void deleteProductById(int productId) {
    	this.productRepository.deleteById(productId);
    }
    
    @Override
    public List<Product> getByKeyword(String keyword){
    	return productRepository.findByKeyword(keyword);
    }
}

