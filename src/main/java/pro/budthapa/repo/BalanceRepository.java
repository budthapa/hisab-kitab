package pro.budthapa.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pro.budthapa.domain.Balance;

@Repository
public interface BalanceRepository extends CrudRepository<Balance, Long>{
	@Query("select max(b.remainingBalance) from Balance b where b.month = ?1")
	Double findRemainingBalanceByMonth(String month);
}
