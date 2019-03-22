package ch.arc.pensebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.arc.pensebet.model.Bet;

@Repository("betRepository")
public interface IBetDAO extends JpaRepository<Bet, Integer> {

}
