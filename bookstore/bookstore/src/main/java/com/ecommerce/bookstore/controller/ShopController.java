package com.ecommerce.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.bookstore.model.Product;
import com.ecommerce.bookstore.service.ProductService;

@Controller
public class ShopController {
	
	@Autowired
    private ProductService productService;
	

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/shopProduct")
    public String viewHomePage(Model model) {
        model.addAttribute("listProducts", productService.getAllProducts());
        return "shop";
	}
	
	 @RequestMapping(path = {"/search"})
	 public String home(Model model, @Param("keyword") String keyword) {
		 if(keyword!=null) {
			 //model.addAttribute("list",productService.getByKeyword(keyword));
			 List<Product> list = productService.getByKeyword(keyword);
			 model.addAttribute("listProducts", list);
			 model.addAttribute("keyword", keyword);
		 }
		 else {
			 List<Product> list = productService.getAllProducts();
			 model.addAttribute("list", list);
		}
		return "shop";
	 }
    
}
