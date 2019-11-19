package COSC381Baseball;

import java.util.ArrayList;
public class PlayerList {
	
	ArrayList<Player> playerList = new ArrayList<Player>();
	
	//Constructor
	public PlayerList(Player player) {
		playerList.add(player);
	}
	//Getter
	public Player getPlayer(String name) {
		for(int i = 0; i<=playerList.size();i++) {
			if(playerList.get(i).getName().equalsIgnoreCase(name))return playerList.get(i);
		}
		System.out.print("Player not found");
		return null;
	}
	//Adds a player to the end of the list
	public void addPlayer(Player player) {
		playerList.add(player);
	}
}
