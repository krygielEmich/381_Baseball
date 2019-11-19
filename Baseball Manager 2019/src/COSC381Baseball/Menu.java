package COSC381Baseball;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private boolean quit=false;
	Scanner stdIn=new Scanner(System.in);

	//Starts with the program, prompts user for what amount of members there are in the league
	//@return int input, has to be a positive integer
	public int initialize() {
		int input = 0;
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
	//Code recycled from last project with some tweaks, uses switch statement and splits 
	//arguments into String input
	public void display(MemberList memberList) {
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
		if(stdIn.hasNext()) {
			input = stdIn.next().split(" ");
			selection=input[0];
		}
		else {
			selection="null";
		}
		stdIn.nextLine();
		switch(selection) {
		case "ODRAFT":
			if(input.length==3) {
				oDraft(input[1],input[2]);
				break;
			}
			else input=null;
		case "IDRAFT":
			if(input.length==2) {
				iDraft(input[1]);
				break;
			}
			else input=null;
		case "OVERALL":
			if(input.length==2) {
				overall(input[1]);
				break;
			}
			else input=null;
		case "POVERALL":
			if(input.length==1) {
				poverall();
				break;
			}
			else input=null;
		case "TEAM":
			if(input.length==2) {
				team(input[1]);
				break;
			}
			else input=null;
		case "STARS":
			if(input.length==2) {
				stars(input[1]);
				break;
			}
			else input=null;
		case "SAVE":
			if(input.length==2) {
				save(input[1]);
				break;
			}
			else input=null;
		case "QUIT":
			this.quit = true;
			break;
		case "RESTORE":
			if(input.length==2) {
				restore(input[1]);
				break;
			}
			else input=null;
		//These two are gonna be trickier... just placeholders for now
		case "EVALFUN":
			if(input.length==2) {
				evalFun(input[1]);
				break;
			}
			else input=null;
		case "PEVALFUN":
			if(input.length==2) {
				pEvalFun(input[1]);
				break;
			}
			else input=null;
		default:
			System.out.println("Invalid Selection!");
			break;
			}
	}
		//Helper method for whenever we need a user to input a String
		public String getInput(String x) throws InputMismatchException{
			String input = "";
			System.out.print(x);
			input = stdIn.next();
			return input;
		}

	//Main driving method, where the list of members is intialized, as long as where most other methods are
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
