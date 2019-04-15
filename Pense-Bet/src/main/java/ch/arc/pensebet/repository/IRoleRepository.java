package ch.arc.pensebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.arc.pensebet.model.Role;

@Repository("roleRepository")
public interface IRoleRepository extends JpaRepository<Role, Integer> {
	Role findByName(String name);
}
