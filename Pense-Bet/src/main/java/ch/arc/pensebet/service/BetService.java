package ch.arc.pensebet.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.arc.pensebet.model.Bet;
import ch.arc.pensebet.repository.IBetDAO;

@Service
public class BetService implements IBetService {
	
	@Autowired
	private IBetDAO betDAO;

	@Override
	public void saveBet(Bet bet) {
		bet.setCreationDate(new Date());
		betDAO.save(bet);
	}
}
