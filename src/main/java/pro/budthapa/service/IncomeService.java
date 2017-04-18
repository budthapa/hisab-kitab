/**
 * 
 */
package pro.budthapa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.budthapa.domain.Income;
import pro.budthapa.repo.IncomeRepository;

/**
 * @author budthapa
 * Apr 8, 2017
 * 
 */
@Service
public class IncomeService{

	@Autowired
	private IncomeRepository incomeRepository;
	/**
	 * @param income
	 */
	public Income saveIncome(Income income) {
		return incomeRepository.save(income);
	}
	/**
	 * @return
	 */
	public List<Income> findAll() {
		List<Income> list=new ArrayList<>();
		incomeRepository.findAll().forEach(list::add);
		return list;
	}
	/**
	 * @param id
	 * @return
	 */
	public Income findById(Long id) {
		return incomeRepository.findOne(id);
	}

    public Income updateIncome(Income income) {
		return incomeRepository.save(income);
    }

    public String getIncomeForCurrentMonth(String currentMonth) {
		return incomeRepository.findByMonth(currentMonth);
	}
	
}
