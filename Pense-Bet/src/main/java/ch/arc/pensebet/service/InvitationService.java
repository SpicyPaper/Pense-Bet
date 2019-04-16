package ch.arc.pensebet.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.model.Invitation;
import ch.arc.pensebet.model.User;
import ch.arc.pensebet.repository.IBetDAO;
import ch.arc.pensebet.repository.IInvitationDAO;

@Service("invitationService")
public class InvitationService implements IInvitationService {
	
	@Autowired
	private IInvitationDAO invitationDAO;
	
	@Override
	public List<Invitation> findAllInvitations() {
		return invitationDAO.findAll();
	}
	
	@Override
	public void saveInvitation(Invitation invitation) {
		invitationDAO.save(invitation);
	}
}
