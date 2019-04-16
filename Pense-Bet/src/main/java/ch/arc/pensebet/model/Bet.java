package ch.arc.pensebet.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bet")

public class Bet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer id;
	
	@Column
	private String subject;
	
	@Column
	private Date creationDate;
	
	@Column
	private Date endingDate;
	
	@Column
	private float amount;
	
	@Column
	private Boolean result = null;

    @ManyToOne
    @JoinColumn
    private State state;

    @ManyToOne
    @JoinColumn
    private User owner;
    
    @OneToMany(mappedBy="bet", cascade = CascadeType.ALL)
    private List<Participation> parti_bets;
    
    @ManyToMany(mappedBy="bets", cascade = CascadeType.ALL)
    private List<User> users;
	
	public Bet() { }
	
	public Bet(String subject, Date endingDate, float amount, State state, User owner) {
		this.subject = subject;
		this.endingDate = endingDate;
		this.amount = amount;
		this.state = state;
		this.owner = owner;
		
		creationDate = new Date();
	}
	
	public Integer getId() {
		return id;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
}
