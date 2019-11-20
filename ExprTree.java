import java.util.Scanner;
public class ExprTree {
	
	public boolean evalfun(String inputString) {
		boolean clear = this.buildTree(inputString);
		if (clear) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean buildTree(String inputString) {
		
		boolean validInput = true;
		String inputExpr = "";
		//gather the input
		if (inputString.equals("ignore")) {
			System.out.println("Please enter the expression you would like to use for ranking players. ");
			System.out.println("The supported stats are: AVG, OBP, SLG, and OPS. Please insert a space between each part. ");
			System.out.println("E.g. AVG * OBP + SLG / OPS");
			Scanner keyboard = new Scanner(System.in);
			inputExpr = keyboard.nextLine();
		}
		//check that the input is using valid stats
		//turn the input into a string array for conversions/checking
		
		String[] inputArr = inputExpr.split(" ");
		if (!inputString.equals("ignore")) {
			inputArr = inputString.split(" ");
		}
		char[] charExprArr = new char[inputArr.length];
		for (int i = 0; i < inputArr.length; i++) {
			if (inputArr[i].equals("AVG")) {
				charExprArr[i] = 'A';
			} else if (inputArr[i].equals("OBP")) {
				charExprArr[i] = 'O';
			} else if (inputArr[i].equals("SLG")) {
				charExprArr[i] = 'S';
			} else if (inputArr[i].equals("OPS")) {
				charExprArr[i] = 'P';
			} else if (inputArr[i].equals("OBP")) {
				charExprArr[i] = 'O';
			} else if (inputArr[i].equals("+")) {
				charExprArr[i] = '+';
			} else if (inputArr[i].equals("-")) {
				charExprArr[i] = '-';
			} else if (inputArr[i].equals("/")) {
				charExprArr[i] = '/';
			} else if (inputArr[i].equals("*")) {
				charExprArr[i] = '*';
			} else {
				System.out.println("Invalid expression entered. Please check your expression and try again");
				return false;
			}
		}
		
		//check translate
		for (int i = 0; i < charExprArr.length; i++) {
			System.out.println(charExprArr[i]);
		}
		
		
		
		return validInput;
	}
}
