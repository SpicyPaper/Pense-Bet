package ch.arc.pensebet.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.arc.pensebet.model.Invitation;
import ch.arc.pensebet.model.User;

@Repository("invitationRepository")
public interface IInvitationRepository extends JpaRepository<Invitation, Integer> {
	public List<Invitation> findByUser(User user, Pageable pageReguest);
}
