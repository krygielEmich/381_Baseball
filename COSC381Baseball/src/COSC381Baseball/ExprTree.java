package COSC381Baseball;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
public class ExprTree {
	
	//root of the tree
	private ExprTreeNode root;
	private boolean expressionSet = false;
	private ArrayList<Player> playerArray;
	public int evalIndex = 0;
	
	public ExprTree() {
		
	}
	public ExprTree(PlayerList players) {
		playerArray = players.getPlayerList(); 
	}
	
	
	public boolean evalfun(String inputString) {		
		return this.buildTree(inputString, false);
	}
	
	public boolean pEvalfun(String inputString) {
		return this.buildTree(inputString, true);
	}
	
	private boolean buildTree(String inputString, boolean isPitcher) {
		evalIndex = 0;
		boolean validInput = true;
		String inputExpr = "";
		//gather the input
		if (inputString.equals("ignore")) {
			if (!isPitcher) {
				System.out.println("Please enter the expression you would like to use for non pitchers. ");
				System.out.println("The supported stats are: AVG, OBP, SLG, and OPS. Please insert a space between each part. ");
				System.out.println("E.g. AVG * OBP + SLG / OPS");
			} else {
				System.out.println("Please enter the expression you would like to use for ranking pitchers.");
				System.out.println("The supported stats are: AVG, ERA, ER, BB. Please insert a space between each part. ");
				System.out.println("E.g. K * ERA + ER / BB");
			}
			Scanner keyboard = new Scanner(System.in);
			inputExpr = keyboard.nextLine();
		}
		//check that the input is using valid stats
		//turn the input into a string array for conversions/checking
		
		String[] inputArr = inputExpr.split(" ");
		if (!inputString.equals("ignore")) {
			inputArr = inputString.split(" ");
		}
		String[] stringExprArr = new String[inputArr.length];
		for (int i = 0; i < inputArr.length; i++) {
			//non pitcher route
			if (!isPitcher) {
				if (inputArr[i].equals("AVG")) {
					stringExprArr[i] = "A";
				} else if (inputArr[i].equals("OBP")) {
					stringExprArr[i] = "O";
				} else if (inputArr[i].equals("SLG")) {
					stringExprArr[i] = "S";
				} else if (inputArr[i].equals("OPS")) {
					stringExprArr[i] = "P";
				} else if (inputArr[i].equals("+")) {
					stringExprArr[i] = "+";
				} else if (inputArr[i].equals("-")) {
					stringExprArr[i] = "-";
				} else if (inputArr[i].equals("/")) {
					stringExprArr[i] = "/";
				} else if (inputArr[i].equals("*")) {
					stringExprArr[i] = "*";
				} else if (Character.isDigit(inputArr[i].charAt(0))) {
					stringExprArr[i] = inputArr[i];
				} else {
					System.out.println("Invalid expression entered. Please check your expression and try again");
					return false;
				}
			} else {//is pitcher
				if (inputArr[i].equals("ER")) {
					stringExprArr[i] = "E";
				} else if (inputArr[i].equals("ERA")) {
					stringExprArr[i] = "R";
				} else if (inputArr[i].equals("K")) {
					stringExprArr[i] = "K";
				} else if (inputArr[i].equals("BB")) {
					stringExprArr[i] = "B";
				} else if (inputArr[i].equals("+")) {
					stringExprArr[i] = "+";
				} else if (inputArr[i].equals("-")) {
					stringExprArr[i] = "-";
				} else if (inputArr[i].equals("/")) {
					stringExprArr[i] = "/";
				} else if (inputArr[i].equals("*")) {
					stringExprArr[i] = "*";
				} else if (Character.isDigit(inputArr[i].charAt(0))) {
					stringExprArr[i] = inputArr[i];
				} else {
					System.out.println("Invalid expression entered. Please check your expression and try again");
					return false;
				}
			}
		}
		
		String prefix = infixToPreFix(stringExprArr);
		String prefixString = new String(prefix);
		
		//test conversion. should be prefix with spaces between each element
		System.out.println("infix");
		for (int i = 0; i < stringExprArr.length; i++) {
			System.out.print(stringExprArr[i] + " ");
		}
		System.out.println("\n" + prefix);
		
		try {
			this.build(prefixString);
		} catch (IOException e) {}
		
		this.evaluate(isPitcher);
		
		expressionSet = true;
		
		
		return validInput;
	}
	
	private void evaluate (boolean isPitcher)                // Evaluate expression   
    {  
    	 double result = 0;
    	if (root != null) {
    		//call eval sub for every player to set their rank
    		for (int i = 0; i < playerArray.size(); i++) {
    			Player currentPlayer = playerArray.get(i);
    			//check if this is being run for pitchers, if it is then only set the rank for them
    			if (isPitcher) {
    				if (currentPlayer.getPosition().equals("P")) {
    					result = evlauateSub(root, playerArray.get(i));
    	    			playerArray.get(i).setRank(result);
    				}
    			} else {
    				if (!currentPlayer.getPosition().equals("P")) {
    					result = evlauateSub(root, playerArray.get(i));
    	    			playerArray.get(i).setRank(result);
    				}
    			}
    			
    		}
    	}
    }
	
	//The expression key code needs the input to be in prefix notation, this will translate it for us
	private String infixToPreFix(String[] input) {
		//stack for operators and operands
		Stack<String> operators = new Stack<String>();
		Stack<String> operands = new Stack<String>();
		
		//cycle through the infix char array 
		for (int i = 0; i < input.length; i++) {
			
			if (input[i].equals("(")) {
				operators.push(input[i]);
			}
			
			//closing paren
			if (input[i].equals(")")) {
				while (!operators.empty() && operators.peek().equals("(")) {
					//add operaotrs and operands together for output
					String operand1, operand2 = "";
					operand1 = operands.pop();
					operand2 = operands.pop();
					String operator = operators.pop();
					String currentOutput = operator + " " + operand2 + " " +operand1;
					operands.push(currentOutput);
				}
				//once it leaves the loop remove the remaining paren
				operators.pop();
			}
			
			//place operand in the right stack if it is one
			if (!isOperator(input[i])) {
				operands.push(input[i]);
			//current char is an operator
			} else {
				//if it is an operator then push it to the right place in operator stack
				while (!operators.empty() && getPriority(input[i]) <= getPriority(operators.peek())) {
					String operand1, operand2 = "";
					operand1 = operands.pop();
					operand2 = operands.pop();
					String operator = operators.pop();
					String temp = operator + " " + operand2 + " " + operand1;
					operands.push(temp);
				}
				operators.push(input[i]);
			}
		}
		
		//empty stack to build final string
		while (!operators.empty()) {
			String operand1, operand2;
			operand1 = operands.pop();
			operand2 = operands.pop();
			String operator = operators.pop();
			String temp = operator + " " + operand2 + " " + operand1;
			operands.push(temp);
		}

		
		return operands.pop();
	}
	
	//helper functions
	private int getPriority(String operator) {
		if (operator.equals("+") || operator.equals("-")) {
			return 1;
		} else {
			return 2;
		}
	}
	
	private boolean isOperator(String c) {
		if (c.equals("+") || c.equals("-") ||  c.equals("*") || c.equals("/")) {
			return true;
		}
		
		return false;
	}
	
	//re used builder function for expression tree (kyle)
	 private void build (String input) throws IOException  {      
		 	
	    	String[] inputArr = input.split(" ");
	    	try {
	    		IndexTracker index = new IndexTracker();
	    		root=this.buildSub(inputArr, index);
	    	}catch (IOException e) {
	    		System.out.println("Not enough opperands provided");
	    	}	
	    }
	
	 
	 //helper for build
	 private ExprTreeNode buildSub ( String[] inputArr, IndexTracker index)  throws IOException {  
		 		if (evalIndex == inputArr.length) {
		 			return null;
		 		}
	    		ExprTree n= new ExprTree();
	    		n.root= new ExprTreeNode(inputArr[index.getIndex()], null, null);
	    		
	    		if (n.root.getElement().equals("+") || n.root.getElement().equals("-") || n.root.getElement().equals("*") || 
	        	n.root.getElement().equals("/")) {
	    			if (n.root.getLeft()==null) {
	    				index.increment();
	    				n.root.setLeft(n.buildSub(inputArr, index));
	    			}			
	    			if (n.root.getRight()==null) {
	    				index.increment();
	    				n.root.setRight(n.buildSub(inputArr, index));
	    			}	    				
	    		}
	    	return n.root;
	    }
	 
	 private double evlauateSub(ExprTreeNode node, Player player)
	    {
	    	ExprTree subTree= new ExprTree();
	    	subTree.root=node;
	    	double result=0, num1=0, num2=0;
	    	if (subTree.root.getElement().equals("+") || subTree.root.getElement().equals("-") || subTree.root.getElement().equals("*") || 
	    	subTree.root.getElement().equals("/")) {
	    		if (subTree.root.getLeft() != null) {
	    			num1=evlauateSub(subTree.root.getLeft(), player);
	    		}			
	    		if (subTree.root.getRight() != null) {
	    			num2=evlauateSub(subTree.root.getRight(), player);
	    		}
	    		//do the math
	    		if (subTree.root.getElement().equals("+")) {
	    			result = num1+num2;
	    		}
	    		if (subTree.root.getElement().equals("-")) {
	    			result= num1-num2;
	    		}
	    		if (subTree.root.getElement().equals("*")) {
	    			result= num1*num2;
	    		}
	    		if (subTree.root.getElement().equals("/")) {
	    			result= num1/num2;
	    		}
	    	}
	    	else {
	    		if (subTree.root.getElement().equals("A")) {
	    			result = player.getAvg();
	    		} else if (subTree.root.getElement().equals("O")) {
	    			result = player.getObp();
	    		} else if (subTree.root.getElement().equals("S")) {
	    			result = player.getSlg();
	    		} else if (subTree.root.getElement().equals("P")) {
	    			result = player.getOps();
	    		}  else if (subTree.root.getElement().equals("E")) {
	    			result = player.getEr();
	    		}  else if (subTree.root.getElement().equals("B")) {
	    			result = player.getBb();
	    		}  else if (subTree.root.getElement().equals("K")) {
	    			result = player.getK();
	    		} else if (Character.isDigit(subTree.root.getElement().charAt(0))) {
	    			result = Double.parseDouble(subTree.root.getElement());
	    		}
	    			
	    	}	    	
	    	return result;
	    } 
	 class IndexTracker {
		 private int index = 0;
		 
		 public void increment() {
			 index++;
		 }
		 public int getIndex() {
			 return index;
		 }
	 }
}
