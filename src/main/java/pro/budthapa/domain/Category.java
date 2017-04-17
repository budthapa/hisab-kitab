/**
 * 
 */
package pro.budthapa.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author budthapa
 * Mar 8, 2017
 * 
 */
@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name", unique=true)
	@NotBlank(message="{category.invalid.name}")
	private String name;

	@OneToMany(mappedBy="category", cascade=CascadeType.ALL)
	private Set<Product> products;

	/*
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<ExpenseDetail> expenseDetail;
    */
	public Category(){}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	/*
	public List<ExpenseDetail> getExpenseDetail() {
		return expenseDetail;
	}

	public void setExpenseDetail(List<ExpenseDetail> expenseDetail) {
		this.expenseDetail = expenseDetail;
	}
	*/

	
}
