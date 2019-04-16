package ch.arc.pensebet.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.State;
import ch.arc.pensebet.model.User;

public interface IBetService {
	public Page<Bet> findPersonnalActive(User user, Pageable pageable);
	public Page<Bet> findPersonnalClosed(User user, Pageable pageable);
	public Page<Bet> findPersonnalEnded(User user, Pageable pageable);
	public Page<Bet> findAll(User user, State state, Pageable pageable);
	public Page<Bet> findAllWaiting(User user, Pageable pageable);
	public Page<Bet> findAllActive(User user, Pageable pageable);
	public Page<Bet> findAllClosed(User user, Pageable pageable);
	public Page<Bet> findAllEnded(User user, Pageable pageable);
	public Optional<Bet> findBetById(Integer id);
	public void saveBet(Bet bet);
}
