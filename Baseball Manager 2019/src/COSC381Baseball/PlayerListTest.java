package COSC381Baseball;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PlayerListTest {
	Player player = new Player (365, 10, "C", "J.Martinez");
	PlayerList playerList = new PlayerList(player);
	
	@Test
	void addPlayerTest() {
		Player player2 = new Player(114,19,"P","J.Verlander");
		playerList.addPlayer(player2);
		assertEquals(player2, playerList.getPlayer("J.Verlander"));
	}
}