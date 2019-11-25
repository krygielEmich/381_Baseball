package COSC381Baseball;

public class Member {
	PlayerList playerList;
	String name;
	
	//Constructor
	public Member(PlayerList playerList, String name) {
		this.playerList= new PlayerList();
		this.name = name;
	}
	//Getter
	public String getName() {
		return this.name;
	}
}
