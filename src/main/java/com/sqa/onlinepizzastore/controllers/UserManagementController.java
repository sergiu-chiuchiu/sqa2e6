package com.sqa.onlinepizzastore.controllers;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sqa.onlinepizzastore.dto.AppUserDto;
import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.services.AppUserService;
import com.sqa.onlinepizzastore.util.EncryptedPasswordUtils;

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
		AppUser appUser = appUserService.getLoggedInAppUserByPrincipal(principal);
		AppUserDto appUserDto = new AppUserDto();
		model.addAttribute("AppUser", appUser);
		model.addAttribute("AppUserPasswords", appUserDto);
		return "UserProfile";
	}

	@PostMapping
	public String updateUserProfile(@ModelAttribute(value="AppUser") AppUserDto appUserDto, @ModelAttribute(value="AppUserPasswords") AppUserDto appUserDtoPasswords, Principal principal, Model model) {
		AppUser appUserToUpdate = appUserService.getLoggedInAppUserByPrincipal(principal);
		
		modelMapper.map(appUserDto, appUserToUpdate);
		appUserService.updateAppUser(appUserToUpdate);
		model.addAttribute("Message", "User information updated successfully");
		return "UserProfile";
	}
	
	@PostMapping(value = "/changePass")
	public String updateUserPassword(@ModelAttribute(value="AppUserPasswords") AppUserDto appUserDtoPasswords, Principal principal, Model model) {
		String oldPass = appUserDtoPasswords.getOldPassword();
		AppUser appUserToUpdate = appUserService.getLoggedInAppUserByPrincipal(principal);
		
		if(EncryptedPasswordUtils.checkPasswordMatch(oldPass, appUserToUpdate.getPassword())) {
			if(appUserDtoPasswords.getPassword().equals(appUserDtoPasswords.getPasswordRepeat()) && appUserDtoPasswords.getPassword() != null) {
				modelMapper.map(appUserDtoPasswords, appUserToUpdate);
				
				appUserService.updateAppUser(appUserToUpdate);
				model.addAttribute("Message", "Password Updated Successfully");
			} else {
				model.addAttribute("Danger", "New passwords do not match");
			}
		} else {
			model.addAttribute("Danger", "Old Password is incorrect");
		}

		model.addAttribute("AppUser", appUserToUpdate);
		return "UserProfile";
	}
	
	@PostMapping(value = "/deleteAccount")
	public String deleteAccount(@ModelAttribute(value="AppUserDelete") AppUserDto appUserMessage, Principal principal, Model model) {
		AppUser appUserToDelete = appUserService.getLoggedInAppUserByPrincipal(principal);
		appUserService.deleteAppUser(appUserToDelete);
		return "redirect:/auth/logout";
	}
		
	
}
