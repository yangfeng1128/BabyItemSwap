package com.babyitemswap.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.babyitemswap.spring.DOA.FormValidationGroup;
import com.babyitemswap.spring.DOA.User;
import com.babyitemswap.spring.service.UserService;

@Controller
public class LoginController {
	private UserService userService;
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	
	@RequestMapping("/messages")
	public String showMessages() {
		return "messages";
	}

	@RequestMapping("/adminpage")
	public String showAdmin(Model model) {

		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);

		return "adminpage";
	}

	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}

	@RequestMapping("/newaccount")
	public String shownewaccount(Model model) {
		model.addAttribute("user", new User());
		return "newaccount";
	}

	// @RequestMapping(value="/createaccount",method=RequestMethod.POST)
	// public String doCreate(@Valid User user, BindingResult result){
	// if(result.hasErrors())
	// {
	// return "newaccount";
	// }
	// user.setAuthority("user");
	// user.setEnabled(true);
	//
	//
	// try {
	// userService.create(user);
	// } catch (DuplicateKeyException e) {
	// result.rejectValue("username","DuplicateKey.user.username","This username has been used");
	// return "newaccount";
	// }
	//
	// return "accountcreated";
	// }

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String doCreate(@Validated(FormValidationGroup.class) User user, BindingResult result) {
		if (result.hasErrors()) {
			return "newaccount";
		}
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		if (userService.exist(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}

		userService.create(user);

		return "accountcreated";
	}
    
	
}
