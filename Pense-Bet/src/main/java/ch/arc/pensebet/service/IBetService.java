package ch.arc.pensebet.service;

import java.util.Optional;

import ch.arc.pensebet.model.Bet;

public interface IBetService {
	public Optional<Bet> findBetById(Integer id);
	public void saveBet(Bet bet);
}
