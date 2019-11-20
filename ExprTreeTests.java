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
	
}
