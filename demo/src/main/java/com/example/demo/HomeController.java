package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.user.dto.UserDTO;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		
		Map<String, Object> map = new HashMap<>();
		UserDTO userDTO = new UserDTO();
		return "home";
		
	}
}
