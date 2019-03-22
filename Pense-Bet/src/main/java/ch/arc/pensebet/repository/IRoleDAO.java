package ch.arc.pensebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.arc.pensebet.model.Role;

public interface IRoleDAO extends JpaRepository<Role, Integer> {

}
