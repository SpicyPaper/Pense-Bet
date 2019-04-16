package ch.arc.pensebet.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ch.arc.pensebet.service.UserService;

@Component
public class StringToUser implements Converter<String, User> {

	@Autowired
	private UserService userService;

	@Override
	public User convert(String arg) {
		Integer id = new Integer(arg);
		return userService.findOne(id);
	}
}