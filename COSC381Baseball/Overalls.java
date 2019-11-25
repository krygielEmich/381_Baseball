package COSC381Baseball;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class Overalls {
	
	private PlayerList players;
	private ArrayList<Player> allPlayers;
	private PlayerList playersToSort;
	
	public Overalls(PlayerList newPlayers) {
		players = newPlayers;
	}
	
	public void overall(String position) {
		//TODO: handle error messages regarding already drafted players
		playersToSort = new PlayerList();
		if (position.equals("")) {
			allPlayers = players.getPlayerList();
			
			//if no position provided, print all non pitcher positions for non drafted players		
			//first put all the non pitcher players into a new PlayerList we can sort for the output
			for (int i = 0; i < allPlayers.size(); i++) {
				//put all players that arent pitchers into the new list
				if (!allPlayers.get(i).getPosition().equals("pitcher") && !allPlayers.get(i).getDrafted()) {
					playersToSort.addPlayer(allPlayers.get(i));
				}
			}
			
			//sort based on their rank (high to low)
			Collections.sort(playersToSort.getPlayerList(), new sortByRank());
			
			//output
			for (int i = 0; i < playersToSort.getPlayerList().size(); i++) {
				System.out.println(playersToSort.getPlayerList().get(i).getName() + " " + playersToSort.getPlayerList().get(i).getTeam() + " " + playersToSort.getPlayerList().get(i).getRank());
			}
		} else {
			//position supplied, so look for that one only
			allPlayers = players.getPlayerList();
			//if no player provided, print all non pitcher positions for non drafted players		
			//first put all the non pitcher players into a new PlayerList we can sort for the output
			for (int i = 0; i < allPlayers.size(); i++) {
				//put all players that arent pitchers into the new list
				if (allPlayers.get(i).getPosition().equals(position) && !allPlayers.get(i).getDrafted()) {
					playersToSort.addPlayer(allPlayers.get(i));
				}
			}
			//sort based on their rank (high to low)
			Collections.sort(playersToSort.getPlayerList(), new sortByRank());
			
			//output
			for (int i = 0; i < playersToSort.getPlayerList().size(); i++) {
				System.out.println(playersToSort.getPlayerList().get(i).getName() + " " + playersToSort.getPlayerList().get(i).getTeam() + " " + playersToSort.getPlayerList().get(i).getRank());
			}
		}
	}
	
	//same as overall but only for pitchers
	public void pOverall() {
		//TODO: handle error messages regarding already drafted players
		playersToSort = new PlayerList();
		allPlayers = players.getPlayerList();
		//if no player provided, print all non pitcher positions for non drafted players		
		//first put all the non pitcher players into a new PlayerList we can sort for the output
		for (int i = 0; i < allPlayers.size(); i++) {
			//put all players that arent pitchers into the new list
			if (allPlayers.get(i).getPosition().equals("pitcher") && !allPlayers.get(i).getDrafted()) {
				playersToSort.addPlayer(allPlayers.get(i));
			}
		}
		//sort based on their rank (high to low)
		Collections.sort(playersToSort.getPlayerList(), new sortByRank());
					
		//output
		for (int i = 0; i < playersToSort.getPlayerList().size(); i++) {
			System.out.println(playersToSort.getPlayerList().get(i).getName() + " " + playersToSort.getPlayerList().get(i).getTeam() + " " + playersToSort.getPlayerList().get(i).getRank());
		}
	}
	//comparator inner class
		class sortByRank implements Comparator<Player> {

			//sort in descending order based on the rank
			public int compare(Player player1, Player player2) {
				return (int) (player2.getRank() - player1.getRank());
			}
			
		}
}
