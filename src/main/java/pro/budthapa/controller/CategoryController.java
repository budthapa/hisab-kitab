/**
 * 
 */
package pro.budthapa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
	private static final String EDIT_CATEGORY = "category/editCategory";
	
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
		if(!bindingResult.hasErrors()){
			Category name=categoryService.checkDuplicateCategory(category.getCategoryName());
			if(name!=null){
				model.addAttribute(category);
				model.addAttribute("categoryExists","category.name.exists");
				return NEW_CATEGORY;
			}else{
				categoryService.saveCategory(category);
				return getAllCategories(model);
			}
		}		
		return NEW_CATEGORY;
	}
	
	@RequestMapping(value="/category/edit/{id}", method=RequestMethod.GET)
	public String editCategory(Model model, @PathVariable Long id){
		Category category=categoryService.findCategory(id);
		if(category!=null){
			model.addAttribute(category);
			return EDIT_CATEGORY;
		}
		return getAllCategories(model);
	}
	
	@RequestMapping(value="/category/edit/{id}", method=RequestMethod.POST)
	public String editCategory(@Valid Category category, BindingResult bindingResult, Model model,
			@PathVariable Long id){
		category.setId(id);
		model.addAttribute(category);
		if(!bindingResult.hasErrors()){
			Category name=categoryService.checkDuplicateCategory(category.getCategoryName());
			if(name!=null){
				model.addAttribute(category);
				model.addAttribute("categoryExists","category.name.exists");
				return EDIT_CATEGORY;
			}else{
				categoryService.updateCategory(category);
				return getAllCategories(model);
			}
		}
		
		return EDIT_CATEGORY;
	}
	
	@RequestMapping(value="/category/delete", method=RequestMethod.POST)
	public String deleteCategory(Model model){
		return showCategories(model);
	}
	
	
}
