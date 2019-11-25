package COSC381Baseball;

public class ExprTreeNode {
	
	 // Data members
    private char element;      // Expression tree element
    private ExprTreeNode left, right;    // References to the left and right children
    

    // Constructor
    public ExprTreeNode ( char elem, ExprTreeNode leftPtr, ExprTreeNode rightPtr )
    {          
    	element=elem;
    	left=leftPtr;
    	right=rightPtr;
    }
    
    public void setElement(char c)
    {
    	element=c;
    }
    
    public ExprTreeNode setLeft(ExprTreeNode l)
    {
    	return left=l;
    }
    
    public ExprTreeNode setRight(ExprTreeNode r)
    {
    	return right=r;
    }
    
    public char getElement()
    {
    	return element;
    }
    
    public ExprTreeNode getLeft()
    {
    	return left;
    }
    
    public ExprTreeNode getRight()
    {
    	return right;
    }
   
}

