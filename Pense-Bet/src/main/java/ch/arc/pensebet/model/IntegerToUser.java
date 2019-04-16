package ch.arc.pensebet.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ch.arc.pensebet.service.UserService;

@Component
public class IntegerToUser implements Converter<Integer, User> {

	@Autowired
	private UserService userService;

	@Override
	public User convert(Integer id) {
		return userService.findOne(id);
	}
}