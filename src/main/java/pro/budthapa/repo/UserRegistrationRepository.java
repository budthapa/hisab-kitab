/**
 * 
 */
package pro.budthapa.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pro.budthapa.domain.Registration;

/**
 * @author budthapa
 * Mar 18, 2017
 * 
 */
@Repository
public interface UserRegistrationRepository extends CrudRepository<Registration,Long>{
	Registration findByToken(String token);
	Registration findByEmail(String email);
}
