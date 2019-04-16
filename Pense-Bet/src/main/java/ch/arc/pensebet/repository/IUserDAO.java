package ch.arc.pensebet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.User;

@Repository("userRepository")
public interface IUserDAO extends JpaRepository<User, Integer> {
	User findByNickname(String nickname);
}
