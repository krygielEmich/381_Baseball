package COSC381Baseball;

public class ExprTreeDemo {
	public static void main(String[] args) {
		//ExprTree testTree = new ExprTree();
		//testTree.evalfun("ignore");
		Player test1 = new Player();
		Player test2 = new Player();
		Player test3 = new Player();
		Player test4 = new Player();
		
		test1.setAvg(1);
		test1.setObp(2);
		test1.setSlg(3);
		test1.setOps(4);
		
		test2.setAvg(1);
		test2.setObp(2);
		test2.setSlg(3);
		test2.setOps(5);
		
		test3.setAvg(1);
		test3.setObp(2);
		test3.setSlg(3);
		test3.setOps(6);
		
		test4.setAvg(1);
		test4.setObp(2);
		test4.setSlg(3);
		test4.setOps(7);
		
		PlayerList players = new PlayerList(test1);
		players.addPlayer(test2);
		players.addPlayer(test3);
		players.addPlayer(test4);
		
		ExprTree testWithPlayers = new ExprTree(players);
		testWithPlayers.evalfun("ignore");
		
		//print out ranks
		for (int i = 0; i < players.getPlayerList().size(); i++) {
			System.out.println(players.getPlayerList().get(i).getRank());
		}
	}
}
