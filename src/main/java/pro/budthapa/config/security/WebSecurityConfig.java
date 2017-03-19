/**
 * 
 */
package pro.budthapa.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author budthapa
 * Mar 19, 2017
 * 
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//permitting all the request temporarely
		//TODO: Implement spring security
		http.authorizeRequests()
		.anyRequest()
		.permitAll();
	}
}
