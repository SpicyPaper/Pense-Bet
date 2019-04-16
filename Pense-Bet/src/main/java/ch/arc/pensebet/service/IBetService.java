package ch.arc.pensebet.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Bet;

@Service
public interface IBetService {
	public Optional<Bet> findBetById(Integer id);
	public void saveBet(Bet bet);
	public Bet findOne(Integer id);
}
