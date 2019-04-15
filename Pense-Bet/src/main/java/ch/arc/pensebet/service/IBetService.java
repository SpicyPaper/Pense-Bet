package ch.arc.pensebet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.User;

public interface IBetService {
	public List<Bet> findAllActive(User user, Pageable pageable);
	public List<Bet> findAllClosed(User user, Pageable pageable);
	public List<Bet> findAllEnded(User user, Pageable pageable);
	public Optional<Bet> findBetById(Integer id);
	public void saveBet(Bet bet);
}
