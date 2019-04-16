package ch.arc.pensebet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Invitation;

@Service
public interface IInvitationService {

	public List<Invitation> findAllInvitations();

	void saveInvitation(Invitation invitation);
}
