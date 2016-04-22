/**
 * This is copyright for assignment from jpmc
 */
package com.csg.sandbox.assignments.calculator;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.csg.sandbox.assignments.business.InMemoryDb;
import com.csg.sandbox.assignments.business.StockDetails;
import com.csg.sandbox.assignments.exception.InvalidQuantityException;

/**
 * @author Saurabh
 *
 */
public class GBCECalculatorTest {
	
	private  Map<String, StockDetails> dbMap;

	/**
	 * @throws java.lang.Exception
	 */
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(InMemoryDb.class);

		// Run dividend and P/E Ratio routines
		this.setDbMap(context.getBean("ExchangeDb", Map.class));

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.dbMap = null;
	}

	/**
	 * Test method for {@link com.csg.sandbox.assignments.calculator.GBCECalculator#allShareIndex(java.util.Map)}.
	 */
	@Test
	public void testAllShareIndexTest1() throws InvalidQuantityException{
        
        for (StockDetails stock: dbMap.values()) {
            // Record some trades
        	for (int i=1; i <= 10; i++) {
					stock.buy(1, 2.0, new Date());
	        		stock.sell(1, 3.0, new Date());
        	}
        }
        Double GBCEallShareIndex = GBCECalculator.allShareIndex(dbMap);
        assertEquals(3.0, GBCEallShareIndex, 0.0);
	}

	/**
	 * Test method for {@link com.csg.sandbox.assignments.calculator.GBCECalculator#allShareIndex(java.util.Map)}.
	 */
	@Test
	public void testAllShareIndexTest2() throws InvalidQuantityException{
        
        for (StockDetails stock: dbMap.values()) {
            // Record some trades
        	for (int i=1; i <= 10; i++) {
					stock.buy(1, 2.0, new Date());
	        		stock.sell(1, 5.0, new Date());

        	}
        }
        Double GBCEallShareIndex = GBCECalculator.allShareIndex(dbMap);
        assertEquals(5.000000000000001, GBCEallShareIndex, 0.0);
	}

	/**
	 * @return the dbMap
	 */
	public Map<String, StockDetails> getDbMap() {
		return dbMap;
	}

	/**
	 * @param dbMap the dbMap to set
	 */
	public void setDbMap(Map<String, StockDetails> dbMap) {
		this.dbMap = dbMap;
	}


}
