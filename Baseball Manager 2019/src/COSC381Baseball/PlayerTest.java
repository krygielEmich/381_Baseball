package COSC381Baseball;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void test() {
		Player player = new Player (365, 10, "C", "J.Martinez");
		player.setDrafted(true);
		assertEquals(player.getDrafted(),true);
	}

	@Test
	void test2() {
		Player player = new Player (365, 10, "C","J.Martinez");
		assertEquals(player.getRBI(),365);
	}

}
