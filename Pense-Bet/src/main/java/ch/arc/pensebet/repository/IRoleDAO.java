package ch.arc.pensebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.arc.pensebet.model.Role;

@Repository("roleRepository")
public interface IRoleDAO extends JpaRepository<Role, Integer> {

}
