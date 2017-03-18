/**
 * 
 */
package pro.budthapa.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pro.budthapa.domain.Registration;
import pro.budthapa.domain.User;
import pro.budthapa.service.UserService;

/**
 * @author budthapa
 * Mar 18, 2017
 * 
 */
@Controller
public class UserController {
	
	private static final String INDEX_PAGE="user/index";
	private static final String ADD_NEW_USER="user/addUser";
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/user/all", method=RequestMethod.GET)
	public String index(User user, Model model){
		/*List<User> userList=userService.findAll();
		if(userList.isEmpty()){
			model.addAttribute("userListEmpty", true);
		}*/
		model.addAttribute("user", user);
		return INDEX_PAGE;
	}
	
	@RequestMapping(value="/user/new", method=RequestMethod.GET)
	public String addUser(Registration register, Model model){
		model.addAttribute("register",register);
		return ADD_NEW_USER;
	}
	
	@RequestMapping(value="/user/new", method=RequestMethod.POST)
	public String addUser(@Valid Registration register, BindingResult result, Model model){
		model.addAttribute("register",register);
		if(result.hasErrors()){
			List<FieldError> err=result.getFieldErrors();
			
			for(FieldError e:err){
				if(e.getField().equals("name")){
					model.addAttribute("invalidName", true);
				}
				if(e.getField().equals("email")){
					model.addAttribute("invalidEmail", true);
				}
//				System.out.println("Error on object ---> "+e.getObjectName()+" on field ---> "+e.getField()+". Message ---> "+e.getDefaultMessage());
			}
			
			return ADD_NEW_USER;
		}
		
		register.setCreateDate(new Date());
		userService.save(register);
		return INDEX_PAGE;
	}
}
