package pro.budthapa.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by budthapa on 4/8/17.
 */
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message="{receipt.number.invalid}")
    @Column(name="receipt_number")
    private String receiptNo;

    @NotNull(message="{income.invalid.amount}")
    private Double amount;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull(message="{income.invalid.receiveddate}")
    private Date expenseDate;

    @NotBlank(message="{income.invalid.month}")
    private String month;

   // @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull(message="{income.invalid.name}")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String remarks;

	@OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    //@NotNull
    private List<ExpenseDetail> expenseDetail;

    @Transient
    private List<String> priceList;
    
    /*
     * temp solution, List<ExpenseDetail> expenseDetail; is passing null so I opt out for this solution
     */
    @Transient
    private List<String> product;
    
    
    public List<String> getProduct() {
		return product;
	}

	public void setProduct(List<String> product) {
		this.product = product;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<ExpenseDetail> getExpenseDetail() {
        return expenseDetail;
    }

    public void setExpenseDetail(List<ExpenseDetail> expenseDetail) {
        this.expenseDetail = expenseDetail;
    }

	public List<String> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<String> priceList) {
		this.priceList = priceList;
	}
    
    
}
