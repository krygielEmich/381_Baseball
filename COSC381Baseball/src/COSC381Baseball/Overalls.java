package COSC381Baseball;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
public class Overalls {
	
	private PlayerList players;
	private ArrayList<Player> allPlayers;
	private PlayerList playersToSort;
	private MemberList members;
	
	public Overalls(PlayerList newPlayers, MemberList newMembers) {
		players = newPlayers;
		members = newMembers;
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
				if (!allPlayers.get(i).getPosition().equals("P") && !allPlayers.get(i).getDrafted()) {
					playersToSort.addPlayer(allPlayers.get(i));
				}
			}
			
			//sort based on their rank (high to low)
			Collections.sort(playersToSort.getPlayerList(), new sortByRank());
			
			//output
				System.out.println(playersToSort);
		} else {
			//position supplied, so look for that one only
			
			//check if the position they are looking for is filled, if it is return and print message
			System.out.println("Who is running this overall? ");
			Scanner keyboard = new Scanner(System.in);
			String member = keyboard.nextLine();
			
			//check to see if member already has the player for that position, if they do then return
			for (int i = 0; i < members.memberList.size(); i++) {
				if (members.memberList.get(i).getName().equalsIgnoreCase(member)) {
					for (int j = 0; j < members.memberList.get(i).playerList.playerList.size(); j++) {
						if (members.memberList.get(i).playerList.playerList.get(j).getPosition().equals(position)) {
							System.out.println("Error: already drafted player for the position " + position);
							return;
						}
					}
				}
			}
			
			allPlayers = players.getPlayerList();
			//if no player provided, print all non pitcher positions for non drafted players		
			//first put all the non pitcher players into a new PlayerList we can sort for the output
			for (int i = 0; i < allPlayers.size(); i++) {
				//put all players that arent pitchers into the new list
				if (allPlayers.get(i).getPosition().equalsIgnoreCase(position) && !allPlayers.get(i).getDrafted()) {
					playersToSort.addPlayer(allPlayers.get(i));
				}
			}
			//sort based on their rank (high to low)
			Collections.sort(playersToSort.getPlayerList(), new sortByRank());
			
			//output
			System.out.println(playersToSort);
			}
		}
	
	//same as overall but only for pitchers
	public void pOverall() {
		//TODO: handle error messages regarding already drafted players
		playersToSort = new PlayerList();
		allPlayers = players.getPlayerList();
		//if no player provided, print all non pitcher positions for non drafted players		
		//first put all the non pitcher players into a new PlayerList we can sort for the output
		
		System.out.println("Who is running this overall? ");
		Scanner keyboard = new Scanner(System.in);
		String member = keyboard.nextLine();
		
		//check to see if member already has the player for that position, if they do then return
		int pitcherCount = 0;
		for (int i = 0; i < members.memberList.size(); i++) {
			if (members.memberList.get(i).getName().equalsIgnoreCase(member)) {
				for (int j = 0; j < members.memberList.get(i).playerList.playerList.size(); j++) {
					if (members.memberList.get(i).playerList.playerList.get(j).getPosition().equals("P")) {
						pitcherCount++;					
					}
					if (pitcherCount == 5) {
						System.out.println("Error: already drafted player for the position P");
						return;
					}
				}
			}
		}
		
		for (int i = 0; i < allPlayers.size(); i++) {
			//put all players that arent pitchers into the new list
			if (allPlayers.get(i).getPosition().equals("P") && !allPlayers.get(i).getDrafted()) {
				playersToSort.addPlayer(allPlayers.get(i));
			}
		}
		//sort based on their rank (high to low)
		Collections.sort(playersToSort.getPlayerList(), new sortByRank());
					
		//output
			System.out.println(playersToSort);
	}
	//comparator inner class
		class sortByRank implements Comparator<Player> {

			//sort in descending order based on the rank
			public int compare(Player player1, Player player2) {
				return (int) (player2.getRank() - player1.getRank());
			}
			
		}
}
