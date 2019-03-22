package ch.arc.pensebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.arc.pensebet.model.State;

public interface IStateDAO extends JpaRepository<State, Integer> {

}
