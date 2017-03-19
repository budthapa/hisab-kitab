/**
 * 
 */
package pro.budthapa.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pro.budthapa.config.MailConfig;
import pro.budthapa.domain.Registration;
import pro.budthapa.domain.User;
import pro.budthapa.service.UserService;
import pro.budthapa.utility.PasswordHashGenerator;

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
	@Autowired
	MailConfig mailConfig;
	
	@RequestMapping(value="/user/all", method=RequestMethod.GET)
	public String index(User user, Model model){
		List<User> userList=userService.findAll();
		if(userList.isEmpty()){
			model.addAttribute("userListEmpty", true);
		}
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
			}
			
			return ADD_NEW_USER;
		}
		String uuid=UUID.randomUUID().toString();
		String activationLink="http://localhost:8085/user/new/token?="+uuid;
		PasswordHashGenerator hash=new PasswordHashGenerator();
		String plainPassword=register.getPassword();
		String hashedPassword=hash.hashPassword(plainPassword);
		register.setToken(uuid);
		register.setCreateDate(new Date());
		register.setPassword(hashedPassword);
		//send activation email
		try {
			mailConfig.sendEmail(register,plainPassword,activationLink);
			//TODO: Send email with thymeleaf template
			//mailConfig.sendVerificationEmail(register, activationLink);
		} catch (Exception e) {
			e.getMessage();
		}
		
		
		userService.save(register);
		return INDEX_PAGE;
	}
	
	
	
	
}
