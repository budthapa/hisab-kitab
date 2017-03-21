/**
 * 
 */
package pro.budthapa.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pro.budthapa.domain.Category;
import pro.budthapa.domain.Product;
import pro.budthapa.service.CategoryService;
import pro.budthapa.service.ProductService;

/**
 * @author budthapa
 * Mar 21, 2017
 * 
 */
@Controller
public class ProductController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	private static final String PRODUCT_PAGE="/product/index";
	private static final String PRODUCT_FORM="/product/addProduct";
	
	@RequestMapping(value={"/product", "/product/all"}, method=RequestMethod.GET)
	public String index(){
		return PRODUCT_PAGE;
	}
	
	@RequestMapping(value={"/product/new","/product/new/"}, method=RequestMethod.GET)
	public String addProduct(Product product, Model model){
		model.addAttribute("allCategories", categories());
		model.addAttribute("product", product);
		return PRODUCT_FORM;
	}
	
	@RequestMapping(value="/product/new", method=RequestMethod.POST)
	public String addProduct(@Valid Product product, BindingResult result, Model model){
		model.addAttribute("product", product);
		model.addAttribute("allCategories", categories());
				
		if(result.hasErrors()){
			return PRODUCT_FORM;
		}
		
		if(productService.findProduct(product.getName())!=null){
			model.addAttribute("productExists", true);
			return PRODUCT_FORM;
		}
		
		String productName=product.getName();
		StringBuilder sb=new StringBuilder(productName);
		productName=sb.replace(0, productName.length(), productName.substring(0, 1).toUpperCase()).append(productName.substring(1)).toString();
		product.setCreateDate(new Date());
		product.setName(productName);

		productService.saveProduct(product);
		
		return PRODUCT_PAGE;
	}
	
	private List<Category> categories(){
		return categoryService.findAllCategories();
	}
}
