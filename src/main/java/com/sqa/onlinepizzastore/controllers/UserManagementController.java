package com.sqa.onlinepizzastore.controllers;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sqa.onlinepizzastore.dto.AppUserDto;
import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.services.AppUserService;

@Controller
@RequestMapping(value="/user")
public class UserManagementController {
	
	private final AppUserService appUserService;
	private final ModelMapper modelMapper;
	
	@Autowired
	public UserManagementController(AppUserService appUserService, ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
		this.appUserService = appUserService;
	}
	
	// add option to change username
	// Gender to be added
	@GetMapping
	public String getUSerProfile(Model model, Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userName = loginedUser.getUsername();
		
		AppUser appUser = appUserService.getAppUserByUserName(userName);
		model.addAttribute("AppUser", appUser);
		return "UserProfile";
	}

	@PostMapping
	public String updateUserProfile(@ModelAttribute(value="AppUser") AppUserDto appUserDto) {
		System.out.println("fdsfsdf " + appUserDto.getGender());
		
		AppUser appUserToUpdate = appUserService.getAppUserByUserName(appUserDto.getUserName());
		modelMapper.map(appUserDto, appUserToUpdate);
		appUserService.updateAppUser(appUserToUpdate);
		return "UserProfile";
	}
	
}
