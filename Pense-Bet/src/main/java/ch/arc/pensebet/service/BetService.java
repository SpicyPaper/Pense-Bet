package ch.arc.pensebet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.Invitation;
import ch.arc.pensebet.model.Participation;
import ch.arc.pensebet.model.State;
import ch.arc.pensebet.model.User;
import ch.arc.pensebet.repository.IBetRepository;
import ch.arc.pensebet.repository.IInvitationRepository;
import ch.arc.pensebet.repository.IParticipationRepository;
import ch.arc.pensebet.repository.IStateRepository;

@Service
public class BetService implements IBetService {
	
	@Autowired
	private IBetRepository betRepository;
	
	@Autowired
	private IStateRepository stateRepository;
	
	@Autowired
	private IParticipationRepository participationRepository;
	
	@Autowired
	private IInvitationRepository invitationRepository;
	
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
	public Page<Bet> findPersonnalActive(User user, Pageable pageable) {
		return betRepository.findByOwnerAndState(user, stateRepository.findByName("ACTIVE"), pageable);
	}

	@Override
	public Page<Bet> findPersonnalClosed(User user, Pageable pageable) {
		return betRepository.findByOwnerAndState(user, stateRepository.findByName("CLOSED"), pageable);
	}

	@Override
	public Page<Bet> findPersonnalEnded(User user, Pageable pageable) {
		return betRepository.findByOwnerAndState(user, stateRepository.findByName("ENDED"), pageable);
	}

	@Override
	public Page<Bet> findAll(User user, State state, Pageable pageable) {
		List<Participation> participations = participationRepository.findByUser(user, pageable);
		List<Bet> bets = new ArrayList<Bet>();
		for (int i = 0; i < participations.size(); i++) {
			if (participations.get(i).getBet().getState().getId() == state.getId()) {
				bets.add(participations.get(i).getBet());
			}
		}
		return new PageImpl<>(bets);
	}

	@Override
	public Page<Bet> findAllWaiting(User user, Pageable pageable) {
		List<Invitation> invitations = invitationRepository.findByUser(user, pageable);
		List<Bet> bets = new ArrayList<Bet>();
		for (int i = 0; i < invitations.size(); i++) {
			bets.add(invitations.get(i).getBet());
		}
		return new PageImpl<>(bets);
	}

	@Override
	public Page<Bet> findAllActive(User user, Pageable pageable) {
		return findAll(user, stateRepository.findByName("ACTIVE"), pageable);
	}

	@Override
	public Page<Bet> findAllClosed(User user, Pageable pageable) {
		return findAll(user, stateRepository.findByName("CLOSED"), pageable);
	}

	@Override
	public Page<Bet> findAllEnded(User user, Pageable pageable) {
		return findAll(user, stateRepository.findByName("ENDED"), pageable);
	}

	@Override
	public Bet findOne(Integer id) {
		return betDAO.findById(id).get();
	}
}
