package com.ecommerce.bookstore.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.bookstore.model.Login;
import com.ecommerce.bookstore.model.User;
import com.ecommerce.bookstore.service.LoginService;

@Controller
public class HomeController {
	
	@Autowired
    private LoginService loginService;

	
	@GetMapping( "/home")
	public String Home() {
		return "home";
	}
	
	@GetMapping( "/contactus")
	public String ContactUs() {
		return "contactus";
	}
	
	@GetMapping( "/shop")
	public String Shop() {
		return "shop";
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "user_login";
	}
	
 //   @PostMapping("/login")
 //   public String login(@ModelAttribute("user") User user ) {
 //   	UserDetails oauthUser = userService.loadUserByUsername(user.getEmail());
 //   	System.out.print(oauthUser);
 //   	if(Objects.nonNull(oauthUser)){ 
 //   		return "home";    
 //   	} 
 //   	else {
 //   		return "redirect:/login";
 //   	}
 //   }
	
	
//	@GetMapping("/user/register")
//	public String showRegistrationForm(Model model) {
//	    model.addAttribute("user", new User());
	     
//	    return "signup_form";
//	}
	
//	@PostMapping("/user/postregister")
//	public String processRegister(User user) {
//	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	    String encodedPassword = passwordEncoder.encode(user.getPassword());
//	    user.setPassword(encodedPassword);
	     
//	    userRepo.save(user);
	     
//	    return "register_success";
//	}
 
                                  
	@GetMapping("/admin/login")
	public String adminLogin(Model model) {
	    model.addAttribute("admin", new Login());
	     
	    return "admin_login";
	}
 
    @PostMapping("/admin/postlogin")
    public String login(@ModelAttribute("admin") Login admin ) {
    	Login oauthUser = loginService.login(admin.getUsername(), admin.getPassword());
    	System.out.print(oauthUser);
    	if(Objects.nonNull(oauthUser)){ 
    		return "admin_product";    
    	} 
    	else {
    		return "redirect:/admin/login";
    	}
    }
    	
    @GetMapping("/admin/logout")
    public String logout(){
        return "index";
    }
    
}