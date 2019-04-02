package com.sqa.onlinepizzastore.controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

import com.sqa.onlinepizzastore.dto.AppUserDto;

import com.sqa.onlinepizzastore.entitites.AppRole;
import com.sqa.onlinepizzastore.entitites.AppSubscribe;
import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.entitites.Mail;
import com.sqa.onlinepizzastore.services.AppRoleService;
import com.sqa.onlinepizzastore.services.AppUserService;
import com.sqa.onlinepizzastore.util.WebUtils;

@Controller
@RequestMapping(value = "/auth")
public class UserAuthenticationController {

	private final AppUserService appUserService;
	private final ModelMapper modelMapper;
	private HttpServletRequest request;

	@Autowired
	public UserAuthenticationController(AppUserService appUserService, ModelMapper modelMapper,
			HttpServletRequest request) {
		super();
		this.request = request;
		this.modelMapper = modelMapper;
		this.appUserService = appUserService;
	}

	// could work if we auto logout user on username check
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
	public String saveNewuser(@ModelAttribute(value = "AppUser") AppUserDto appUserDto) {
		if (!appUserDto.getPassword().equals(appUserDto.getPasswordRepeat())) {
			return "SignUp";
		}
		appUserService.saveAppUserAsUser(modelMapper.map(appUserDto, AppUser.class));
		return "Menu";
	}

	@GetMapping(value = "/login")
	public String getLoginPage(Model model) throws ParseException {
		appUserService.createDefaultAdmin();
		appUserService.createDefaultOperator();
		return "LogIn";
	}

	@GetMapping(value = "/logoutSuccessful")
	public String getLogout() {
		return "redirect:/index";
	}

	@GetMapping(value = "/403")
	public String accessDenied(Model model, Principal principal) {
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);

			String message = "Hi " + principal.getName() //
					+ "<br /> You do not have permission to access this page!";
			model.addAttribute("message", message);
		}
		return "403";
	}

	@GetMapping(value = "/reqPassReset")
	public String resetPassword(Model model) throws MalformedURLException {
		AppUserDto appUserDto = new AppUserDto();
		model.addAttribute("AppUser", appUserDto);

		return "RequestPasswordReset";
	}

	@PostMapping(value = "/reqPassReset")
	public String processResetPassword(Model model, @ModelAttribute(value = "AppUser") AppUser appUserEmail)
			throws AddressException, MessagingException, MalformedURLException {
		AppUser appUser = appUserService.getAppUserByEmail(appUserEmail.getEmail());
		if (appUser == null) {
			System.out.println("Email Not found");
			model.addAttribute("message", "The e-mail you have provided does not exist!");
			return "RequestPasswordReset";
		}
		appUserService.sendPasswordResetEmail(appUser);

		model.addAttribute("message", "Email has been sent successfully!");
		return "redirect:/index";
	}

	@GetMapping(value = "/resetPassword/{token}")
	public String resetPasswordForm(Model model, @PathVariable("token") String token) {
		// Check if token exists
		AppUser appUser = appUserService.getAppUserByPasswordResetToken(token);
		if (appUser == null) {
			model.addAttribute("message", "Invalid token!");
			return "redirect:/index";
		}

		AppUserDto appUserDto = new AppUserDto();
		model.addAttribute("AppUser", appUserDto);

		return "ResetPassword";
	}

	@PostMapping(value = "/resetPassword/{token}")
	public String ProcessResetPasswordForm(Model model, @ModelAttribute(value = "AppUser") AppUserDto appUserDto,
			@PathVariable("token") String token) {
		// Check if token exists
		AppUser appUser = appUserService.getAppUserByPasswordResetToken(token);
		if (appUser == null) {
			model.addAttribute("message", "Invalid token!");
			return "redirect:/index";
		}
		
		if (!appUserDto.getPassword().equals(appUserDto.getPasswordRepeat())) {
///////////////////////////////////////////////////
			String test = request.getRequestURL().toString();
			System.out.println("sfdsfdssd " + test);
///////////////////////////////////////////////////			
			model.addAttribute("message", "Passwords do not match!");
			return "redirect:" + test;
		}
		appUserService.saveAppUserAsUser(modelMapper.map(appUserDto, AppUser.class));
		
		String test = request.getRequestURL().toString();
		System.out.println("sfdsfdssd " + test);
		
		model.addAttribute("message", "Password has been reset successfully!");
		return "redirect:/index";
	}

}
