package com.ecommerce.bookstore.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ecommerce.bookstore.model.Product;
import com.ecommerce.bookstore.service.ProductService;

@Controller
public class ProductController {
	@Autowired
    private ProductService productService;
	

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10240000);
		return multipartResolver;
	}
		
    @GetMapping("/productManagement")
    public String viewHomePage(Model model) {
        model.addAttribute("listProducts", productService.getAllProducts());
        return "product_index";
    }
    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model) {
        // create model attribute to bind form data
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }
    @PostMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
    	//if (result.hasErrors())
		//	return "new_Product";
        productService.saveProduct(product);
        MultipartFile image = product.getProductImage();
		if (image != null && !image.isEmpty()) {
			Path path = Paths
					.get("C:/Users/LENOVO/Downloads/bookstore/bookstore/src/main/resource/images/products/"
							+ product.getProductId() + ".jpg");

			try {
				image.transferTo(new File(path.toString()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
        return "redirect:/productManagement";
    }
    
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") int productId, Model model) {
    	Product product = productService.getProductById(productId);
    	model.addAttribute("product", product);
    	return "update_product";
    }
    
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable (value = "id") int productId) {
    	this.productService.deleteProductById(productId);
    	return "redirect:/productManagement";
    }
}
