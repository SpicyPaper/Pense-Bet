package ch.arc.pensebet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Invitation;
import ch.arc.pensebet.model.User;

@Service
public interface IInvitationService {

	public List<Invitation> findAllInvitations();

	void saveInvitation(Invitation invitation);
}
