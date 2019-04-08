package com.sqa.onlinepizzastore.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sqa.onlinepizzastore.dto.AppUserDto;
import com.sqa.onlinepizzastore.entitites.AppCart;
import com.sqa.onlinepizzastore.entitites.AppCartDetail;
import com.sqa.onlinepizzastore.entitites.AppProduct;
import com.sqa.onlinepizzastore.entitites.AppSubscribe;
import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.entitites.Ingredient;
import com.sqa.onlinepizzastore.entitites.Mail;
import com.sqa.onlinepizzastore.services.AppCartDetailService;
import com.sqa.onlinepizzastore.services.AppCartService;
import com.sqa.onlinepizzastore.repositories.AppProductRepository;
import com.sqa.onlinepizzastore.services.AppProductService;
import com.sqa.onlinepizzastore.services.IngredientService;

@Controller
public class AppProductController {

	private final ModelMapper modelMapper;
	 
	
	 @Autowired
	 AppProductService appProductService;
	 
	 

	public AppProductController(ModelMapper modelMapper, AppProductService appProductService,
			IngredientService ingredientService, AppCartService appCartService,
			AppCartDetailService appCartDetailService) {
		super();
		this.modelMapper = modelMapper;
		this.appProductService = appProductService;
		this.ingredientService = ingredientService;
		this.appCartService = appCartService;
		this.appCartDetailService = appCartDetailService;
	}

	@Autowired
	 IngredientService ingredientService;
	 
	 @Autowired
	 AppProductRepository appProductRepository;

	 @Autowired
	 AppCartService appCartService;
	 
	 @Autowired
	 AppCartDetailService appCartDetailService;
	 
		
//VIEW PIZZAS-----------------------------------------
	 
	 	@RequestMapping(value = "/menu/pizza", method = RequestMethod.GET)
	 	public ModelAndView viewCustoms() {	 		
	 		ModelAndView mav = new ModelAndView("Pizza"); 
	 		mav.addObject("pizzas", appProductRepository.findPizzas());
	 		return mav;
	 	}
	 	
	 	@ModelAttribute("pizzas")
	 	public List<AppProduct> pizzas() {
	     return appProductRepository.findPizzas();
	 	}
		 
	 	@ModelAttribute("ingredients")
		 public List<Ingredient> ingredients() {
		   return ingredientService.findAll();
		}
	 	
//VIEW DIPS-----------------------------------------	 	
		@RequestMapping(value = "/menu/dips", method = RequestMethod.GET)
	 	public ModelAndView viewDips() {	 		
	 		ModelAndView mav = new ModelAndView("Dips"); 
	 		mav.addObject("pizzas", appProductRepository.findDips());
	 		return mav;
	 	}
	 	@ModelAttribute("dips")
	 	public List<AppProduct> dips() {
	     return appProductRepository.findDips();
	 	}
//VIEW Drinks-----------------------------------------	 	
	 	@RequestMapping(value = "/menu/drinks", method = RequestMethod.GET)
	 	public ModelAndView viewDrinks() {	 		
	 		ModelAndView mav = new ModelAndView("Drinks"); 
	 		mav.addObject("drinks", appProductRepository.findDrinks());
	 		return mav;
	 	}
	 	@ModelAttribute("drinks")
	 	public List<AppProduct> drinks() {
	     return appProductRepository.findDrinks();
	 	}
//VIEW SODAS-----------------------------------------	 	

	 	@RequestMapping(value = "/menu/drinks/soda", method = RequestMethod.GET)
	 	public ModelAndView viewSodas() {	 		
	 		ModelAndView mav = new ModelAndView("Soda"); 
	 		mav.addObject("sodas", appProductRepository.findSodas());
	 		return mav;
	 	}
	 	@ModelAttribute("sodas")
	 	public List<AppProduct> sodas() {
	     return appProductRepository.findSodas();
	 	}
//VIEW COCKTAILS-----------------------------------------	 	
	 	
	 	@RequestMapping(value = "/menu/drinks/cocktail", method = RequestMethod.GET)
	 	public ModelAndView viewCocktails() {	 		
	 		ModelAndView mav = new ModelAndView("Cocktail"); 
	 		mav.addObject("cocktails", appProductRepository.findCocktails());
	 		return mav;
	 	}
	 	@ModelAttribute("cocktails")
	 	public List<AppProduct> cocktails() {
	     return appProductRepository.findCocktails();
	 	}
         //	 	@RequestMapping(value="/menu/pizza", method=RequestMethod.POST)
//		public String addProductToCart( @ModelAttribute(value="appProduct") AppProduct appProduct, BindingResult bindingResult, Model model) throws ParseException, MessagingException {
//			//AppProduct appProduct = new AppProduct();
//			//appProduct.setProductId(appProduct.getProductId());
//			AppCartDetail appCartDetail = new AppCartDetail();
////	 		
//	 		AppProduct appProduct1 =appProductService.getAppProductByProductName(appProduct.getProductName());
//	 		System.out.println(appProduct1.getProductId());
//	 		model.addAttribute("appProduct", appProduct1);
//			AppCart appCart = new AppCart();
//			appCart.setCustomerEmail("admin@gmail.com");
////			String name = appProduct1.getProductName();
//			appCartDetail.setAppCart(appCart);
//			appCartDetail.setCartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01"));
//			appCartDetail.setCartQty(1);
//			appCartDetail.setAppProduct(appProduct1);
//			appCartService.saveAppCart(modelMapper.map(appCart, AppCart.class));
//			appCartDetailService.saveAppCartDetail(modelMapper.map(appCartDetail, AppCartDetail.class));
//
//			return "shopping-cart";
//		}
}
