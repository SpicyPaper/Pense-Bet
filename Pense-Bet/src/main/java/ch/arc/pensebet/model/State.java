package ch.arc.pensebet.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="state")

public class State {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer id;
	
	@Column
	private String name;
    
    @OneToMany(mappedBy = "state")
    private Set<Bet> bets;
	
	public State() { }
	
	public State(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Bet> getBets() {
		return bets;
	}
	public void setBets(Set<Bet> bets) {
		this.bets = bets;
	}
	public Integer getId() {
		return id;
	}

}
