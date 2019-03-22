package ch.arc.pensebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.arc.pensebet.model.User;

public interface IUserDAO extends JpaRepository<User, Integer> {

}
