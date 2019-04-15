package ch.arc.pensebet.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.User;
import ch.arc.pensebet.repository.IBetRepository;
import ch.arc.pensebet.repository.IStateRepository;

@Service
public class BetService implements IBetService {
	
	@Autowired
	private IBetRepository betRepository;
	
	@Autowired
	private IStateRepository stateRepository;
	
	@Override
	public Optional<Bet> findBetById(Integer id) {
		return betRepository.findById(id);
	}

	@Override
	public void saveBet(Bet bet) {
		bet.setCreationDate(new Date());
		betRepository.save(bet);
	}

	@Override
	public List<Bet> findAllActive(User user, Pageable pageable) {
		return betRepository.findByOwnerAndState(user, stateRepository.findByName("ACTIVE"), pageable);
	}

	@Override
	public List<Bet> findAllClosed(User user, Pageable pageable) {
		return betRepository.findByOwnerAndState(user, stateRepository.findByName("CLOSED"), pageable);
	}

	@Override
	public List<Bet> findAllEnded(User user, Pageable pageable) {
		return betRepository.findByOwnerAndState(user, stateRepository.findByName("ENDED"), pageable);
	}
}
