package com.sqa.onlinepizzastore.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

import com.sqa.onlinepizzastore.dto.AppUserDto;

import com.sqa.onlinepizzastore.entitites.AppRole;

import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.services.AppRoleService;
import com.sqa.onlinepizzastore.services.AppUserService;
import com.sqa.onlinepizzastore.util.WebUtils;

@Controller
@RequestMapping(value = "/auth")
public class UserAuthenticationController {
	
	private final AppUserService appUserService;
	private final ModelMapper modelMapper;
	
	@Autowired
	public UserAuthenticationController(AppUserService appUserService, ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
		this.appUserService = appUserService;
	}
	 
	
	@GetMapping(value = "/signup")
	public String getSignUp(Model model) throws ParseException {
		AppUserDto appUserDto = new AppUserDto();
		Date defaultBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");  
		appUserDto.setBirthDate(defaultBirthDate);
		model.addAttribute("AppUser", appUserDto);

		return "SignUp";
	}
	
	// + validari parola
	@PostMapping(value = "/signup")
	public String saveNewuser(@ModelAttribute(value="AppUser") AppUserDto appUserDto) {
		if (!appUserDto.getPassword().equals(appUserDto.getPasswordRepeat())) {
			return "SignUp";
		}

		appUserService.saveAppUserAsUser(modelMapper.map(appUserDto, AppUser.class));
		return "Menu";
	}

	@GetMapping(value = "/login")
	public String getLoginPage(Model model) {

		return "LogIn";
	}
	
	@GetMapping(value = "/logoutSuccessful")
	public String getLogout(Model model) {
		model.addAttribute("Title", "Logout");
		return "index";
	}
	
	@GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
//            System.out.println("username: " + appUserService.getAppUserByUserName(loginedUser.getUsername()).getEmail());
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br /> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403";
    }

}
