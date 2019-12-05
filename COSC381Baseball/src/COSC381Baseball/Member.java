package COSC381Baseball;

public class Member {
	PlayerList playerList;
	String name;
	public int P = 0;
	
	//Constructor
	public Member(PlayerList playerList, String name) {
		this.playerList= new PlayerList();
		this.name = name;
	}
	public Member() 
	{
		
	}
	//Getter
	public String getName() {
		return this.name;
	}
}
