package ch.arc.pensebet.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@IdClass(ParticipationId.class)
@Table(name = "participation")

public class Participation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6325995693678991091L;

	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "BET_ID", referencedColumnName = "ID")
	private Bet bet;

	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private User user;

	@Column
	private boolean agree;

	public boolean isAgree() {
		return agree;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
