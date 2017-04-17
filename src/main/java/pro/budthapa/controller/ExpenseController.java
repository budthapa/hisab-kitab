package pro.budthapa.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pro.budthapa.domain.Expense;
import pro.budthapa.service.IncomeService;
import pro.budthapa.service.ProductService;
import pro.budthapa.service.UserService;
import pro.budthapa.utility.Months;

/**
 * Created by budthapa on 4/8/17.
 */
@Controller
public class ExpenseController {
    private static final String EXPENSE_ALL="expense/index";
    private static final String EXPENSE_NEW="expense/addExpense";
    private int totalItem=0;
    @Autowired
    private UserService userService;

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = {"/expense/all","/expense/all/"})
    public String index(){
        return EXPENSE_ALL;
    }

    @GetMapping("/expense/new")
    public String addExpense(Expense expense, Model model){
        LocalDate date1=LocalDate.now();
        String currentMonth=date1.format(DateTimeFormatter.ofPattern("MM", Locale.US));
        
      //  String collectionOfCurrentMonth = incomeService.incomeOfCurrentMonth(currentDate);
        model.addAttribute("products",productService.findAll());

        expenseAttribute(model,expense);
        return EXPENSE_NEW;
    }

    @PostMapping("/expense/new")
    public String addExpense(@Valid Expense expense, BindingResult result, Model model){
    	totalItem=0;
    	model.addAttribute("products",productService.findAll());
    	model.addAttribute("users",userService.findAll());
        model.addAttribute("expense",expense);
        model.addAttribute("months", Months.months());

    	
    	
    	/*
    	 * Replace expenseDetail[0] in addExpense.html,
    	 * Add List<String> product in expense class; it will work 
    	 * 
    	 * List<String> exp=expense.getProduct();
    	   exp.forEach(e->{
    		 System.out.println("product is :"+e);
    		
    	  });
    	*/
    	
        List<String> product=expense.getProduct();
    	for(String e:product){
    		totalItem += e.length();
    	}

    	if(totalItem<1){
    		model.addAttribute("productNotSelected", true);
    		return EXPENSE_NEW;
    	}
    	
    	Map<Integer,String> productMap=new HashMap<>();
    	for(int i=0;i<product.size();i++){
    		if(!product.get(i).isEmpty()){
    			productMap.put(i, product.get(i));
    		}
    	}
    	
    	List<String> list=expense.getPriceList();
    	
    	productMap.forEach((k,v) -> {
    		if(list.get(k).isEmpty()){
    			model.addAttribute("priceNotSelected", true);
    			return;
    		}else{
    			//set the product id and price
    		}
    	});
    	
    	if(result.hasErrors()){
    		return EXPENSE_NEW;
    	}
    	
    	
    	
    	return EXPENSE_ALL;
        
    }

    private void expenseAttribute(Model model, Expense expense){
        model.addAttribute("users",userService.findAll());
        model.addAttribute("expense",expense);
        model.addAttribute("months", Months.months());
    }
}
