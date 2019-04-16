package ch.arc.pensebet.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.State;
import ch.arc.pensebet.model.User;

@Repository("betRepository")
public interface IBetRepository extends JpaRepository<Bet, Integer> {
	public Page<Bet> findByOwnerAndState(User user, State state, Pageable pageReguest);
	public Optional<Bet> findById(Integer id);
}
