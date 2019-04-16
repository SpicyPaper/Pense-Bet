package ch.arc.pensebet.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ch.arc.pensebet.service.BetService;

@Component
public class IntegerToBet implements Converter<Integer, Bet> {

	@Autowired
	private BetService betService;

	@Override
	public Bet convert(Integer id) {
		return betService.findOne(id);
	}
}
