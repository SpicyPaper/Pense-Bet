package ch.arc.pensebet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.State;
import ch.arc.pensebet.model.User;

@Service
public interface IBetService {
	public Page<Bet> findByOwnerAndStateActive(User user, Pageable pageable);
	public Page<Bet> findByOwnerAndStateClosed(User user, Pageable pageable);
	public Page<Bet> findByOwnerAndStateEnded(User user, Pageable pageable);
	public Page<Bet> findByInvitationAndStateWaiting(User user, Pageable pageable);
	public Page<Bet> findByParticipantAndState(User user, State state, Pageable pageable);
	public Page<Bet> findByParticipantAndStateActive(User user, Pageable pageable);
	public Page<Bet> findByParticipantAndStateClosed(User user, Pageable pageable);
	public Page<Bet> findByParticipantAndStateEnded(User user, Pageable pageable);
	public Page<Bet> findAll(Pageable pageable);
	public Page<Bet> findByStateActive(Pageable pageable);
	public Page<Bet> findByStateClosed(Pageable pageable);
	public Page<Bet> findByStateEnded(Pageable pageable);
	public Bet findBetById(Integer id);
	public void saveBet(Bet bet);
	public List<Bet> findBySubjectAndParticipant(User participant, String betSubject);
	public List<Bet> findBySubjectAndParticipantAndOwner(User participant, String betSubject, User searchedOwner);
	public void deleteBet(Bet bet);
	public Page<Bet> detectEnded(Page<Bet> bets, Pageable pageable);
}
