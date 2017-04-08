/**
 * 
 */
package pro.budthapa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pro.budthapa.domain.Income;
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
		if(result.hasErrors()){
			model.addAttribute("users", userService.findAll());
			model.addAttribute("months",Months.months());
			return INCOME_ADD;
			
		}
		incomeService.saveIncome(income);
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
