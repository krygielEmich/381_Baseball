package COSC381Baseball;
import java.util.ArrayList;
public class Overalls {
	
	private PlayerList players;
	private ArrayList<Player> allPlayers;
	private PlayerList playersToSort;
	
	public Overalls(PlayerList newPlayers) {
		players = newPlayers;
	}
	
	public void overall(String position) {
		if (position.equals("")) {
			allPlayers = players.getPlayerList();
			//if no player provided, print all non pitcher positions for non drafted players		
			//first put all the non pitcher players into a new PlayerList we can sort for the output
			for (int i = 0; i < allPlayers.size(); i++) {
				//put all players that arent pitchers into the new list
				if (!allPlayers.get(i).getPosition().equals("pitcher")) {
					playersToSort.addPlayer(allPlayers.get(i));
				}
			}
			//sort based on their rank (high to low)
			
		}
	}
	
	public void pOverall() {
		
	}
}
