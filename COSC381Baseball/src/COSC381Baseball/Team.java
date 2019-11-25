package COSC381Baseball;

public class Team {
	Member member;
	PlayerList printed = new PlayerList();
	private int pitchers=0;

	public Team(Member member) {
		this.member = member;
		printLine("C");
		printLine("1B");
		printLine("2B");
		printLine("3B");
		printLine("SS");
		printLine("LF");
		printLine("CF");
		printLine("RF");
		printLine("P");
		printLine("P");
		printLine("P");
		printLine("P");
		printLine("P");
		
	}
	public void printLine(String pos) {
		boolean isPitcher=false;
		if(pos=="P") {
			pitchers++;
			isPitcher=true;
		}
		if(printed.exists(member.playerList.getPos(pos).getName()))member.playerList.getPos(pos).setIgnore(true);
		if(member.playerList.getPos(pos).getName()!="") {
			System.out.print(pos);
			if(isPitcher)System.out.print(pitchers);
			System.out.print(": "+member.playerList.getPos(pos).getName()+"\n");
			printed.addPlayer(member.playerList.getPos(pos));
		}
		else {
			System.out.print(pos);
			if(isPitcher)System.out.print(pitchers);
			System.out.print(": Not Drafted\n");
		}
		member.playerList.resetIgnore();
	}
}
