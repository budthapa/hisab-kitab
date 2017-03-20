/**
 * 
 */
package pro.budthapa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author budthapa
 * Mar 20, 2017
 * 
 */
@Controller
public class HomeController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	@RequestMapping(value={"/login", "/login/"}, method=RequestMethod.GET)
	public String login(){
		return "login";
	}
}
