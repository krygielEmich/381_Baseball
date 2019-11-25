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
		test1.setOps(44);
		
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
		test4.setOps(77);
		test4.setDrafted(true);
		
		Player p1 = new Player();
		Player p2 = new Player();
		Player p3 = new Player();
		Player p4 = new Player();
		
		p1.setAvg(1);
		p1.setWin(2);
		p1.setLoss(25);
		p1.setEra(4);
		
		p2.setAvg(1);
		p2.setWin(2);
		p2.setLoss(25);
		p2.setEra(5);
		
		p3.setAvg(1);
		p3.setWin(2);
		p3.setLoss(25);
		p3.setEra(6);
		
		p4.setAvg(1);
		p4.setWin(2);
		p4.setLoss(25);
		p4.setEra(7);
		
		p1.setPositon("pitcher");
		p2.setPositon("pitcher");
		p3.setPositon("pitcher");
		p4.setPositon("pitcher");
		test1.setPositon("yeet");
		test2.setPositon("yeet");
		test3.setPositon("yeet");		
		test4.setPositon("yeet");


		
		PlayerList players = new PlayerList(test1);
		players.addPlayer(test2);
		players.addPlayer(test3);
		players.addPlayer(test4);
		players.addPlayer(p1);
		players.addPlayer(p2);
		players.addPlayer(p3);
		players.addPlayer(p4);
		
		ExprTree testWithPlayers = new ExprTree(players);
		testWithPlayers.evalfun("ignore");
		testWithPlayers.pEvalfun("ignore");
		
		//print out ranks
		System.out.println("Printing ranks. ");
		for (int i = 0; i < players.getPlayerList().size(); i++) {
			//System.out.println(players.getPlayerList().get(i).getRank());
		}
		
		//test overall no input
		Overalls overall = new Overalls(players);
		overall.overall("");
		System.out.println("====now pitchers====");
		overall.pOverall();
	}
}
