package ch.arc.pensebet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Role;
import ch.arc.pensebet.model.User;
import ch.arc.pensebet.repository.IRoleRepository;
import ch.arc.pensebet.repository.IUserRepository;

@Service("userService")
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepository userDAO;

	@Autowired
	private IRoleRepository roleDAO;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@Override
	public User findUserByNickname(String nickname) {
		return userDAO.findByNickname(nickname);
	}
	
	@Override
	public List<User> findAllUsers() {
		return userDAO.findAll();
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(pwdEncoder.encode(user.getPassword()));
		Role role = roleDAO.findByName("ADMIN");
		user.setRole(role);
		userDAO.save(user);
	}

}
