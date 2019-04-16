package ch.arc.pensebet.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@IdClass(InvitationId.class)
@Table(name = "invitation")
public class Invitation implements Serializable {
	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "BET_ID", referencedColumnName = "ID")
	private Bet bet;

	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private User user;

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
