package ch.arc.pensebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.State;
import ch.arc.pensebet.model.User;
import ch.arc.pensebet.repository.IStateRepository;

@Service("stateService")
public class StateService implements IStateService {
	
	@Autowired
	private IStateRepository stateRepository;
	
	@Override
	public State findStateByName(String name) {
		return stateRepository.findByName(name);
	}

}
