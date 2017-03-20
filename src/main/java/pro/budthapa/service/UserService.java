/**
 * 
 */
package pro.budthapa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.budthapa.domain.Registration;
import pro.budthapa.domain.User;
import pro.budthapa.repo.UserRegistrationRepository;
import pro.budthapa.repo.UserRepository;

/**
 * @author budthapa
 * Mar 18, 2017
 * 
 */
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRegistrationRepository userRegistrationRepository;
	
	
	public List<User> findAll(){
		List<User> userList=new ArrayList<>();
		userRepository.findAll().forEach(userList::add);
		return userList;
	}

	/**
	 * @param register
	 */
	public Registration save(Registration register) {
		return userRegistrationRepository.save(register);
	}
	
	public Registration findRegisteredUser(String token){
		return userRegistrationRepository.findByToken(token);
	}
	
	public User save(User user){
		return userRepository.save(user);
	}
	
	public Registration findNewRegistration(String email){
		return userRegistrationRepository.findByEmail(email);
	}
	public User findByEmail(String email){
		return userRepository.findByEmail(email);
	}

	/**
	 * @param email
	 * @return
	 */
	public void deleteValidatedRegistration(Registration register) {
		userRegistrationRepository.delete(register);;
	}
}
