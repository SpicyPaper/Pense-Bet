package ch.arc.pensebet.model;

import java.io.Serializable;

public class ParticipationId implements Serializable {
	private Bet bet;
	private User user;

	public ParticipationId() {
	}

	public ParticipationId(Bet bet, User user) {
		this.bet = bet;
		this.user = user;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((bet == null) ? 0 : bet.hashCode()));
		result = (int) (prime * result + ((user == null) ? 0 : user.hashCode()));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipationId other = (ParticipationId) obj;
		if (bet == null) {
			if (other.bet != null)
				return false;
		} else if (!bet.equals(other.bet))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
