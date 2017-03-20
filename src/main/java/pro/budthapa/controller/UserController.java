/**
 * 
 */
package pro.budthapa.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pro.budthapa.config.MailConfig;
import pro.budthapa.domain.Registration;
import pro.budthapa.domain.User;
import pro.budthapa.service.UserService;
import pro.budthapa.utility.PasswordHashGenerator;
import pro.budthapa.utility.Role;

/**
 * @author budthapa
 * Mar 18, 2017
 * 
 */
@Controller
public class UserController {
	
	private static final String INDEX_PAGE="user/index";
	private static final String REDIRECT_TO_INDEX="redirect:/user/all";
	private static final String ADD_NEW_USER="user/addUser";
	private static final String LOGIN_PAGE = "login";
	
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
	public String addUser(Model model, @Valid @ModelAttribute("register") Registration register, BindingResult result, 
			HttpServletRequest request){
		model.addAttribute("register",register);
		if(result.hasErrors()){
			//TODO: Validate name, email, password manually
			return ADD_NEW_USER;
		}
		String email=register.getEmail();
		Registration newReg=userService.findNewRegistration(email);
		User regUser=userService.findByEmail(email);
		
		if(newReg!=null || regUser!=null){
			model.addAttribute("userExists",true);
			return ADD_NEW_USER;
		}
		
		String uuid=UUID.randomUUID().toString();
		String activationLink="http://"+request.getServerName()+":"+request.getServerPort()+"/user/new/validate/"+uuid;
		System.out.println("server "+request.getServerName()+":"+request.getServerPort()+request.getContextPath());
		System.out.println("activation link ---> "+activationLink);
		PasswordHashGenerator hash=new PasswordHashGenerator();
		String plainPassword=register.getValidatedPassword();
		String hashedPassword=hash.hashPassword(plainPassword);
		register.setToken(uuid);
		register.setCreateDate(new Date());
		register.setValidatedPassword(hashedPassword);
		//TODO: get context path so that we can send the address in activation email
		try {
		//	mailConfig.sendEmail(register,plainPassword,activationLink);
			//TODO: Send email with thymeleaf template
//			mailConfig.sendVerificationEmail(register, activationLink);
		} catch (Exception e) {
			e.getMessage();
		}
		
		
		userService.save(register);
		return REDIRECT_TO_INDEX;
	}
	
	@RequestMapping(value="/user/new/validate/{token}", method=RequestMethod.GET)
	public String validateUserToken(@PathVariable String token, Model model, Registration register){
		
		//TODO: Set activation link time for 24 hours;
		register=userService.findRegisteredUser(token);
		
		if(register!=null){
			User user=new User();
			user.setName(register.getName());
			user.setEmail(register.getEmail());
			user.setPassword(register.getValidatedPassword());
			user.setJoinDate(register.getCreateDate());
			user.setRole(Role.ROLE_USER.toString());
			userService.save(user);
			userService.deleteValidatedRegistration(register);
			model.addAttribute("userValidateSuccess", true);
			return LOGIN_PAGE;
		}
		model.addAttribute("tokenInvalid", true);
		return LOGIN_PAGE;
	}
	
	
}
