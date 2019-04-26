package ch.arc.pensebet.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
		betRepository.save(bet);
	}

	@Override
	public void deleteBet(Bet bet) {
		betRepository.delete(bet);
	}

	@Override
	public Page<Bet> findByOwnerAndStateActive(User user, Pageable pageable) {
		return betRepository.findByOwnerAndState(user, stateRepository.findByName("ACTIVE"), pageable);
	}

	@Override
	public Page<Bet> findByOwnerAndStateClosed(User user, Pageable pageable) {
		return betRepository.findByOwnerAndState(user, stateRepository.findByName("CLOSED"), pageable);
	}

	@Override
	public Page<Bet> findByOwnerAndStateEnded(User user, Pageable pageable) {
		return betRepository.findByOwnerAndState(user, stateRepository.findByName("ENDED"), pageable);
	}

	@Override
	public Page<Bet> findByInvitationAndStateWaiting(User user, Pageable pageable) {
		List<Invitation> invitations = invitationRepository.findByUser(user, pageable);
		return new PageImpl<>(invitations.stream().parallel().map(Invitation::getBet).collect(Collectors.toList()));
	}

	@Override
	public Page<Bet> findBySubjectAndParticipant(User participant, String betSubject, PageRequest pageable) {
		return findBySubject(betSubject, betRepository.findByParticipant(participant.getId(), pageable));
	}

	@Override
	public Page<Bet> findBySubjectAndParticipantAndOwner(User participant, String betSubject, User searchedOwner,
			PageRequest pageable) {
		return findBySubject(betSubject, betRepository.findByParticipantAndOwner(participant.getId(), searchedOwner.getId(), pageable));
	}
	
	private Page<Bet> findBySubject(String betSubject, Page<Bet> bets) {
		String[] betSubjects = betSubject.toLowerCase().split(" ");
		return new PageImpl<>(bets.getContent().stream().parallel().filter(bet -> stringContainsItemFromList(bet.getSubject().toLowerCase(), betSubjects)).collect(Collectors.toList()));
	}
	
	/**
	 * Source: https://stackoverflow.com/a/8995988
	 */
	private static boolean stringContainsItemFromList(String inputStr, String[] items) {
	    return Arrays.stream(items).parallel().allMatch(inputStr::contains);
	}

	@Override
	public Page<Bet> findByParticipantAndState(User user, State state, Pageable pageable) {
		List<Participation> participations = participationRepository.findByUser(user, pageable);
		return new PageImpl<>(participations.stream().parallel()
				.filter(participation -> participation.getBet().getState().getId() == state.getId())
				.map(Participation::getBet).collect(Collectors.toList()));
	}

	@Override
	public Page<Bet> findByParticipantAndStateActive(User user, Pageable pageable) {
		return findByParticipantAndState(user, stateRepository.findByName("ACTIVE"), pageable);
	}

	@Override
	public Page<Bet> findByParticipantAndStateClosed(User user, Pageable pageable) {
		return findByParticipantAndState(user, stateRepository.findByName("CLOSED"), pageable);
	}

	@Override
	public Page<Bet> findByParticipantAndStateEnded(User user, Pageable pageable) {
		return findByParticipantAndState(user, stateRepository.findByName("ENDED"), pageable);
	}

	public Bet findOne(Integer id) {
		return findBetById(id).get();
	}

	@Override
	public Page<Bet> findAll(Pageable pageable) {
		return betRepository.findAll(pageable);
	}

	@Override
	public Page<Bet> findByStateActive(Pageable pageable) {
		return betRepository.findByState(stateRepository.findByName("ACTIVE"), pageable);
	}

	@Override
	public Page<Bet> findByStateClosed(Pageable pageable) {
		return betRepository.findByState(stateRepository.findByName("CLOSED"), pageable);
	}

	@Override
	public Page<Bet> findByStateEnded(Pageable pageable) {
		return betRepository.findByState(stateRepository.findByName("ENDED"), pageable);
	}

}
