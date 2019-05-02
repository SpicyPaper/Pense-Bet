package ch.arc.pensebet;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ch.arc.pensebet.model.Bet;
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
		assertEquals(money, user.getMoney(), 0.001);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testBetModel() {
		float amount = 3.54f;
		Date creationDate = new Date(2019, 5, 2);
		Date endingDate = new Date(2019, 5, 4);
		float moneyPerWinner = 2;
		boolean result = true;
		
		Bet bet = new Bet();
		bet.setAmount(amount);
		bet.setCreationDate(creationDate);
		bet.setEndingDate(endingDate);
		bet.setMoneyPerWinner(moneyPerWinner);
		bet.setResult(result);
		
		assertEquals(amount, bet.getAmount(), 0.001);
		assertEquals(creationDate.getDay(), bet.getCreationDate().getDay());
		assertEquals(creationDate.getMonth(), bet.getCreationDate().getMonth());
		assertEquals(creationDate.getYear(), bet.getCreationDate().getYear());
		assertEquals(endingDate.getDay(), bet.getEndingDate().getDay());
		assertEquals(endingDate.getMonth(), bet.getEndingDate().getMonth());
		assertEquals(endingDate.getYear(), bet.getEndingDate().getYear());
		assertEquals(moneyPerWinner, bet.getMoneyPerWinner(), 0.01);
		assertEquals(result, bet.getResult());
	}

}
