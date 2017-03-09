/**
 * 
 */
package pro.budthapa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pro.budthapa.domain.Category;
import pro.budthapa.service.CategoryService;

/**
 * @author budthapa
 * Mar 8, 2017
 * 
 */

@Controller
public class CategoryController {
	
	private static final String SHOW_CATEGORIES="category/showCategory";
	private static final String NEW_CATEGORY = "category/addCategory";
	
	@Autowired
	CategoryService categoryService;
	
	public String showCategories(Model model){
		//getAllCategories(model);
		return SHOW_CATEGORIES;
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.GET)
	public String getAllCategories(Model model){
		model.addAttribute("categories",categoryService.findAllCategories());
		return showCategories(model);
	}
	
	@RequestMapping(value="/category/new", method=RequestMethod.GET)
	public String addCategory(Model model){
		model.addAttribute(new Category());
		return NEW_CATEGORY;
	}
	
	@RequestMapping(value="/category/new", method=RequestMethod.POST)
	public String addCategory(@Valid Category category, BindingResult bindingResult, Model model){
		model.addAttribute(category);
		if(!bindingResult.hasErrors()){
			categoryService.saveCategory(category);
			return getAllCategories(model);
		}
		return NEW_CATEGORY;
	}
	
	
}
