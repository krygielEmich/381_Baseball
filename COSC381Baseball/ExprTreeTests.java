package COSC381Baseball;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ExprTreeTests {

	@Before
	public void setUp() throws Exception {
		ExprTree testTree = new ExprTree();
	}
	
	@Test
	public void testEvalBadInput() {
		ExprTree testTree = new ExprTree();
		assertEquals(false, testTree.evalfun("AVG + B - OPS"));
	}
	
	@Test
	public void testEvalGoodInput() {
		ExprTree testTree = new ExprTree();
		assertEquals(true, testTree.evalfun("AVG + OBP * SLG / OPS"));
	}
	
	public void testPEvalBadInput() {
		ExprTree testTree = new ExprTree();
		//assertEquals(false, testTree.pevalfun("AVG + B - OPS"));
	}
	
	public void testPEvalGoodInput() {
		ExprTree testTree = new ExprTree();
		//assertEquals(false, testTree.pevalfun("AVG + B - OPS"));
	}
	
}
