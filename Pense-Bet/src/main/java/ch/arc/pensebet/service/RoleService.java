package ch.arc.pensebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Role;
import ch.arc.pensebet.repository.IRoleDAO;

@Service("roleService")
public class RoleService implements IRoleService {

	@Autowired
	private IRoleDAO roleDAO;

	@Override
	public Role findRoleByName(String name) {
		return roleDAO.findByName(name);
	}
}
