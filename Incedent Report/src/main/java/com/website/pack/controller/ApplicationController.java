package com.website.pack.controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@SessionAttributes("User")
public class ApplicationController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/home")
	public String Home(ModelMap model, Model mod) {
		if(!model.containsAttribute("User")) {
			mod.addAttribute("mode", "MODE_LOGIN");
			return "index";
		}
		mod.addAttribute("rok", model.get("User"));
		return "index";
	}

}
