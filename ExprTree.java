import java.util.Scanner;
import java.util.Stack;
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
			System.out.print(charExprArr[i]);
		}
		
		String prefix = infixToPreFix(charExprArr);
		System.out.println(prefix);
		
		
		
		return validInput;
	}
	
	//The expression key code needs the input to be in prefix notation, this will translate it for us
	private String infixToPreFix(char[] input) {
		//array for output
		char[] preFixInput = new char[input.length];
		
		//stack for operators and operands
		Stack<Character> operators = new Stack<Character>();
		Stack<String> operands = new Stack<String>();
		
		//cycle through the infix char array 
		for (int i = 0; i < input.length; i++) {
			
			//place operand in the right stack if it is one
			if (!isOperator(input[i])) {
				operands.push(Character.toString(input[i]));
			}
			
			//if it is an operator then push it to the right place in operator stack
			while (!operators.empty() && getPriority(input[i]) <= getPriority(operators.peek())) {
				String operand1, operand2;
				operand1 = operands.pop();
				operand2 = operands.pop();
				char operator = operators.pop();
				String temp = operator + operand2 + operand1;
				operands.push(temp);
			}
			operators.push(input[i]);
		} 
		//empty stack to build final string
		while (!operators.empty()) {
			String operand1, operand2;
			operand1 = operands.pop();
			operand2 = operands.pop();
			char operator = operators.pop();
			String temp = operator + operand2 + operand1;
			operands.push(temp);
		}

		
		return operands.pop();
	}
	
	//helper functions
	private int getPriority(char operator) {
		if (operator == '+' || operator == '-') {
			return 1;
		} else {
			return 2;
		}
	}
	
	private boolean isOperator(char c) {
		if (c == '+' || c == '-' ||  c == '*' || c == '/') {
			return true;
		}
		
		return false;
	}
	
}
