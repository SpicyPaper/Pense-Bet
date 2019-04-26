package ch.arc.pensebet.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.State;
import ch.arc.pensebet.model.User;

@Repository("betRepository")
public interface IBetRepository extends JpaRepository<Bet, Integer> {
	public Page<Bet> findByOwnerAndState(User user, State state, Pageable pageReguest);
	public Page<Bet> findAll(Pageable pageReguest);
	public Page<Bet> findByState(State state, Pageable pageReguest);
	public Optional<Bet> findById(Integer id);
	
	@Query(value = "SELECT b.* FROM bet b, participation p WHERE "
			+ "p.user_id = :participantId "
			+ "AND b.id = p.bet_id", 
           nativeQuery = true)
	public Page<Bet> findByParticipant(
			@Param("participantId") Integer participantId, 
			Pageable pageReguest);
	
	@Query(value = "SELECT b.* FROM bet b, participation p WHERE "
			+ "p.user_id = :participantId "
			+ "AND b.owner_id = :ownerId "
			+ "AND b.id = p.bet_id", 
           nativeQuery = true)
	public Page<Bet> findByParticipantAndOwner(
			@Param("participantId") Integer participantId, 
			@Param("ownerId") Integer ownerId, 
			Pageable pageReguest);
}
