package ch.arc.pensebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.arc.pensebet.model.State;

@Repository("stateRepository")
public interface IStateRepository extends JpaRepository<State, Integer> {
	State findByName(String name);
}
