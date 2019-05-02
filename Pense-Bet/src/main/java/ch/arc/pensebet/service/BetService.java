package ch.arc.pensebet.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public Bet findBetById(Integer id) {
		Optional<Bet> elisaBet = betRepository.findById(id);

		if(elisaBet.isPresent())
		{
			if (elisaBet.get().getResult() != null)
			{
				elisaBet.get().setState(stateRepository.findByName("CLOSED"));
				betRepository.save(elisaBet.get());
			}
			else if (elisaBet.get().getEndingDate().compareTo(new Date()) < 0)
			{
				elisaBet.get().setState(stateRepository.findByName("ENDED"));
				betRepository.save(elisaBet.get());
			}
			
			return elisaBet.get();
		}
		
		return null;
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
		return detectEnded(betRepository.findByOwnerAndState(user, stateRepository.findByName("ACTIVE"), pageable), pageable);
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
		Page<Invitation> invitations = invitationRepository.findByUser(user, pageable);
		Page<Bet> bets = new PageImpl<>(invitations.getContent().stream().map(Invitation::getBet).collect(Collectors.toList()));
		return detectEnded(bets, pageable);
	}

	@Override
	public List<Bet> findBySubjectAndParticipant(User participant, String betSubject) {
		return findBySubject(betSubject, betRepository.findByParticipant(participant.getId()));
	}

	@Override
	public List<Bet> findBySubjectAndParticipantAndOwner(User participant, String betSubject, User searchedOwner) {
		return findBySubject(betSubject, betRepository.findByParticipantAndOwner(participant.getId(), searchedOwner.getId()));
	}
	
	private List<Bet> findBySubject(String betSubject, List<Bet> bets) {
		String[] betSubjects = betSubject.toLowerCase().split(" ");
		return bets.stream().filter(bet -> stringContainsItemFromList(bet.getSubject().toLowerCase(), betSubjects)).collect(Collectors.toList());
	}
	
	/**
	 * Source: https://stackoverflow.com/a/8995988
	 */
	private static boolean stringContainsItemFromList(String inputStr, String[] items) {
	    return Arrays.stream(items).allMatch(inputStr::contains);
	}

	@Override
	public Page<Bet> findByParticipantAndState(User user, State state, Pageable pageable) {
		Page<Participation> participations = participationRepository.findByUser(user, pageable);
		return new PageImpl<>(participations.getContent().stream().parallel()
				.filter(participation -> participation.getBet().getState().getId().equals(state.getId()))
				.map(Participation::getBet).collect(Collectors.toList()), pageable, participations.getTotalElements());
	}

	@Override
	public Page<Bet> findByParticipantAndStateActive(User user, Pageable pageable) {
		return detectEnded(findByParticipantAndState(user, stateRepository.findByName("ACTIVE"), pageable), pageable);
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
		Bet bet = findBetById(id);
		
		return bet;
	}

	@Override
	public Page<Bet> findAll(Pageable pageable) {
		return betRepository.findAll(pageable);
	}

	@Override
	public Page<Bet> findByStateActive(Pageable pageable) {
		return detectEnded(betRepository.findByState(stateRepository.findByName("ACTIVE"), pageable), pageable);
	}

	@Override
	public Page<Bet> findByStateClosed(Pageable pageable) {
		return betRepository.findByState(stateRepository.findByName("CLOSED"), pageable);
	}

	@Override
	public Page<Bet> findByStateEnded(Pageable pageable) {
		return betRepository.findByState(stateRepository.findByName("ENDED"), pageable);
	}
	
	@Override
	public Page<Bet> detectEnded(Page<Bet> bets, Pageable pageable) {		
		List<Bet> betsNotEnded = new ArrayList<>();
		
		for (int i = bets.getContent().size() - 1; i >= 0; i--)
		{
			Bet bet = bets.getContent().get(i);
			if (bet.getResult() != null)
			{
				bet.setState(stateRepository.findByName("CLOSED"));
				betRepository.save(bet);
			}
			else if (bet.getEndingDate().compareTo(new Date()) < 0)
			{
				bet.setState(stateRepository.findByName("ENDED"));
				betRepository.save(bet);
			}
			else
			{
				betsNotEnded.add(bet);
			}
		}
		return new PageImpl<>(betsNotEnded, pageable, betsNotEnded.size());
	}
}
