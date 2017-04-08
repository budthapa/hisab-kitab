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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 * @author budthapa
 * Mar 22, 2017
 * 
 */
@Entity
public class Income {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="{income.invalid.month}")
	private String month;
	
	@NotNull(message="{income.invalid.receiveddate}")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date receivedDate;
	
	@NotNull(message="{income.invalid.amount}")
	@NumberFormat
	private Double amount;
	
	@NotNull(message="{income.invalid.name}")
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@Column(length=512)
	private String remarks;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
