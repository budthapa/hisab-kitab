package pro.budthapa.service;

import java.util.ArrayList;
import java.util.List;

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

	public List<Expense> findAllExpense() {
		List<Expense> allExpense = new ArrayList<>();
		expenseRepository.findAll().forEach(allExpense::add);
		return allExpense;
	}

	public Expense findExpense(Long id){
		return expenseRepository.findOne(id);
	}
}
