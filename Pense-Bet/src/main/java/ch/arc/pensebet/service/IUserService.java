package ch.arc.pensebet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.User;

@Service
public interface IUserService {
	public User findUserByNickname(String nickname);
	public List<User> findAllUsers();
	public void saveUser(User user);
}
