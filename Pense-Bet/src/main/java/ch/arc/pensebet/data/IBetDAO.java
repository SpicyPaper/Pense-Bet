package ch.arc.pensebet.data;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.arc.pensebet.model.Bet;

public interface IBetDAO extends JpaRepository<Bet, Integer> {

}
