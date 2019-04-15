package ch.arc.pensebet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.arc.pensebet.model.Bet;

@Repository("betRepository")
public interface IBetDAO extends JpaRepository<Bet, Integer> {
	public Optional<Bet> findById(Integer id);
}
