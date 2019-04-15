package ch.arc.pensebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Role;
import ch.arc.pensebet.repository.IRoleRepository;

@Service("roleService")
public class RoleService implements IRoleService {

	@Autowired
	private IRoleRepository roleDAO;

	@Override
	public Role findRoleByName(String name) {
		return roleDAO.findByName(name);
	}
}
