package ch.arc.pensebet.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@IdClass(InvitationId.class)
@Table(name = "invitation")
public class Invitation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7057651885542882143L;

	@Id
    @ManyToOne
    @JoinColumn(name = "bet_id")
    private Bet bet;
	
	@Id
    @ManyToOne
    @JoinColumn(name = "user_id")
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
