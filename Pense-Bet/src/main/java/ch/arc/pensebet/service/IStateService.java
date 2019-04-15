package ch.arc.pensebet.service;

import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.State;

@Service
public interface IStateService {
	public State findStateByName(String name);
}
