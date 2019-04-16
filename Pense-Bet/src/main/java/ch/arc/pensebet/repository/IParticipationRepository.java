package ch.arc.pensebet.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.arc.pensebet.model.Participation;
import ch.arc.pensebet.model.User;

@Repository("participationRepository")
public interface IParticipationRepository extends JpaRepository<Participation, Integer> {
	public List<Participation> findByUser(User user, Pageable pageReguest);
}
