package ch.arc.pensebet.repository;

import java.util.List;
import java.util.Optional;

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
//	@Query("SELECT b FROM Bet b WHERE b.state_id = :state.id AND b.owner_id = :user.id")
//    public List<Bet> findAllByStateByUser(@Param("state") State state, @Param("user") User user);
	
	public List<Bet> findByOwnerAndState(User user, State state, Pageable pageReguest);
	public Optional<Bet> findById(Integer id);
}
