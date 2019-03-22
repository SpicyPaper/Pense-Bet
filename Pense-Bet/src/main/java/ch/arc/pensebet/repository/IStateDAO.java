package ch.arc.pensebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.arc.pensebet.model.State;

@Repository("stateRepository")
public interface IStateDAO extends JpaRepository<State, Integer> {

}
