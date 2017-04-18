/**
 * 
 */
package pro.budthapa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pro.budthapa.domain.Balance;
import pro.budthapa.domain.Income;
import pro.budthapa.service.BalanceService;
import pro.budthapa.service.IncomeService;
import pro.budthapa.service.UserService;
import pro.budthapa.utility.Months;

/**
 * @author budthapa
 * Mar 22, 2017
 * 
 */
@Controller
public class IncomeController {
	
	private static final String INCOME_ALL = "income/index";
	private static final String INCOME_ADD = "income/addIncome";
	private static final String INCOME_EDIT = "income/editIncome";

	@Autowired
	private UserService userService;
	
	@Autowired
	private IncomeService incomeService;
	
	@Autowired
	private BalanceService balanceService;
	
	@RequestMapping(value={"/income/all","/income/all/"}, method=RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("incomeList", incomeService.findAll()); 
		
		return INCOME_ALL;
	}
	
	@RequestMapping(value={"/income/new"}, method=RequestMethod.GET)
	public String addIncome(Income income, Model model){
		incomeAttributes(model, income);
		return INCOME_ADD;
	}
	
	@RequestMapping(value={"/income/new"}, method=RequestMethod.POST)
	public String addIncome(@Valid Income income, BindingResult result, Model model){
		model.addAttribute("income",income);
		model.addAttribute("users", userService.findAll());
		model.addAttribute("months",Months.months());
		if(result.hasErrors()){
			return INCOME_ADD;
			
		}
		Income savedIncome = incomeService.saveIncome(income);
		
		Double balanceAmount = balanceService.getRemainingBalanceByMonth(savedIncome.getMonth());
		if(balanceAmount==null){
			balanceAmount=0.0;
		}
		
		Double newBalance = savedIncome.getAmount()+balanceAmount;
		
		Balance balance = new Balance();
		balance.setUser(savedIncome.getUser());
		balance.setExpenseAmount(0.0);
		balance.setMonth(savedIncome.getMonth());
		balance.setPreviousBalance(balanceAmount);
		balance.setRemainingBalance(newBalance);
		
		balanceService.saveNewBalance(balance);
		
		model.addAttribute("incomeSaved", true);
		return INCOME_ADD;
	}
	
	@GetMapping("/income/edit/{id}")
	public String editIncome(@PathVariable Long id, Model model){
		Income income=incomeService.findById(id);
		if(income!=null){
			incomeAttributes(model,income);
			return INCOME_EDIT;			
		}
		
		return "redirect:/income/all";
	}

	@PostMapping("/income/edit/{id}")
	public String editIncome(@PathVariable Long id, @Valid Income income, BindingResult result, Model model){
		model.addAttribute("income",income);
		if(result.hasErrors()){
			model.addAttribute("users", userService.findAll());
			model.addAttribute("months",Months.months());
			return INCOME_ADD;

		}
		income.setId(id);
		incomeService.updateIncome(income);
		model.addAttribute("incomeUpdated", true);
		return INCOME_ADD;
	}
	
	private void incomeAttributes(Model model, Income income){
		model.addAttribute("income", income);
		model.addAttribute("users",userService.findAll());
		model.addAttribute("months",Months.months());
	}
}
