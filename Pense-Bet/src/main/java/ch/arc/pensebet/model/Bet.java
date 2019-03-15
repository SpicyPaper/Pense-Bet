package ch.arc.pensebet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bet")

public class Bet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	public Integer id;
	
	@Column
	public String subject;
	
	@Column
	public Date creationDate;
	
	@Column
	public Date endingDate;
	public float amout;
	
	@Column
	public Boolean result = null;

    @ManyToOne
    @JoinColumn
	public State state;
	
	public Bet() { }
	
	public Bet(String subject, Date endingDate, float amout, State state) {
		this.subject = subject;
		this.endingDate = endingDate;
		this.amout = amout;
		this.state = state;
		
		creationDate = new Date();
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
	public float getAmout() {
		return amout;
	}
	public void setAmout(float amout) {
		this.amout = amout;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
}
