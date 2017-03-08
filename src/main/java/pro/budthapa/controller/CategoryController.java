/**
 * 
 */
package pro.budthapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value="/categories", method=RequestMethod.GET)
	public List<Category> getAllCategories(){
		return categoryService.findAllCategories();
	}
	
	@RequestMapping(value="/category/new", method=RequestMethod.GET)
	public String addCategory(){
		return "category/addCategory";
	}
}
