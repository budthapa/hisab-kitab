/**
 * 
 */
package pro.budthapa.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pro.budthapa.domain.Income;

/**
 * @author budthapa
 * Apr 8, 2017
 * 
 */
@Repository
public interface IncomeRepository extends CrudRepository<Income, Long>{
	
	@Query("select sum(i.amount) from Income i where i.month = ?1") //class name must be upper case
	String findByMonth(String currentMonth);

}
