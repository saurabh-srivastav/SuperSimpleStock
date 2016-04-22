/**
 * This is copyright for assignment from jpmc
 */
package com.csg.sandbox.assignments.business;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.csg.sandbox.assignments.enums.StockType;

/**
 * @author Saurabh
 *
 */
public class InMemoryDbTest {
	
	private InMemoryDb inMemoryDb= null;
	
	ApplicationContext context = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.inMemoryDb = new InMemoryDb();
		this.context = new AnnotationConfigApplicationContext(InMemoryDb.class);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.inMemoryDb = null;
		this.context = null;
	}

	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.InMemoryDb#InMemDb()}.
	 */
	@Test 
	public void testInMemDb() {
		Map <String, StockDetails> unmodifiableMap = inMemoryDb.InMemDb();
		assertNotNull(unmodifiableMap);
		@SuppressWarnings("unchecked")
		final Map<String, StockDetails> dbMap = context.getBean("ExchangeDb", Map.class);
		assertNotNull(dbMap);
		
	}
	/**
	 * Test method to check that we can't add any value after initialisation {@link com.csg.sandbox.assignments.business.InMemoryDb#InMemDb()}.
	 */
	@Test (expected=UnsupportedOperationException.class)
	public void testInMemDbNegative() {
		@SuppressWarnings("unchecked")
		final Map<String, StockDetails> dbMap = context.getBean("ExchangeDb", Map.class);
		assertNotNull(dbMap);
		dbMap.put("POP1", new StockDetails("POP",StockType.COMMON,2.0,0.0,0.0));
		
	}

}
