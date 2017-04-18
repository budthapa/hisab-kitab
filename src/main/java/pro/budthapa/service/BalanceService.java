package pro.budthapa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.budthapa.domain.Balance;
import pro.budthapa.repo.BalanceRepository;

@Service
public class BalanceService {
	@Autowired
	private BalanceRepository balanceRepository;
	
	public Double getRemainingBalanceByMonth(String month) {
		return balanceRepository.findRemainingBalanceByMonth(month);
	}

	public Balance saveNewBalance(Balance balance) {
		return balanceRepository.save(balance);
	}
}
