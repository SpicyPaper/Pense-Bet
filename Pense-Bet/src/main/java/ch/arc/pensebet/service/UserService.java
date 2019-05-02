package ch.arc.pensebet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.User;
import ch.arc.pensebet.repository.IUserRepository;

@Service("userService")
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public User findUserByNickname(String nickname) {
		return userRepository.findByNickname(nickname);
	}
	
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User findOne(Integer id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent())
		{
			return user.get();
		}
		
		return null;
	}
}
