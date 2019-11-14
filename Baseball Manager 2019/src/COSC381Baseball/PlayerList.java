package COSC381Baseball;

import java.util.ArrayList;
public class PlayerList {
	
	ArrayList<Player> playerList = new ArrayList<Player>();
	
	//Constructor
	public PlayerList(Player player) {
		playerList.add(player);
	}
	//Getter
	public Player getPlayer(int index) {
		return playerList.get(index);
	}
	//Adds a player to the end of the list
	public void addPlayer(Player player) {
		playerList.add(player);
	}
}
