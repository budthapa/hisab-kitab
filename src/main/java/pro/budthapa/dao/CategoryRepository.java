/**
 * 
 */
package pro.budthapa.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pro.budthapa.domain.Category;

/**
 * @author budthapa
 * Mar 8, 2017
 * 
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{

}
