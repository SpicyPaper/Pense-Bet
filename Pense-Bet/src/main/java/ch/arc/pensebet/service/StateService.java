package ch.arc.pensebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.State;
import ch.arc.pensebet.model.User;
import ch.arc.pensebet.repository.IStateDAO;

@Service("stateService")
public class StateService implements IStateService {
	
	@Autowired
	private IStateDAO stateDAO;
	
	@Override
	public State findStateByName(String name) {
		return stateDAO.findByName(name);
	}

}
