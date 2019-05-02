package ch.arc.pensebet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ch.arc.pensebet.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PenseBetApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testUserModel() {
		String email = "g@pensebet.ch";
		String nickname = "g";
		float money = 10;
		
		User user = new User();
		user.setEmail(email);
		user.setNickname(nickname);
		user.setMoney(money);
		
		assertEquals(email, user.getEmail());
		assertEquals(nickname, user.getNickname());
		assertEquals(money, user.getMoney());
	}

}
