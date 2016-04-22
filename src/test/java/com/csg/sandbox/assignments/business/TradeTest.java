/**
 * This is copyright for assignment from jpmc
 */
package com.csg.sandbox.assignments.business;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.csg.sandbox.assignments.enums.TradeType;

/**
 * @author Saurabh
 *
 */
public class TradeTest {
	
	private Trade trade = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		trade = new Trade(TradeType.BUY, 1, 3.0) ;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		trade=null;
	}

	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.Trade#Trade(com.csg.sandbox.assignments.enums.TradeType, java.lang.Integer, java.lang.Double)}.
	 */
	@Test
	public void testTrade() {
		assertNotNull(trade );
		assertEquals(TradeType.BUY, trade.getType());
	}

	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.Trade#toString()}.
	 */
	@Test
	public void testToString() {
		assertNotNull(trade.toString());
	}

}
