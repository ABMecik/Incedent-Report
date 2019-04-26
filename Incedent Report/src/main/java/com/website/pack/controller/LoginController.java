package com.website.pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.website.pack.model.User;
import com.website.pack.service.UserService;

@Controller
@SessionAttributes("User")
public class LoginController {
	
	@Autowired
	private UserService userService;

	@GetMapping("")
	public String hello1(Model mod, ModelMap model) {
		if(model.containsAttribute("User")) {
			mod.addAttribute("rok", model.get("User"));
			return "index";
		}
		else {
			mod.addAttribute("mode", "MODE_LOGIN");
			return "index";
		}
	}

	@GetMapping("/")
	public String hello2(Model mod, ModelMap model) {
		if(model.containsAttribute("User")) {
			mod.addAttribute("rok", model.get("User"));
			return "index";
		}
		else {
			mod.addAttribute("mode", "MODE_LOGIN");
			return "index";
		}
	}

	@RequestMapping("/welcome")
	public String Welcome(Model mod, ModelMap model) {
		if(model.containsAttribute("User")) {
			mod.addAttribute("rok", model.get("User"));
			return "index";
		}
		else {
			mod.addAttribute("mode", "MODE_LOGIN");
			return "index";
		}
	}


	@RequestMapping("/register")
	public String registration(Model mod, ModelMap model) {
		if(model.containsAttribute("User")) {
			mod.addAttribute("rok", model.get("User"));
			return "index";
		}
		else {
			mod.addAttribute("mode", "MODE_REGISTER");
			return "index";
		}
	}


	@RequestMapping("/login")
	public String login(Model mod, ModelMap model) {
		if(model.containsAttribute("User")) {
			mod.addAttribute("rok", model.get("User"));
			return "index";
		}
		else {
			mod.addAttribute("mode", "MODE_LOGIN");
			return "index";
		}


	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user,@RequestParam String passwordagain, BindingResult bindingResult, Model mod) {

		if (user.getUsername().equals("") || user.getEmail().equals("") || user.getPassword().equals("")) {
			mod.addAttribute("error", "Please fill in all fields");
			mod.addAttribute("mode", "MODE_REGISTER");
			return "index";
		}
		else if (userService.isUserPresent(user.getEmail())) {
			mod.addAttribute("error", "Email already exist");
			mod.addAttribute("mode", "MODE_REGISTER");
			return "index";
		}
		else if(user.getPassword().length()<=4) {
			mod.addAttribute("error", "The password must be longer than 4 characters.");
			mod.addAttribute("mode", "MODE_REGISTER");
			return "index";
		}
		else if (!user.getPassword().equals(passwordagain)) {
			mod.addAttribute("error", "Passwords not match");
			mod.addAttribute("mode", "MODE_REGISTER");
			return "index";
		}
		else if (bindingResult.hasErrors()) {
			mod.addAttribute("error", bindingResult.getAllErrors().toString());
			mod.addAttribute("mode", "MODE_REGISTER");
			return "index";
		} 
		else {

			userService.createUser(user);
			//userService.saveMyUser(user);
			mod.addAttribute("mode", "MODE_LOGIN");
			return "index";
		}



	}


	@PostMapping("/login")
	public String loginUser(@ModelAttribute User user, ModelMap model, Model mod) {
		if (userService.findByUsernameAndPassword(user.getUsername(), user.getPassword()) != null) {
			User logedUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
			model.addAttribute("User", logedUser);
			//model.addAttribute("Role", user.getUsertype());
			mod.addAttribute("rok", model.get("User"));
			return "homepage";

		} else {
			mod.addAttribute("error", "Invalid Username or Password");
			mod.addAttribute("mode", "MODE_LOGIN");
			return "index";
		}
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(SessionStatus status){
		status.setComplete();
		return "redirect:/";
	}
}
