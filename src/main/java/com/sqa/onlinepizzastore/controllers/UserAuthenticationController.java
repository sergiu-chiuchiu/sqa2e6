package com.sqa.onlinepizzastore.controllers;

import java.net.MalformedURLException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;

import com.sqa.onlinepizzastore.dto.AppUserDto;
import com.sqa.onlinepizzastore.dto.AppUserEmailDto;
import com.sqa.onlinepizzastore.dto.AppUserPassResetDto;
import com.sqa.onlinepizzastore.dto.AppUserSignUpDto;
import com.sqa.onlinepizzastore.dto.MessageDto;
import com.sqa.onlinepizzastore.entitites.AppUser;
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
	public String saveNewuser(Model model, RedirectAttributes rattrs, @Valid @ModelAttribute(value = "AppUser") AppUserSignUpDto appUserDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "SignUp";
		}
		// check password matching
		if (!appUserDto.getPassword().equals(appUserDto.getPasswordRepeat())) {
			model.addAttribute("message", new MessageDto("text-warning", "The passwords are not matching"));
			return "SignUp";
		}
		
		appUserService.saveAppUserAsUser(modelMapper.map(appUserDto, AppUser.class));
		rattrs.addFlashAttribute("message", new MessageDto("alert-success", "Your account has been successfully created!"));
		return "redirect:/index";
	}

	@GetMapping(value = "/login")
	public String postLoginPage() throws ParseException {
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
	public String resetPassword(Model model) {
		model.addAttribute("AppUser", new AppUserEmailDto());
		return "RequestPasswordReset";
	}

	@PostMapping(value = "/reqPassReset")
	public String processResetPassword(RedirectAttributes rattrs, Model model, @Valid @ModelAttribute(value = "AppUser") AppUserEmailDto appUserEmail
			, BindingResult bindingResult) throws AddressException, MessagingException, MalformedURLException {
		if (bindingResult.hasErrors()) {
			return "RequestPasswordReset";
		}
		
		AppUser appUser = appUserService.getAppUserByEmail(appUserEmail.getEmail());
		if (appUser == null) {
			model.addAttribute("message", new MessageDto("text-danger", "The e-mail you have provided does not exist!"));
			return "RequestPasswordReset";
		}
		appUserService.sendPasswordResetEmail(appUser);
		
		rattrs.addFlashAttribute("message", new MessageDto("alert-success", "Email has been sent successfully!"));
		return "redirect:/index";
	}

	@GetMapping(value = "/resetPassword/{token}")
	public String resetPasswordForm(RedirectAttributes rattr, Model model, 
			@PathVariable("token") String token) {
		// Check if token exists
		AppUser appUser = appUserService.getAppUserByPasswordResetToken(token);
		if (appUser == null) {
			rattr.addFlashAttribute("message", new MessageDto("alert-warning", "Invalid password reset link!"));
			return "redirect:/index";
		}

		AppUserPassResetDto appUserDto = new AppUserPassResetDto();
		model.addAttribute("AppUser", appUserDto);
		model.addAttribute("token", token);
		return "ResetPassword";
	}

	@PostMapping(value = "/resetPassword/{token}")
	public String ProcessResetPasswordForm(RedirectAttributes rattr, Model model, @Valid @ModelAttribute(value = "AppUser") AppUserPassResetDto appUserDto,
			BindingResult bindingResult, @PathVariable("token") String token) {
		// Check if token exists
		AppUser appUser = appUserService.getAppUserByPasswordResetToken(token);
		if (appUser == null) {
			rattr.addFlashAttribute("message", new MessageDto("alert-warning", "Invalid password reset link!"));
			return "redirect:/index";
		}
		
		String reqUrl = request.getRequestURL().toString();
		
		// field validations
		if (bindingResult.hasErrors()) {
			return "ResetPassword";
		}
		// Check if passwords are the same
		if (!appUserDto.getPassword().equals(appUserDto.getPasswordRepeat())) {
			rattr.addFlashAttribute("message", new MessageDto("text-warning", "Passwords do not match!"));
			return "redirect:" + reqUrl;
		}

		appUser.setPasswordResetToken(null);
		modelMapper.map(appUserDto, appUser);
		appUserService.updateAppUserPassword(appUser);

		rattr.addFlashAttribute("message", new MessageDto("alert-success", "Password has been reset successfully!"));
		return "redirect:/index";
	}

}
