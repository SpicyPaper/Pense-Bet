package ch.arc.pensebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.arc.pensebet.model.Role;
import ch.arc.pensebet.model.State;
import ch.arc.pensebet.model.User;

@Repository("stateRepository")
public interface IStateDAO extends JpaRepository<State, Integer> {
	State findByName(String name);
}
