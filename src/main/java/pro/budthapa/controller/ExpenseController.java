package pro.budthapa.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pro.budthapa.domain.Balance;
import pro.budthapa.domain.Expense;
import pro.budthapa.domain.ExpenseDetail;
import pro.budthapa.domain.Product;
import pro.budthapa.service.BalanceService;
import pro.budthapa.service.ExpenseService;
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
    
    @Autowired
    private ExpenseService expenseService;
    
    @Autowired
    private BalanceService balanceService;
    
    @GetMapping(value = {"/expense/all","/expense/all/"})
    public String index(){
        return EXPENSE_ALL;
    }

    @GetMapping("/expense/new")
    public String addExpense(Expense expense, Model model){
        LocalDate date1=LocalDate.now();
        String currentMonth = String.valueOf(date1.getMonth().getValue());
        
//        String currentMonth=date1.format(DateTimeFormatter.ofPattern("MM", Locale.US));
        Double remainingBalance = balanceService.getRemainingBalanceByMonth(currentMonth);
        model.addAttribute("products",productService.findAll());
        model.addAttribute("remainingBalance", remainingBalance);
        
        expenseAttribute(model,expense);
        return EXPENSE_NEW;
    }

    @PostMapping("/expense/new")
    public String addExpense(@Valid Expense expense, BindingResult result, Model model){
    	totalItem=0;
    	model.addAttribute("products",productService.findAll());
    	/*
    	model.addAttribute("users",userService.findAll());
        model.addAttribute("expense",expense);
        model.addAttribute("months", Months.months());
		*/
    	
        expenseAttribute(model,expense);
    	
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
    	
    	List<ExpenseDetail> expenseDetail = new ArrayList<>();
    	
    	List<String> list=expense.getPriceList();
    	productMap.forEach((k,v) -> {
    		if(list.get(k).isEmpty()){
    			model.addAttribute("priceNotSelected", true);
    			return;
    		}else{
    			//set the product id and price
    			ExpenseDetail ed=new ExpenseDetail();
    			ed.setExpense(expense);
    			
    			Product expenseProduct = new Product();
    	    	ed.setPrice(Double.parseDouble(list.get(k)));

    	    	expenseProduct.setId(Long.parseLong(v));
    	    	ed.setProduct(expenseProduct);
    	    	
    	    	expenseDetail.add(ed);
    		}
    	});
    	
    	if(result.hasErrors()){
    		return EXPENSE_NEW;
    	}
    	LocalDate ld=LocalDate.now();
    	int currentMonth=ld.getMonthValue();
    	String month=expense.getMonth();
    	Date date=expense.getExpenseDate();
    	
    	if(Integer.parseInt(month)>currentMonth|| date.after(new Date())){
    		model.addAttribute("invalidDate", true);
    		return EXPENSE_NEW;
    	}
    	
    	expense.setExpenseDetail(expenseDetail);
    	expenseService.save(expense);
    	    	
    	model.addAttribute("expenseSaved", true);
    	
    	Double remainingBalance = balanceService.getRemainingBalanceByMonth(String.valueOf(currentMonth));

    	Double newBalance = calculateRemainingBalance(expense.getAmount(), remainingBalance);
    	
    	setNewBalance(expense, remainingBalance, newBalance);
    	
    	model.addAttribute("remainingBalance", newBalance);
    	return EXPENSE_NEW;
        
    }

    private void expenseAttribute(Model model, Expense expense){
        model.addAttribute("users",userService.findAll());
        model.addAttribute("expense",expense);
        model.addAttribute("months", Months.months());
    }
    
    private Double calculateRemainingBalance(Double amount, Double balance){
    	Double remainingBalance = balance - amount;
    	return remainingBalance;
    }
    
    private void setNewBalance(Expense expense, Double remainingBalance, Double newBalance){
    	Balance balance = new Balance();
    	balance.setUser(expense.getUser());
		balance.setExpenseAmount(expense.getAmount());
		balance.setMonth(expense.getMonth());
		balance.setPreviousBalance(remainingBalance);
		balance.setRemainingBalance(newBalance);
		
    	balanceService.saveNewBalance(balance);
    }
}
