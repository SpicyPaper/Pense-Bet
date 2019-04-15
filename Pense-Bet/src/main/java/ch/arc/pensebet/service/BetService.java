package ch.arc.pensebet.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.repository.IBetRepository;

@Service
public class BetService implements IBetService {
	
	@Autowired
	private IBetRepository betRepository;
	
	@Override
	public Optional<Bet> findBetById(Integer id) {
		return betRepository.findById(id);
	}

	@Override
	public void saveBet(Bet bet) {
		bet.setCreationDate(new Date());
		betRepository.save(bet);
	}
}
