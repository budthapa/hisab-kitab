/**
 * 
 */
package pro.budthapa.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pro.budthapa.domain.Income;
import pro.budthapa.domain.User;
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
		List<User> users=userService.findAll();
		model.addAttribute("users",users);
		model.addAttribute("income",income);
		
		Map<Integer,String> months=Months.months();
		model.addAttribute("months",months);
		
		return INCOME_ADD;
	}
	
	@RequestMapping(value={"/income/new"}, method=RequestMethod.POST)
	public String addIncome(@Valid Income income, BindingResult result, Model model){
		model.addAttribute("income",income);
		if(result.hasErrors()){
			List<User> users=userService.findAll();
			
			model.addAttribute("users", users);
			Map<Integer,String> months=Months.months();
			model.addAttribute("months",months);
			return INCOME_ADD;
			
		}
		incomeService.saveIncome(income);
		model.addAttribute("incomeSaved", true);
		return INCOME_ADD;
	}
	
	
}
