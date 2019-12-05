package COSC381Baseball;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private boolean quit=false;
	public PlayerList mlbList;
	public static MemberList restoredList;

	//Starts with the program, prompts user for what amount of members there are in the league
	//@return int input, has to be a positive integer
	public int initialize() {
		Scanner stdIn=new Scanner(System.in);
		int input = 0;
		initializeMLBList();
		System.out.print("How many members are drafting: ");
		while(input<=0) {
			input = 0;
			try {
				input = stdIn.nextInt();
				if(input<=0) throw new InputMismatchException("e");
			}
			catch(InputMismatchException e) {
				System.out.print("Invalid input, try again: ");
			}
		}
		return input;	
	}
	// this will pull from the MLB database
	public void initializeMLBList() {		
		//Player	Team	Pos	OBP	SLG	OPS	AVG for Batters
		//Player	Team	Pos	ER	K	BB	ERA for Pitchers
		PlayerList draftList= new PlayerList();
				
		//Retrieve the input name
		String name = "Player_Data";
		
		//Create variable file
		File file;
		
		file = new File(name+".txt");
		
		//If the string at the default user's path exists (old save), use that file to overwrite
		if(file.isFile())
		{
			//do
		}
		else
		{
			//If file doesnt exist, exit the restore
			System.out.println("Path Not Found: Unable to download MLB List from"+name+".txt.");
			return;
		}
		
		//Create a new scanner for the file
		Scanner fileScan;
		try 
		{
			fileScan = new Scanner(file);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("File Not Found: Unable to download MLB List from"+name+".txt.");
			return;
		}
		
		//Create counter to check player count
		int counter = 0;
		
		//While file is scanning, store values into proper places
		while(fileScan.hasNext() )
		{	
			//If beginning, count number of position players
			if(counter == 0)
			{
				counter = fileScan.nextInt()+1;
			}
			//Now retrieving all player data
			else if(fileScan.hasNextLine())
			{
				
				//Decrement counter, adding player
				counter--;
				
				//Create empty array of values from line
				String[] tempPlayer = fileScan.nextLine().split("\t");

				//Now Create new player class and retrieve converted data
				Player newGuy = new Player();
				newGuy = this.ToPlayerList(tempPlayer);
				
				//Add to new list
				draftList.addPlayer(newGuy);
			}
		}
			
		//Now update mlbdata with new player list
		draftList.playerList.remove(0);
		draftList.playerList.remove(135);
		this.mlbList = draftList;
		
		//Once file no longer has any values, close the scanner
		fileScan.close();
		
		//Print confirmation of successful restore
		System.out.println("The MLB List has been implemented from: "+name+".txt.");	
	}
	//Code recycled from last project with some tweaks, uses switch statement and splits 
	//arguments into String input
	public void display(MemberList memberList) {
		Scanner stdIn=new Scanner(System.in);
		System.out.println("\nPossible Commands:");
		System.out.println(
				  "ODRAFT (Player Name) (Member Name)\n"
				+ "IDRAFT (Player Name)\n"
				+ "OVERALL\n"
				+ "POVERALL\n"
				+ "TEAM (Member Name)\n"
				+ "STARS (Member Name)\n"
				+ "SAVE (File Name)\n"
				+ "QUIT\n"
				+ "RESTORE (File Name)\n"
				+ "EVALFUN (Expression)\n"
				+ "PEVALFUN (Expression)\n");
		String[] input = null;
		String selection;
		String userIn = stdIn.nextLine();
		input = userIn.split(" ");
		selection=input[0];
		switch(selection.toUpperCase().trim()) {
		case "ODRAFT":
			if(input.length==3) {
				if(memberList.getMember(input[2])==null) {
					System.out.println("Member doesn't exist");
					break;
				}
				ODraft oDraft = new ODraft(mlbList.getPlayer(input[1]),memberList.getMember(input[2]),mlbList);
				if(oDraft.getValidInput())mlbList.getPlayer(input[1]).setDrafted(true);
				break;
			}
			else 
			{
				System.out.println("Invalid Selection!");
				break;
			}
		case "IDRAFT":
			if(input.length==2) {
				ODraft oDraft = new ODraft(mlbList.getPlayer(input[1]),memberList.memberList.get(0),mlbList);
				if(oDraft.getValidInput())mlbList.getPlayer(input[1]).setDrafted(true);
				break;
			}
			else 
			{
				System.out.println("Invalid Selection!");
				break;
			}
		case "OVERALL":
			if(input.length==1) {
				Overalls overall = new Overalls(mlbList, memberList);
				System.out.println("Enter the position you would like to display. Press enter to display all ");
				String position = stdIn.nextLine();
				overall.overall(position);
				break;
			}
			else 
			{
				System.out.println("Invalid Selection!");
				break;
			}
		case "POVERALL":
			if(input.length==1) {

				Overalls overall = new Overalls(mlbList, memberList);
				overall.pOverall();

				//poverall();

				break;
			}
			else 
			{
				System.out.println("Invalid Selection!");
				break;
			}
		case "TEAM":
			if(input.length==2) {

				//team(input[1]);

				if(memberList.getMember(input[1])==null) {
					System.out.println("Member doesn't exist");
					break;
				}
				if(memberList.getMember(input[1]).playerList==null) {
					System.out.println("No players draft!\n");
					break;
				}
				Team team = new Team(memberList.getMember(input[1]));

				break;
			}
			else 
			{
				System.out.println("Invalid Selection!");
				break;
			}
		case "STARS":
			if(input.length==2) {
				if(memberList.getMember(input[1])==null) {
					System.out.println("Member doesn't exist");
					break;
				}
				if(memberList.getMember(input[1]).playerList==null) {
					System.out.println("No players drafted!\n");
					break;
				}
				STARS stars = new STARS(memberList.getMember(input[1]));
				break;
			}
			else 
			{
				System.out.println("Invalid Selection!");
				break;
			}
		case "SAVE":
			if(input.length==2) {
				Save(input[1], memberList);
				break;
			}
			else 
			{
				System.out.println("Invalid Selection!");
				break;
			}
		case "QUIT":
			this.quit = Quit();
			System.out.println("Exiting Baseball Manager...");
			System.exit(0);
			break;
		case "RESTORE":
			if(input.length==2) {
				//Set the member list
				restoredList = Restore(input[1], memberList);
				break;
			}
			else 
			{
				System.out.println("Invalid Selection!");
				break;
			}
		//These two are gonna be trickier... just placeholders for now
		case "EVALFUN":
			if(input.length==1) {
				ExprTree evalTree = new ExprTree(mlbList);
				evalTree.evalfun("ignore");
				break;
			}
			else 
			{
				System.out.println("Invalid Selection!");
				break;
			}
		
		case "PEVALFUN":
			if(input.length==1) {
				ExprTree evalTree = new ExprTree(mlbList);
				evalTree.pEvalfun("ignore");
				break;
			}
			else 
			{
				System.out.println("Invalid Selection!");
				break;
			}
		
		default:
			System.out.println("Invalid Selection!");
			break;
			}
	}
		//Helper method for whenever we need a user to input a String
		public String getInput(String x) throws InputMismatchException{
			Scanner stdIn=new Scanner(System.in);
			String input = "";
			System.out.print(x);
			input = stdIn.next();
			return input;
		}

	//Main driving method, where the list of members is intialized, as well as where most other methods are
	//running
	public static void main(String args[]) 
	{
		Menu menu = new Menu();
		int memberAmt = menu.initialize();
		MemberList memberList = new MemberList(new Member(null, menu.getInput("Enter the name of Member 1: ")));
		for(int i = 2; i<=memberAmt;i++) {
			memberList.addMember(new Member(null,menu.getInput("Enter the name of Member "+i+": ")));
		}
		while(!menu.quit)
		{
			if(restoredList != null)
			{
				memberList = restoredList;
			}
			menu.display(memberList);
		}
			
	}
	//This method will restore the current state of the collection class in the program from the savedat.txt file
	public MemberList Restore(String fileName, MemberList members)
	{
		//Retrieve the input name
		String name = fileName;
		
		//Create variable file
		File file;
		
		file = new File(name+".fantasy.txt");
		
		//If the string at the default user's path exists (old save), use that file to overwrite
		if(file.isFile())
		{
			//do
		}
		else
		{
			//If file doesnt exist, exit the restore
			System.out.println("Path Not Found: Unable to restore the state of the system from file named "+
			name+".fantasy.txt.");
			return null;
		}
		
		//Create a new scanner for the file
		Scanner fileScan;
		try 
		{
			fileScan = new Scanner(file);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("File Not Found: Unable to restore the state of the system from file named "+
			name+".fantasy.txt.");
			return null;
		}
		
		//Set counter
		int i = -1;
			
		//Check if has read in num of members
		int numMembs = 0;
		
		if(i== -1 && fileScan.hasNextInt())
		{
			numMembs = fileScan.nextInt();
			
			//Move to next line
			fileScan.nextLine();
		}
		
		//RESTORING MEMBERS HERE///////////////////////////////
		//Create new temporary member list here
		MemberList newMembers = new MemberList();
		
		//Scan for and restore out all members in the save file
		for(int j = 0; j < numMembs; j++)
		{
			//Create empty new member playerList
			PlayerList newMemPlayerList = new PlayerList();
			
			//Now Create new member class and retrieve converted data
			Member newMem = new Member();
			
			//Create empty array of values from line
			String tempMember = fileScan.nextLine();
			
			//Set member name
			newMem.name = tempMember;
			
			//Now retrieving all player data
			while(fileScan.hasNextLine())
			{
					//Create empty array of values from line
					String[] newTempPlayer = fileScan.nextLine().split(" ");

					//Now Create new player class and retrieve converted data
					Player newGuy = new Player();
					newGuy = this.ToPlayerList(newTempPlayer);
					
					//Add to new list
					newMemPlayerList.addPlayer(newGuy);
					
					//Exit loop and break
					if(fileScan.hasNextInt())
					{
						if(fileScan.nextInt() == -1)
							break;
					}
			}
			
			//Add the new player list	
			newMem.playerList = newMemPlayerList;
			
			//Add to new list
			newMembers.addMember(newMem);
		}
		
		//RESTORING PLAYERS HERE///////////////////////////////
		//Create new empty playerList
		PlayerList newList = new PlayerList();
		
		//While file is scanning, store values into proper places
		while(fileScan.hasNext())
		{	
			//Now retrieving all player data
			if(fileScan.hasNextLine())
			{
				//Create empty array of values from line
				String[] tempPlayer = fileScan.nextLine().split(" ");

				//Now Create new player class and retrieve converted data
				Player newGuy = new Player();
				newGuy = this.ToPlayerList(tempPlayer);
				
				//Add to new list
				newList.addPlayer(newGuy);
			}
		}
		
		//Now update mlbdata with new player list
		this.mlbList = newList;
		
		//Once file no longer has any values, close the scanner
		fileScan.close();
		
		//Print confirmation of successful restore
		System.out.println("The state of the system has been restore from "+name+".fantasy.txt.");
		
		//Now update the members list
		return newMembers;
	}
	
	//This method will save the current state of the collection class and the program to a .txt file
	public boolean Save(String fileName, MemberList members)
	{
		//Retrieve the file name
		String name = fileName;
		
		//Create variable file
		File file;
		
		file = new File(name+".fantasy.txt");
		
		//If the string at the default user's path exists (old save), use that file to overwrite
		if(file.isFile())
		{
			//do
		}
		else
		{
			//Else, create the default file save name
			file = new File(fileName+".fantasy.txt");
		}

		//Create a new buffered writer to write the save data to the file
		try 
		{
			BufferedWriter out = new BufferedWriter(new  FileWriter(file));
			
			//Retrieve string of text for save file
			String str = this.DraftToString(members);
			
			//write to the file
			out.write(str);
			
			//close the writer
			out.close();
			
			System.out.println("The state of the system has been save in "+name+".fantasy.txt.");
			return true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Unable to save the state of the system to file "+name+".fantasy.txt.");
			return false;
		}
	}
	//Make string of text that will be written to the save file	
	private Player ToPlayerList(String[] player)
	{
		//Create the empty player class
		Player guy = new Player();
		
		//For loop with a switch statement to sort values into strings
		for(int i = 0; i< player.length; i++)
		{
			switch(i)
			{
				case 0:
					guy.name = player[i];
					break;
				case 1:
					guy.mlbTeam = player[i];
					break;
				case 2:
					guy.position = player[i];
					break;
				case 3:
					if(guy.position.contains("P"))
					{
						guy.er = Float.valueOf(player[i]);
						break;
					}
					else
					{
						guy.obp = Float.valueOf(player[i]);
						guy.obp  = Math.round(guy.obp  * 1000.0) / 1000.0;
						break;
					}
				case 4:
					if(guy.position.contains("P"))
					{
						guy.k = Float.valueOf(player[i]);
						break;
					}
					else
					{
						guy.slg = Float.valueOf(player[i]);
						guy.slg  = Math.round(guy.slg  * 1000.0) / 1000.0;
						break;
					}
				case 5:
					if(guy.position.contains("P"))
					{
						guy.bb = Float.valueOf(player[i]);
						break;
					}
					else
					{
						guy.ops = Float.valueOf(player[i]);
						guy.ops  = Math.round(guy.ops  * 1000.0) / 1000.0;
						break;
					}
				case 6:
					if(guy.position.contains("P"))
					{
						guy.era = Float.valueOf(player[i]);
						guy.era  = Math.round(guy.era  * 1000.0) / 1000.0;
						break;
					}
					else
					{
						guy.avg = Float.valueOf(player[i]);
						guy.avg  = Math.round(guy.avg  * 1000.0) / 1000.0;
						break;
					}
				default:
					System.out.println("Error, should not have this many values");
					break;
			}
		}
	    //Return the player
		return guy;
	}
	//Make string of text that will be written to the save file	
	private Member ToMemberList(String[] member)
	{
		//Create the empty member class
		Member user = new Member();
		
		user.name = member[0];
		
	    //Return the member
		return user;
	}

	//Make string of text that will be written to the save file	
	private String DraftToString(MemberList members)
	{
		//Create the string
		String text = "";
		
		//First, append to string of members and their player lists
		text+= MembersToString(members);
		
		//Next, append toString of all players in the mlb list
		text+= PlayersToString(mlbList);
		
	    //Return the string
		return text;
	}
	//Make string of text that will be written to the save file	
	private String MembersToString(MemberList members)
	{
		//Create the string
		String text = "";
		
		//First, save the number of members on the first line
		text+= members.memberList.size()+"\n";
		
		for(int i = 0; i< members.memberList.size(); i++)
		{
			Member temp = members.memberList.get(i);
			text+= temp.name;
			
			//If member has a player list, print start and end and print out members
			if(temp.playerList != null)
			{
				text+="\nTeam\n";
				text+= members.memberList.get(i).playerList.toStringSave();
				text+="End of Team\n";
			}
			else
			{
				text+= " NULL\n";
			}	
		}
	    //Return the string
		return text;
	}
	//Make string of text that will be written to the save file	
	private String PlayersToString(PlayerList players)
	{
		//Create the string
		String text = "";
		
		text+= "\n"+players.toStringSave();
		
	    //Return the string
		return text;
	}
	
	public boolean Quit()
	{
		if(this.quit == false)
		{
			//Set true
			return true;
		}
		else
		{
			//Already true, still return true
			return true;
		}
	}
}
