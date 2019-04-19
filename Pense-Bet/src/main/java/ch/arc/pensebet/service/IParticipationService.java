package ch.arc.pensebet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.Participation;

@Service
public interface IParticipationService {

	public List<Participation> findAllParticipations();
	public List<Participation> findParticipationsByBet(Bet bet);
}