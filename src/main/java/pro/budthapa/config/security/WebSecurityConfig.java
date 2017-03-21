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
		
		http.authorizeRequests().anyRequest().permitAll();
		/*
		http
		.authorizeRequests()
			.antMatchers(PATTERN)
			.permitAll()
		.and()
			.authorizeRequests()
			.antMatchers("/user/**", "/category/**").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.failureUrl("/login?error")
		.and()
			.logout()
			.logoutUrl("/logout")
			.deleteCookies("remember-me")
			.logoutSuccessUrl("/login")
			.permitAll()
			.and()
			.rememberMe();
			
		*/
	}
	
	private final String PATTERN[]={
		"/",	
		"/login",
		"/css/**",
		"/fonts/**",
		"/img/**",
		"/js/**",
		"/about"
	};
	
}
