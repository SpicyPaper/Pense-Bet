package ch.arc.pensebet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.Invitation;

@Repository("invitationRepository")
public interface IInvitationDAO extends JpaRepository<Invitation, Integer> {
	
}