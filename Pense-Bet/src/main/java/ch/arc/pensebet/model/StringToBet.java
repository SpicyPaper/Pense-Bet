package ch.arc.pensebet.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ch.arc.pensebet.service.BetService;
import ch.arc.pensebet.service.UserService;

@Component
public class StringToBet implements Converter<String, Bet> {

	@Autowired
	private BetService betService;

	@Override
	public Bet convert(String arg0) {
		Integer id = new Integer(arg0);
		return betService.findOne(id);
	}
}