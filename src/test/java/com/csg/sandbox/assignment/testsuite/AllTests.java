/**
 * This is copyright for assignment from jpmc
 */
package com.csg.sandbox.assignment.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.csg.sandbox.assignments.business.InMemoryDb;
import com.csg.sandbox.assignments.business.StockDetailsTest;
import com.csg.sandbox.assignments.business.Trade;
import com.csg.sandbox.assignments.calculator.GBCECalculatorTest;

/**
 * @author Saurabh
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ StockDetailsTest.class, GBCECalculatorTest.class, Trade.class, InMemoryDb.class })

public class AllTests {

}
