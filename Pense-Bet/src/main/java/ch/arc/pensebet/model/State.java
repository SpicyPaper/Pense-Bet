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
	public Integer id;
	
	@Column
	public String name;
    
    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
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
