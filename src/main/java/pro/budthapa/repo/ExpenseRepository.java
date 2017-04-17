package pro.budthapa.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pro.budthapa.domain.Expense;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long>{

}
