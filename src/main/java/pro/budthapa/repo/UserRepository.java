/**
 * 
 */
package pro.budthapa.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pro.budthapa.domain.User;

/**
 * @author budthapa
 * Mar 18, 2017
 * 
 */
@Repository
public interface UserRepository  extends CrudRepository<User, Long>{
	User findByEmail(String email);
}
