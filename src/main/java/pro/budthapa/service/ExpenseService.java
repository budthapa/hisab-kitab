package pro.budthapa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.budthapa.domain.Expense;
import pro.budthapa.repo.ExpenseRepository;

@Service
public class ExpenseService {
	@Autowired
	private ExpenseRepository expenseRepository;
	
	public Expense save(Expense expense){
		return expenseRepository.save(expense);
	}
}
