package ch.arc.pensebet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Participation;
import ch.arc.pensebet.repository.IParticipationRepository;

@Service("participationService")
public class ParticipationService implements IParticipationService {
	
	@Autowired
	private IParticipationRepository participationRepository;
	
	@Override
	public List<Participation> findAllParticipations() {
		return participationRepository.findAll();
	}
	
}