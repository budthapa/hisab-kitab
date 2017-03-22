/**
 * 
 */
package pro.budthapa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pro.budthapa.domain.Income;

/**
 * @author budthapa
 * Mar 22, 2017
 * 
 */
@Controller
public class IncomeController {
	
	private static final String INCOME_ALL = "income/index";
	private static final String INCOME_ADD = "income/addIncome";

	@RequestMapping(value={"/income/all","/income/all/"}, method=RequestMethod.GET)
	public String index(){
		return INCOME_ALL;
	}
	
	@RequestMapping(value={"/income/new"}, method=RequestMethod.GET)
	public String addIncome(Income income, Model model){
		model.addAttribute("income",income);
		return INCOME_ADD;
	}
}
