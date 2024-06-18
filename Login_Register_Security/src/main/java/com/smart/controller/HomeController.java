package com.smart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.smart.entity.User;
import com.smart.repository.UserRepo;
import com.smart.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	

	@Autowired
	private UserRepo userRepo;
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			User user = userRepo.findByEmail(email);
			m.addAttribute("user", user);
		}

	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	@GetMapping("/user/profile")
	public String profile(Principal p, Model m) {
		String email = p.getName();
		User user = userRepo.findByEmail(email);
		m.addAttribute("user", user);
		return "profile";
	}

	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user ) {
		
	//	System.out.println(user);
		
		
		User u=userService.saveUser(user);
		
		if(u!=null) {
			System.out.println("save success");
		} else {
			System.out.println("error in server");
		}
		return "redirect:/register";
	}
}
