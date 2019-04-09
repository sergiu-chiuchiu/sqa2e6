package com.sqa.onlinepizzastore.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sqa.onlinepizzastore.entitites.AppCart;
import com.sqa.onlinepizzastore.entitites.AppOrder;
import com.sqa.onlinepizzastore.entitites.AppProduct;
import com.sqa.onlinepizzastore.entitites.AppUser;
import com.sqa.onlinepizzastore.entitites.CartItem;
import com.sqa.onlinepizzastore.entitites.CustomizeCartItem;
import com.sqa.onlinepizzastore.entitites.Customized;
import com.sqa.onlinepizzastore.services.AppCartService;
import com.sqa.onlinepizzastore.services.AppOrderService;
import com.sqa.onlinepizzastore.services.AppProductService;
import com.sqa.onlinepizzastore.services.AppUserService;
import com.sqa.onlinepizzastore.services.CartItemService;
import com.sqa.onlinepizzastore.services.CustomizedService;

@Controller
public class CartController {

	private final AppProductService appProductService;
	private final CartItemService cartItemService;
	private final ModelMapper modelMapper;
	private final AppCartService appCartService;
	private final AppUserService appUserService;
	private final CustomizedService customizedService;
	private final AppOrderService appOrderService;
	

	

public CartController(AppProductService appProductService, CartItemService cartItemService, ModelMapper modelMapper,
			AppCartService appCartService, AppUserService appUserService, CustomizedService customizedService,
			AppOrderService appOrderService) {
		super();
		this.appProductService = appProductService;
		this.cartItemService = cartItemService;
		this.modelMapper = modelMapper;
		this.appCartService = appCartService;
		this.appUserService = appUserService;
		this.customizedService = customizedService;
		this.appOrderService = appOrderService;
	}

	@RequestMapping(value = "shoppingcart/{productName}", method = RequestMethod.GET)
	public String buy(@PathVariable("productName") String productName, Model model, Principal principal) {
		AppUser appUser = appUserService.getLoggedInAppUserByPrincipal(principal);
		AppCart appCart = appCartService.getAppCartByActive('A');
		
		if(appCart==null) {
			appCart = new AppCart();
			appCart.setCustomerEmail(appUser.getEmail());
			appCart.setActive('A');
			appCartService.saveAppCart(modelMapper.map(appCart, AppCart.class));
			
			Set<CartItem> cartItems = new HashSet<CartItem>();
			appCart.setCartItems(cartItems);
		}
		
		Set<CartItem> cartItems = appCart.getCartItems();
		
		int index = this.exists(productName, cartItems);
		System.out.println(index);
		if(index==-1) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(appProductService.getAppProductByProductName(productName));
			cartItem.setQty(1);
			cartItem.setAppCart(appCart);
			cartItems.add(cartItem);
			cartItemService.saveCartItem(modelMapper.map(cartItem, CartItem.class));
		}
		else {
			List<CartItem> cartI = new ArrayList<>(cartItems); 
			int qty = cartI.get(index).getQty() + 1;
			cartI.get(index).setQty(qty);
			cartItems = new HashSet<>(cartI);
			cartItemService.updateCartItem(modelMapper.map(cartI.get(index), CartItem.class));
			
		}
		return "redirect:/menu/pizza";
	}
	
	@RequestMapping(value = "/shoppingcart", method = RequestMethod.GET)
	public String viewCartItems(Model model) {
		List<AppCart> carts = appCartService.getAppCartByAppUserEmail("admin@gmail.com");
		int index = findActiveCart(carts);
		AppCart activeCart = new AppCart();

		if(index!=-1) {
			activeCart = carts.get(index);
		}
		//List<AppProduct> list = new ArrayList<AppProduct>();
		List<CustomizeCartItem> cartItemsDiplay = new ArrayList<CustomizeCartItem>();
 		List<CartItem> cartItems = new ArrayList<>(activeCart.getCartItems());

		for(int i=0; i<cartItems.size(); i++) {
			if(cartItems.get(i).getCustomized()==null) {
			AppProduct app = cartItems.get(i).getProduct();
			CustomizeCartItem cci = new CustomizeCartItem();
			cci.setImage(app.getImageLink());
			cci.setName(app.getProductName());
			cci.setPrice(app.getPrice());
			cci.setQty(cartItems.get(i).getQty());
			cartItemsDiplay.add(cci);
			}else {
				Customized app = cartItems.get(i).getCustomized();
				CustomizeCartItem cci = new CustomizeCartItem();
				cci.setImage("");
				cci.setName(app.getCustomized_name());
				cci.setPrice(app.getCustomized_price());
				cci.setQty(cartItems.get(i).getQty());
				cartItemsDiplay.add(cci);	
			}
		}
		model.addAttribute("CartItems", cartItemsDiplay);
		
		return "shopping-cart";
	}
	
	
//------------------------------------ incercare customized shopping cart--------------------	
	
@RequestMapping(value = "shoppingcart/pizza/{customized_name}", method = RequestMethod.GET)
public String buyCust(@PathVariable("customized_name") String customizedName, Model model, Principal principal) {
	AppUser appUser = appUserService.getLoggedInAppUserByPrincipal(principal);
	AppCart appCart = appCartService.getAppCartByActive('A');	
	System.out.println("ias");
	if(appCart==null) {
		appCart = new AppCart();
		appCart.setCustomerEmail(appUser.getEmail());
		appCart.setActive('A');
		appCartService.saveAppCart(modelMapper.map(appCart, AppCart.class));
		
		Set<CartItem> cartItems = new HashSet<CartItem>();
		appCart.setCartItems(cartItems);
	}		
	
	Set<CartItem> cartItems = appCart.getCartItems();
	System.out.println(cartItems.size());
	int index = this.existsCust(customizedName, cartItems);	
	System.out.println(index);
	if(index==-1) {
		CartItem cartItem = new CartItem();
		System.out.println("produs");
		cartItem.setCustomized(customizedService.getCustomizedByCustomizedName(customizedName));
		cartItem.setQty(1);
		cartItem.setAppCart(appCart);
		cartItems.add(cartItem);
		cartItemService.saveCartItem(modelMapper.map(cartItem, CartItem.class));
	}
	else {
		List<CartItem> cartI = new ArrayList<>(cartItems); 
		int qty = cartI.get(index).getQty() + 1;
		cartI.get(index).setQty(qty);
		cartItems = new HashSet<>(cartI);
		cartItemService.updateCartItem(modelMapper.map(cartI.get(index), CartItem.class));		
	}
	return "redirect:/menu/pizza/viewCreations";
}
//	
//	@RequestMapping(value = "/shoppingcart", method = RequestMethod.GET)
//	public String viewCartItemsCust(Model model) {
//		List<AppCart> carts = appCartService.getAppCartByAppUserEmail("admin@gmail.com");
//		int index = findActiveCart(carts);
//		AppCart activeCart = new AppCart();
//		System.out.println(index);
//		if(index!=-1) {
//			activeCart = carts.get(index);
//		}
//		List<AppProduct> list = new ArrayList<AppProduct>();
//		List<CustomizeCartItem> cartItemsDiplay = new ArrayList<CustomizeCartItem>();
// 		List<CartItem> cartItems = new ArrayList<>(activeCart.getCartItems());
// 		System.out.println(cartItems.size());
//		for(int i=0; i<cartItems.size(); i++) {
//			
//			Customized app = cartItems.get(i).getCustomized();
//			CustomizeCartItem cci = new CustomizeCartItem();
//			cci.setImage("");
//			cci.setName(app.getCustomized_name());
//			cci.setPrice(app.getCustomized_price());
//			cci.setQty(cartItems.get(i).getQty());
//			cartItemsDiplay.add(cci);
//			
//		}
//		model.addAttribute("CartItems", cartItemsDiplay);
//		
//		return "shopping-cart";
//	}

private int existsCust(String customizedName, Set<CartItem> cart) {
	List<CartItem> cartItems = new ArrayList<>(cart); 
	
	for (int i = 0; i < cart.size(); i++) {
		if(cartItems.get(i).getProduct() ==null && cartItems.get(i).getCustomized().getCustomized_name().equals(customizedName)) {
			return i;
		}
	}
	return -1;
}

		
//------------------------------------ pana aici incercare customized
	
	
	private int exists(String productName,  Set<CartItem> cart) {
		List<CartItem> cartItems = new ArrayList<>(cart); 
		for (int i = 0; i < cart.size(); i++) {
			if (cartItems.get(i).getCustomized() ==null && cartItems.get(i).getProduct().getProductName().equals(productName)) {
				return i;
			}
		}
		return -1;
	}
	
//	@RequestMapping(value = "shoppingcart/{name}", method = RequestMethod.GET)
//	public String deleteCartItem(@PathVariable("name") String name, Model model) {
//		
//		AppCart appCart = appCartService.getAppCartByActive('A');
//		System.out.println(appCart.getCartItems().size());
//		if(appCart != null) {
//			
//			Set<CartItem> cartItems = appCart.getCartItems();
//			
//			int index = this.exists(name, cartItems);
//			System.out.println(index);
//			
//			if(index != -1) {
//				List<CartItem> cartI = new ArrayList<>(cartItems); 
//				CartItem ci = cartI.get(index);
//				cartI.remove(index);
//				cartItems = new HashSet<>(cartI);
//				appCart.setCartItems(cartItems);
//				System.out.println(cartI.size());
//				cartItemService.deleteCartItem(ci);
//				
//			}
//		}
//		return "shopping-cart";
//	}
	

	
	public int findActiveCart(List<AppCart> carts) {
		for(int i=0; i<=carts.size(); i++) {
			if(carts.get(i).getActive()=='A') {
				return i;
			}
		}
		return -1;
	}

	//-------------------------------creare order---------------------------------
	
	@RequestMapping(value="/shoppingcart",method=RequestMethod.POST)
	public String editsave(@ModelAttribute("appOrder") AppOrder appOrder, Model model, Principal principal) {		
	
		AppUser appUser = appUserService.getLoggedInAppUserByPrincipal(principal);			
		List<AppCart> carts = appCartService.getAppCartByAppUserEmail("admin@gmail.com");
		int index = findActiveCart(carts);
		System.out.println("halo");
		AppCart activeCart = new AppCart();
		System.out.println(index);
		if(index!=-1) {
			activeCart = carts.get(index);
		}			
		appOrder = new AppOrder();
		appOrder.setAppCart(activeCart);
		appOrderService.saveAppOrder(appOrder);
		activeCart.setActive('D');
		appCartService.updateAppCart(activeCart);		
		AppCart nextCart = new AppCart();
		nextCart.setActive('A');
		appCartService.saveAppCart(nextCart);
		
		return new String("redirect:/shoppingcart");
	}	
	
	
	
	
	
	
	
}
