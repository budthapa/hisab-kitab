/**
 * 
 */
package pro.budthapa.repo;

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

}
