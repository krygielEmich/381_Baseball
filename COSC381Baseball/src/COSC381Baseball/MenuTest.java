package COSC381Baseball;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MenuTest {
	//Create menu class
	Menu menu = new Menu();

	@Test
	void test() {
		
	}
	@Test
	void saveTest() 
	{
		menu.initializeMLBList();
		Member temp = new Member(null,"Connor");
		MemberList members = new MemberList(temp);
		assertEquals(menu.Save("test", members),true);
	}
	@Test
	void restoreTest() 
	{
		menu.initializeMLBList();
		PlayerList tempList = menu.mlbList;
		Member temp = new Member(tempList,"Connor");
		MemberList members = new MemberList(temp);
		assertEquals(menu.Restore("test", members),true);
	}
	@Test
	void quitTest() 
	{
		assertEquals(menu.Quit(),true);
	}
}
