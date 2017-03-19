/**
 * 
 */
package pro.budthapa.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author budthapa
 * Mar 19, 2017
 * 
 */
public class PasswordHashGenerator {
	public String hashPassword(String plainPassword){
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		return encoder.encode(plainPassword);
	}
}
