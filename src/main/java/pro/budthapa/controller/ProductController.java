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
import org.springframework.web.bind.annotation.PathVariable;
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
	
	private static final String PRODUCT_INDEX="product/index";
	private static final String PRODUCT_ADD="product/addProduct";
	private static final String PRODUCT_EDIT="product/editProduct";
	
	@RequestMapping(value={"/product", "/product/all", "/product/edit/"}, method=RequestMethod.GET)
	public String index(Model model){
		List<Product> products=products();
		if(products.isEmpty()){
			model.addAttribute("productListEmpty", true);
		}
		model.addAttribute("products", products);
		return PRODUCT_INDEX;
	}
	
	@RequestMapping(value={"/product/new","/product/new/"}, method=RequestMethod.GET)
	public String addProduct(Product product, Model model){
		model.addAttribute("allCategories", categories());
		model.addAttribute("product", product);
		return PRODUCT_ADD;
	}
	
	@RequestMapping(value="/product/new", method=RequestMethod.POST)
	public String addProduct(@Valid Product product, BindingResult result, Model model){
		model.addAttribute("product", product);
		model.addAttribute("allCategories", categories());
				
		if(result.hasErrors()){
			return PRODUCT_ADD;
		}
		
		if(productService.findProduct(product.getName())!=null){
			model.addAttribute("productExists", true);
			return PRODUCT_ADD;
		}
		
		String productName=capitalizeFirstCharacter(product);
		
		product.setCreateDate(new Date());
		product.setName(productName);
		//TODO: upload image
		productService.saveProduct(product);
		model.addAttribute("productSaved",true);
		return PRODUCT_ADD;
	}
	
	@RequestMapping(value="/product/edit/{id}", method=RequestMethod.GET)
	public String editProduct(@PathVariable("id") Long id, Product product, Model model){
		product=productService.findProduct(id);
		model.addAttribute("allCategories", categories());			
		model.addAttribute("product",product);
		if(product!=null){
			return PRODUCT_EDIT;
		}
		
		List<Product> products=products();
		if(product==null || products.isEmpty()){
			model.addAttribute("productListEmpty", true);
		}
		model.addAttribute("products",products());
		return PRODUCT_INDEX;
	}
	
	@RequestMapping(value="/product/edit/{id}", method=RequestMethod.POST)
	public String editProduct(@Valid Product product, BindingResult result, Model model){
		model.addAttribute("product", product);
		model.addAttribute("allCategories", categories());
				
		if(result.hasErrors()){
			return PRODUCT_EDIT;
		}
		String productName=capitalizeFirstCharacter(product);
		Product prod=productService.findProduct(product.getName());
		
		if(prod!=null){			
			if(prod.getName().toLowerCase().equals(product.getName()) || (prod.getCategory().getId()!=product.getCategory().getId())){
				product.setCreateDate(new Date());
				product.setName(productName);
			}
		}

		productService.updateProduct(product);			
		model.addAttribute("productUpdated",true);
		return PRODUCT_EDIT;
	}
	
	private String capitalizeFirstCharacter(Product product){
		String productName=product.getName();
		StringBuilder sb=new StringBuilder(productName);
		productName=sb.replace(0, productName.length(), productName.substring(0, 1).toUpperCase()).append(productName.substring(1)).toString();
		return productName;
	}
	
	private List<Category> categories(){
		return categoryService.findAllCategories();
	}
	
	private List<Product> products(){
		return productService.findAllProducts();
		
	}
}
