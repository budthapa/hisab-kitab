/**
 * 
 */
package pro.budthapa.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
		try {
			String hostname = InetAddress.getLocalHost().getHostAddress();
			System.out.println("host is "+hostname);
		} catch (UnknownHostException e) {
			e.getMessage();
		}
		return "index";
	}
	
	@RequestMapping(value={"/login", "/login/"}, method=RequestMethod.GET)
	public String login(){
		return "login";
	}
}
