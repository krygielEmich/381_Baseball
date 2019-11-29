package COSC381Baseball;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private boolean quit=false;
	public PlayerList mlbList;

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
	//Eventually this will pull from the MLB database, right now I just have
	//A placeholder so I can implement Drafting.
	public void initializeMLBList() {
		mlbList = new PlayerList(new Player(365, 10, "C", "J.Martinez"));
		mlbList.addPlayer(new Player(114,19,"P","J.Verlander"));
		mlbList.addPlayer(new Player(125,13,"1B","M.Cabrera"));
		
		//more test data
		Player testPlayer1 = new Player();
		testPlayer1.setPositon("C");
		testPlayer1.setObp(3);
		testPlayer1.setSlg(6);
		testPlayer1.setOps(67.1);
		testPlayer1.setAvg(9);
		testPlayer1.setName("Kyle");
		
		Player testPlayer2 = new Player();
		testPlayer2.setPositon("1B");
		testPlayer2.setObp(3);
		testPlayer2.setSlg(6);
		testPlayer2.setOps(0.8);
		testPlayer2.setAvg(9);
		testPlayer2.setName("Connor");
		
		Player testPlayer3 = new Player();
		testPlayer3.setPositon("P");
		testPlayer3.setBb(3);
		testPlayer3.setEr(6);
		testPlayer3.setEra(6);
		testPlayer3.setK(9.2);
		testPlayer3.setName("Patrick");

		Player testPlayer4 = new Player();
		testPlayer4.setPositon("P");
		testPlayer4.setBb(3.5);
		testPlayer4.setEr(1);
		testPlayer4.setEra(7);
		testPlayer4.setK(49);
		testPlayer4.setName("Matt");
		
		mlbList.addPlayer(testPlayer1);
		mlbList.addPlayer(testPlayer2);
		mlbList.addPlayer(testPlayer3);
		mlbList.addPlayer(testPlayer4);
		
	}
	//Code recycled from last project with some tweaks, uses switch statement and splits 
	//arguments into String input
	public void display(MemberList memberList) {
		Scanner stdIn=new Scanner(System.in);
		System.out.println("\nPossible Commands:");
		System.out.println(
				  "ODRAFT (Player Name) (Member Name)\n"
				+ "IDRAFT (Player Name)\n"
				+ "OVERALL (Position)\n"
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
			else System.out.println("Invalid Selection!");
			break;
		case "IDRAFT":
			if(input.length==2) {
				ODraft oDraft = new ODraft(mlbList.getPlayer(input[1]),memberList.memberList.get(0),mlbList);
				if(oDraft.getValidInput())mlbList.getPlayer(input[1]).setDrafted(true);
				break;
			}
			else System.out.println("Invalid Selection!");
			break;
		case "OVERALL":

			if(input.length==1) {
				Overalls overall = new Overalls(mlbList);
				System.out.println("Enter the position you would like to display. Press enter to display all ");
				String position = stdIn.nextLine();
				overall.overall(position);

			if(input.length==2) {
				//overall(input[1]);

				break;
			}
			else System.out.println("Invalid Selection!");
			break;
			}
		case "POVERALL":
			if(input.length==1) {

				Overalls overall = new Overalls(mlbList);
				overall.pOverall();

				//poverall();

				break;
			}
			else System.out.println("Invalid Selection!");
			break;
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
			else System.out.println("Invalid Selection!");
			break;
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
			else System.out.println("Invalid Selection!");
			break;
		case "SAVE":
			if(input.length==2) {
				Save(input[1], memberList);
				break;
			}
			else System.out.println("Invalid Selection!");
			break;
		case "QUIT":
			this.quit = Quit();
			System.out.println("Exiting Baseball Manager...");
			System.exit(0);
			break;
		case "RESTORE":
			if(input.length==2) {
				//Set the member list
				memberList = Restore(input[1], memberList);
				break;
			}
			else System.out.println("Invalid Selection!");
			break;
		//These two are gonna be trickier... just placeholders for now
		case "EVALFUN":
			if(input.length==1) {
				ExprTree evalTree = new ExprTree(mlbList);
				evalTree.evalfun("ignore");
				break;
			}
			else System.out.println("Invalid Selection!");
			break;
		case "PEVALFUN":
			if(input.length==1) {
				ExprTree evalTree = new ExprTree(mlbList);
				evalTree.pEvalfun("ignore");
				break;
			}
			else System.out.println("Invalid Selection!");
			break;
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
	public static void main(String args[]) {
		Menu menu = new Menu();
		int memberAmt = menu.initialize();
		MemberList memberList = new MemberList(new Member(null, menu.getInput("Enter the name of Member 1: ")));
		for(int i = 2; i<=memberAmt;i++) {
			memberList.addMember(new Member(null,menu.getInput("Enter the name of Member "+i+": ")));
		}
		while(!menu.quit)menu.display(memberList);
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
			
			//Now retrieving all player data
			if(fileScan.hasNextLine())
			{
				//Create empty array of values from line
				String[] tempMember = fileScan.nextLine().split(" ");
				
				//Check if has player data
				if(tempMember[1].equalsIgnoreCase("Start"))
				{
					
					//Player Data exists for this member, begin loop for player data
					while(!fileScan.next().equalsIgnoreCase("End"))
					{
						if(fileScan.hasNextLine())
						{
							//Create empty array of values from line
							String[] newTempPlayer = fileScan.nextLine().split(" ");

							//Now Create new player class and retrieve converted data
							Player newGuy = new Player();
							newGuy = this.ToPlayerList(newTempPlayer);
							
							//Add to new list
							newMemPlayerList.addPlayer(newGuy);
						}
					}
				}
				
				//Now Create new member class and retrieve converted data
				Member newMem = new Member();
				
				//Add the new player list	
				newMem = this.ToMemberList(tempMember);
				
				newMem.playerList = newMemPlayerList;
				
				//Add to new list
				newMembers.addMember(newMem);
			}
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
					guy.team = player[i];
					break;
				case 2:
					guy.position = player[i];
					break;
				case 3:
					guy.obp = Float.valueOf(player[i]);
					break;
				case 4:
					guy.slg = Float.valueOf(player[i]);
					break;
				case 5:
					guy.ops =  Float.valueOf(player[i]);
					break;
				case 6:
					guy.avg = Float.valueOf(player[i]);
					break;
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
		text+= PlayersToString(this.mlbList);
		
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
				text+=" Start\n";
				text+= members.memberList.get(i).playerList.toStringSave()+"\n";
				text+="End\n";
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
