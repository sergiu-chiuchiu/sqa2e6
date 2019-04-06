package com.sqa.onlinepizzastore.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sqa.onlinepizzastore.dto.AppUserDto;
import com.sqa.onlinepizzastore.dto.AppUserPaswordChangeDto;
import com.sqa.onlinepizzastore.dto.AppUserProfileDto;
import com.sqa.onlinepizzastore.dto.AppUserSignUpAdminDto;
import com.sqa.onlinepizzastore.dto.MessageDto;
import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.services.AppUserService;
import com.sqa.onlinepizzastore.util.EncryptedPasswordUtils;

@Controller
@RequestMapping(value = "/user")
public class UserManagementController {

	private final AppUserService appUserService;
	private final ModelMapper modelMapper;

	@Autowired
	public UserManagementController(AppUserService appUserService, ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
		this.appUserService = appUserService;
	}

	// Gender to be added
	@GetMapping
	public String getUserProfile(Model model, Principal principal) {
		AppUser appUser = appUserService.getLoggedInAppUserByPrincipal(principal);
		AppUserPaswordChangeDto appUserDto = new AppUserPaswordChangeDto();
		AppUserProfileDto appUserProfileDto = modelMapper.map(appUser, AppUserProfileDto.class);

		model.addAttribute("AppUser", appUserProfileDto);
		model.addAttribute("AppUserPasswords", appUserDto);
		return "UserProfile";
	}

	@PostMapping
	public String updateUserProfile(@Valid @ModelAttribute(value = "AppUser") AppUserProfileDto appUserDto,
			BindingResult bindingResult, Principal principal, RedirectAttributes rattrs,
			@ModelAttribute(value = "AppUserPasswords") AppUserPaswordChangeDto appUserDtoPasswords) {

		if (bindingResult.hasErrors()) {
			return "UserProfile";
		}

		AppUser appUserToUpdate = appUserService.getLoggedInAppUserByPrincipal(principal);

		if (!appUserDto.getUserName().equals(appUserToUpdate.getUserName())) {
			return "redirect:/auth/logout";
		}

		modelMapper.map(appUserDto, appUserToUpdate);
		appUserService.updateAppUser(appUserToUpdate);
		rattrs.addFlashAttribute("message",
				new MessageDto("text-success", "Your profile information has been updated successfully"));
		return "redirect:/user";
	}

	@PostMapping(value = "/changePass")
	public String updateUserPassword(
			@Valid @ModelAttribute(value = "AppUserPasswords") AppUserPaswordChangeDto appUserDtoPasswords,
			BindingResult bindingResult, Principal principal, RedirectAttributes rattr,
			@ModelAttribute(value = "AppUser") AppUserProfileDto appUserDto) {

		String oldPass = appUserDtoPasswords.getOldPassword();
		AppUser appUserToUpdate = appUserService.getLoggedInAppUserByPrincipal(principal);

		if (EncryptedPasswordUtils.checkPasswordMatch(oldPass, appUserToUpdate.getPassword())) {
			if (bindingResult.hasErrors()) {
				return "UserProfile";
			}

			if (appUserDtoPasswords.getPassword().equals(appUserDtoPasswords.getPasswordRepeat())
					&& appUserDtoPasswords.getPassword() != null) {
				modelMapper.map(appUserDtoPasswords, appUserToUpdate);

				appUserService.updateAppUserPassword(appUserToUpdate);
				rattr.addFlashAttribute("message",
						new MessageDto("text-success", "Your profile information has been updated successfully"));
			} else {
				rattr.addFlashAttribute("message", new MessageDto("text-warning", "The new passwords do not match"));
			}
		} else {
			rattr.addFlashAttribute("message", new MessageDto("text-danger", "Invalid old password"));
		}

		return "redirect:/user";
	}

	@DeleteMapping
	public String deleteAccount(@RequestParam(value = "message") String message, Principal principal, Model model) {
		System.out.println("Message " + message);
		AppUser appUserToDelete = appUserService.getLoggedInAppUserByPrincipal(principal);
		appUserService.deleteAppUser(appUserToDelete);
		return "redirect:/auth/logout";
	}

	@GetMapping(value = "/users")
	public String viewUsers(Model model) {
		model.addAttribute("AppUsers", appUserService.getAllUsers());
		return "ViewUsers";
	}

	@GetMapping(value = "/adduser")
	public String addUser(Model model) throws ParseException {
		AppUserDto appUserDto = new AppUserDto();
		Date defaultBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");
		appUserDto.setBirthDate(defaultBirthDate);
		model.addAttribute("AppUser", appUserDto);

		return "addUser";
	}

	// + validari parola
	@PostMapping(value = "/adduser")
	public String saveNewPrivilegedUser(@Valid @ModelAttribute(value = "AppUser") AppUserSignUpAdminDto appUserDto,
			BindingResult bindingResult, RedirectAttributes rattr) {
		// field validations
		if (bindingResult.hasErrors()) {
			return "adduser";
		}

		if (!appUserDto.getPassword().equals(appUserDto.getPasswordRepeat())) {
			rattr.addFlashAttribute("message", new MessageDto("text-warning", "Passwords do not match!"));
			return "redirect:/user/adduser";
		}

		appUserService.savePrivilegedAppUser(modelMapper.map(appUserDto, AppUser.class), appUserDto.getRoleName());
		return "adduser";
	}

}
