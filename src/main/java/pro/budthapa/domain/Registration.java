/**
 * 
 */
package pro.budthapa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author budthapa Mar 18, 2017
 * 
 */
@Entity
public class Registration {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	@Size(min=3, max=50, message="{name.invalid}")
	@Column(nullable=false)
	private String name;

	@Email
	@NotEmpty(message="{email.invalid}")
	@Column(nullable=false)
	private String email;
	
	@Column(name="password")
	private String validatedPassword;
	
	@Transient
	@Size(min=8,max=30, message="{password.invalid}")
	private String password;
	
	private String token;
	
	private Date createDate;

	public Registration() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getValidatedPassword() {
		return validatedPassword;
	}

	public void setValidatedPassword(String validatedPassword) {
		this.validatedPassword = validatedPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		this.validatedPassword=password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
