package COSC381Baseball;

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
		switch(selection) {
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
			if(input.length==2) {
				//overall(input[1]);
				break;
			}
			else System.out.println("Invalid Selection!");
			break;
		case "POVERALL":
			if(input.length==1) {
				//poverall();
				break;
			}
			else System.out.println("Invalid Selection!");
			break;
		case "TEAM":
			if(input.length==2) {
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
					System.out.println("No players draft!\n");
					break;
				}
				STARS stars = new STARS(memberList.getMember(input[1]));
				break;
			}
			else System.out.println("Invalid Selection!");
			break;
		case "SAVE":
			if(input.length==2) {
				//save(input[1]);
				break;
			}
			else System.out.println("Invalid Selection!");
			break;
		case "QUIT":
			this.quit = true;
			break;
		case "RESTORE":
			if(input.length==2) {
				//restore(input[1]);
				break;
			}
			else System.out.println("Invalid Selection!");
			break;
		//These two are gonna be trickier... just placeholders for now
		case "EVALFUN":
			if(input.length==2) {
				ExprTree evalTree = new ExprTree(mlbList);
				evalTree.evalfun("ignore");
				break;
			}
			else System.out.println("Invalid Selection!");
			break;
		case "PEVALFUN":
			if(input.length==2) {
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
}
