package ch.arc.pensebet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.Role;
import ch.arc.pensebet.model.User;
import ch.arc.pensebet.repository.IRoleRepository;
import ch.arc.pensebet.repository.IUserRepository;

@Service("userService")
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

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
		user.setPassword(pwdEncoder.encode(user.getPassword()));
		Role role = roleRepository.findByName("ADMIN");
		user.setRole(role);
		userRepository.save(user);
	}

	@Override
	public User findOne(Integer id) {
		return userDAO.findById(id).get();
	}
}
