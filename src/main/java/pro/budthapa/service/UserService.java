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
	
	/*
	public List<User> findAll(){
		List<User> userList=new ArrayList<>();
		userRepository.findAll().forEach(userList::add);
		return userList;
	}
*/
	/**
	 * @param register
	 */
	public Registration save(Registration register) {
		return userRepository.save(register);
	}
}
