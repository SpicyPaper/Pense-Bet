package ch.arc.pensebet.service;

import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Role;

@Service
public interface IRoleService {
	public Role findRoleByName(String name);
}
