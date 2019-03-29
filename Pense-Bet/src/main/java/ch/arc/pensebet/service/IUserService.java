package ch.arc.pensebet.service;

import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.User;

@Service
public interface IUserService {
	public User findUserByNickname(String nickname);
	public void saveUser(User user);
}
