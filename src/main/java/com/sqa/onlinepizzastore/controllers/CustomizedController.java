package com.sqa.onlinepizzastore.controllers;


import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.sqa.onlinepizzastore.entitites.Customized;
import com.sqa.onlinepizzastore.entitites.Ingredient;
import com.sqa.onlinepizzastore.services.CustomizedService;
import com.sqa.onlinepizzastore.services.IngredientService;



@Controller
public class CustomizedController {


	 @Autowired
	 CustomizedService customizedService;
	 
	 @Autowired
	 IngredientService ingredientService;

	 @ModelAttribute("ingredients")
	 	public List<Ingredient> ingredients() {
	     return ingredientService.findAll();
	 	}
	 
	 
// VIEW CREATIONS-----------------------------------------
	 
	 	@RequestMapping(value = "/menu/pizza/viewCreations", method = RequestMethod.GET)
	 	public ModelAndView viewcustoms() {	 		
	 		ModelAndView mav = new ModelAndView("viewCreations"); //aici trebuie adaugat o pagina pentru a vizualiza ingredientele
	 		mav.addObject("customs", customizedService.findAll());
	 		return mav;
	 	}
	 	@ModelAttribute("customs")
	 	public List<Customized> customs() {
	     return customizedService.findAll();
	 	}	 
	 
	 
//---------------------- CREATE A PIZZA
		@RequestMapping(value="/menu/pizza/customizepizza", method=RequestMethod.GET)
		public String index(Model model) {
			model.addAttribute("ingredients", ingredientService.findAll());
			model.addAttribute("cust", new Customized());
			return "CustomizePizza";
		}
	 
		@RequestMapping(value = "/menu/pizza/customizepizza", method = RequestMethod.POST)
		public String addNewCust(@Valid @ModelAttribute("cust") Customized cust, BindingResult bindingResult, Model model) {
			if (bindingResult.hasErrors()) {
				System.out.println("BINDING RESULT ERROR");
				return "CustomizePizza";
			}
		
			model.addAttribute("customized_id", cust.getCustomized_id());
			model.addAttribute("customized_energy", cust.getCustomized_energy());
			model.addAttribute("ingredients", cust.getIngredients());
			model.addAttribute("customized_name", cust.getCustomized_name());
			model.addAttribute("countertops", cust.getCountertops());
			customizedService.save(cust);
			return new String("redirect:/menu/pizza/viewCreations");
			}
		
//-------------------UPDATE
		@RequestMapping(value="/menu/pizza/viewCreations/editCreation/{id}", method=RequestMethod.GET)
		public String viewC(@PathVariable Integer id, Model model) {
			Customized cust=customizedService.findOne(id);
			model.addAttribute("cust",cust);
			return "editCreation";
		}
	 
		@RequestMapping(value="/menu/pizza/viewCreations/editCreation",method=RequestMethod.POST)
		public String editsave(@Valid @ModelAttribute("cust") Customized c, BindingResult bindingResult, Model model) {		
			if (bindingResult.hasErrors()) {
				System.out.println("BINDING RESULT ERROR");
				return "editCreation";
			}
			Customized cust =customizedService.findOne(c.getCustomized_id());		
			cust.setCustomized_id(c.getCustomized_id());
			cust.setCustomized_name(c.getCustomized_name());
			cust.setCustomized_energy(c.getCustomized_energy());
			cust.setCountertops(c.getCountertops());
			cust.setIngredients(c.getIngredients());			
			customizedService.update(cust);
			return new String("redirect:/menu/pizza/viewCreations");
		}	
		
//---------------------DELETE
	 	@RequestMapping(value="/menu/pizza/viewCreations/deleteCreation/{id}",method=RequestMethod.GET)
		public ModelAndView delete(@PathVariable Integer id) {
			Customized cust=customizedService.findOne(id);		
			customizedService.delete(cust);
			return new ModelAndView("redirect:/menu/pizza/viewCreations");
		}

	 	
	 	
//		@RequestMapping(value = "/addCust", method = RequestMethod.POST)
//		public Customized addNewPost(@RequestBody Customized cust, BindingResult bindingResult, Model model) {
//			
//			model.addAttribute("customized_name", cust.getCustomized_name());
//			model.addAttribute("countertops", cust.getCountertops());
//			return customizedService.save(cust);
//		}
	 	
//----------------------	 	
//	 	@PostMapping(value="/")
//	 	public Customized saveCust(@RequestBody Customized saveCust) {
//	 		return customizedService.save(saveCust);
//	 	}
//	 	
//	 	
	 	
	 	
	 	
//	 	@PutMapping	(value="/updateCust/{id}")
//	 	public Customized saveMyCust(@PathVariable("id") Integer id, @RequestBody Customized updateCust) {
//	 		if(customizedService.customizedExists(id)){
//	 			Customized customizedToUpdate = customizedService.findOne(id);
//	 			modelMapper.map(updatedCustomized, customizedToUpdate);
//	 			return customizedService.update(customizedToUpdate);
//	 		}else {
//	 				return customizedService.update(updatedCustomized);
//	 	}
//}
	 	
	 	
//	 	
//		@RequestMapping(value="/editSaveCu", method= RequestMethod.POST)
//		public ModelAndView editsave(@ModelAttribute("customized") Customized c) {		
//			Customized customized = new Customized();
//			customized.setCustomized_name(c.getCustomized_name());
//			customized.setIngredients(c.getIngredients());
//			customized.setCountertops(c.getCountertops());			
//			customizedService.save(c);
//			return new ModelAndView("redirect:/viewsCustoms");
//		}
//	
	 
//-----------------------	 
	 	 
//	    @RequestMapping(value = { "/acesta" }, method = RequestMethod.POST)
//	    public String savePerson(Model model, //
//	            @ModelAttribute("customized") Customized customized) {
//	    	Integer customized_id = customized.getCustomized_id();
//	        String customized_name = customized.getCustomized_name();
//	        String countertops=customized.getCountertops();
//	        Set<Ingredient> ingredients=customized.getIngredients();
//	        customizedService.save(customized);	 
//	        
//	        if (customized_name != null && customized_name.length() > 0 //
//	                && countertops != null && countertops.length() > 0) {
//        	Customized newCustom = new Customized(customized_id, customized_name,/*	customized_date,customized_energy,*/
//	        			countertops,ingredients);
//	        	customizeds.add(newCustom);
//	 
//	            return "acesta";
//	        }	 
//	        model.addAttribute("errorMessage", null);
//	        return "acesta";
//	    }
	 
	 
	 
	 
	 
//-------------------- incercareeeee	 
//	 public Integer i=0;
//	 
//	 @RequestMapping(value="/custom", method = RequestMethod.GET)
//	    public String initForm(Model model) {
//	        Customized customized = new Customized();
//	        customized.setCustomized_id(i+1);
//	        customized.setCustomized_name("");
//	        customized.setCustomized_energy(null);
//	        model.addAttribute("customized", customized);
//	        initModelList(model);
//	        return "CustomizePizza"; //trebuie pagina asta pentru a visualiza produsul
//	    }
//	 
//	    @RequestMapping(value="/customm", method=RequestMethod.POST)
//	    public String submitForm(Model model, @Validated Customized customized, BindingResult result) {
//	        model.addAttribute("customized", customized);
//	        String returnVal = "successCustom";
//	        if(result.hasErrors()) {
//	            initModelList(model);
//	            returnVal = "customized";
//	        } else {
//	            model.addAttribute("customized", customized);
//	        }       
//	        return returnVal;
//	    }
//	 
//	    private void initModelList(Model model) {
//	        List<String> countertopList = new ArrayList<String>();
//	        countertopList.add("Italian");
//	        countertopList.add("Fluffy");
//	        countertopList.add("Multicereal"); 
//	        model.addAttribute("countertops", countertopList);
//	    }
//
//
//	 
////-----------------------------
//	 
//	 @RequestMapping(value="/cu")
//	 private ModelAndView CustomizedPage() {
//	 ModelAndView mav = new ModelAndView("customized-form");	  
//	 List<String> countertops = new ArrayList<String>();
//	 countertops.add("Italian");
//	 countertops.add("Fluffy");
//	 countertops.add("Multicereal");	  
//	 mav.addObject("countertop", countertops);
//	 mav.addObject("customized", new Customized());	  
//	 return mav;
//	 }
//	  
//	 
//	 @RequestMapping(value="/custom-result")
//	 private ModelAndView processCountertops(@ModelAttribute Customized customized) {
//	 ModelAndView mav = new ModelAndView("custom-result");
//	 mav.addObject("customized", customized);  
//	 return mav;
//	 }
//	 	 
//	 @RequestMapping(value = { "/custommm" }, method = RequestMethod.GET)
//	 public String selectCountertop(Model model) {	  
//	     Customized customized = new Customized();
//	     model.addAttribute("Customized", customized);	  
//	     String [] list = {"Italian" , "Fluffy", "Mutlticereal"} ;
//	     model.addAttribute("countertops", list);	  
//	     return "CustomizePizza";
//	 }
//
////	 ----------------------------------------
//	 
//	 
//	 @RequestMapping(value="/cust", method=RequestMethod.GET)
//	 public String getCustForm(){
//		 return "CustomizePizza";
//	 }
//	 	
//	 	@RequestMapping(value="/viewCustoms") 
//		public ModelAndView getAll() {		
//			List<Customized> list=customizedService.findAll();
//			return new ModelAndView("viewCustoms","list",list);
//		}
//		
//	 	
////	 	private static long cust_id = 0;
////	 	private static List<Customized> customs = new ArrayList<>();
////	 	 
////		@RequestMapping(value="/addCustom",method=RequestMethod.POST)
////	 	public ModelAndView createCustomized(@RequestBody Customized customized) {
////			customized.setCustomied_id(Integer.valueOf(cust_id++));
////			customs.add(customized);
////			return new ModelAndView("redirect:/CustomizePizza");
////		}
//		
//		
//		@RequestMapping(value="/editCustom/{id}")
//		public String edit (@PathVariable Integer id, ModelMap model) {		
//			Customized customized=customizedService.findOne(id);
//			model.addAttribute("Customized",customized);
//			return "acesta";
//		}
//		
//	
//		
//		@ModelAttribute("countertopList")
//		   public List<String> getCountertop() {
//		      List<String> countertopList = new ArrayList<String>();
//		      countertopList.add("Italian");
//		      countertopList.add("Fluffy");
//		      countertopList.add("Multicereals");
//		      return countertopList;
//		   }
//		
//		
//		@RequestMapping(value="/deleteCustom/{id}",method=RequestMethod.GET)
//		public ModelAndView delete(@PathVariable Integer id) {
//			Customized customized=customizedService.findOne(id);
//			customizedService.delete(customized);
//			return new ModelAndView("redirect:/viewCustoms");
//		}	
		
		
		 
//	 	@RequestMapping(value = "/customized", method = RequestMethod.GET)
//	 	public ModelAndView messages() {
//		 	Customized customized = new Customized();
//	 		ModelAndView mav = new ModelAndView("addCust"); //aici trebuie adaugat o pagina pentru a vizualiza ingredientele
//	 		mav.addObject("ingredients", ingredientService.findAll());
//	 		mav.addObject("customized", customized);
//	 		return mav;
//	 	}

}
