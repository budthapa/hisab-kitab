/**
 * 
 */
package pro.budthapa.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pro.budthapa.domain.Product;

/**
 * @author budthapa
 * Mar 21, 2017
 * 
 */
@Repository
public interface ProductRepository extends CrudRepository<Product,Long>{

}
