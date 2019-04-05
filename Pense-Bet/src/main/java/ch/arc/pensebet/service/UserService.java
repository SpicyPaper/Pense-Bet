package ch.arc.pensebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Role;
import ch.arc.pensebet.model.User;
import ch.arc.pensebet.repository.IRoleDAO;
import ch.arc.pensebet.repository.IUserDAO;

@Service("userService")
public class UserService implements IUserService {
	
	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@Override
	public User findUserByNickname(String nickname) {
		return userDAO.findByNickname(nickname);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(pwdEncoder.encode(user.getPassword()));
		Role role = roleDAO.findByName("ADMIN");
		user.setRole(role);
		userDAO.save(user);
	}

}
