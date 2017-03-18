/**
 * 
 */
package pro.budthapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pro.budthapa.domain.User;
import pro.budthapa.service.UserService;

/**
 * @author budthapa
 * Mar 18, 2017
 * 
 */
@Controller
public class UserController {
	
	private static final String INDEX_PAGE="/user/index";
	private static final String ADD_NEW_USER="/user/addUser";
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/user/all", method=RequestMethod.GET)
	public String index(User user, Model model){
		List<User> userList=userService.findAll();
		if(userList.isEmpty()){
			model.addAttribute("userListEmpty", true);
		}
		model.addAttribute("user", user);
		return INDEX_PAGE;
	}
	
	@RequestMapping(value="/user/add", method=RequestMethod.GET)
	public String addUser(){
		return ADD_NEW_USER;
	}
}
