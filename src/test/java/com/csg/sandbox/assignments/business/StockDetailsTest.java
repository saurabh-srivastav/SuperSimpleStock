/**
 * This is copyright for assignment from jpmc
 */
package com.csg.sandbox.assignments.business;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.csg.sandbox.assignments.business.StockDetails;
import com.csg.sandbox.assignments.business.Trade;
import com.csg.sandbox.assignments.enums.StockType;
import com.csg.sandbox.assignments.enums.TradeType;
import com.csg.sandbox.assignments.exception.InvalidQuantityException;

/**
 * @author Saurabh
 *
 */
public class StockDetailsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.StockDetails#dividend(java.lang.Double)}.
	 */
	@Test
	public void testDividend() {
        StockDetails stockALE = new StockDetails("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
        StockDetails stockGIN = new StockDetails("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0);
        // Test dividend for Common
		BigDecimal dividendALE = stockALE.dividend(1.0);
		BigDecimal expectedDividendALE = new BigDecimal (stockALE.getLastDividend()/1.0);
		assertEquals(expectedDividendALE.doubleValue(), dividendALE.doubleValue(), 0.0);
		// Test dividend for Preferred
		BigDecimal dividendGIN = stockGIN.dividend(1.0);
		BigDecimal expectedDividendGIN = new BigDecimal (stockGIN.getFixedDividend()*stockGIN.getParValue()/1.0);
		assertEquals(expectedDividendGIN.doubleValue(), dividendGIN.doubleValue(), 0.0);
	}
	
	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.StockDetails#dividend(java.lang.Double)}.
	 */
	@Test
	public void testDividendNegative() {
        StockDetails stockALE = new StockDetails("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
        StockDetails stockGIN = new StockDetails("GIN", StockType.PREFERRED, 0.0, 0.2, 100.0);
        // Test dividend for Common
		BigDecimal dividendALE = stockALE.dividend(0.0);
		BigDecimal expectedDividendALE = BigDecimal.ZERO;
		assertEquals(expectedDividendALE.doubleValue(), dividendALE.doubleValue(), 0.0);
		// Test dividend for Preferred
		BigDecimal dividendGIN = stockGIN.dividend(-1.0);
		BigDecimal expectedDividendGIN = BigDecimal.ZERO;
		assertEquals(expectedDividendGIN.doubleValue(), dividendGIN.doubleValue(), 0.0);
	}

	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.StockDetails#PERatio(java.lang.Double)}.
	 */
	@Test
	public void testPERatio() {
        StockDetails stockALE = new StockDetails("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
        BigDecimal peRatioALE = stockALE.PERatio(1.0);
        BigDecimal expectedPeRatioALE = new BigDecimal (1.0).divide(new BigDecimal(stockALE.getLastDividend()), 5,RoundingMode.HALF_UP);
        assertEquals(expectedPeRatioALE.doubleValue(), peRatioALE.doubleValue(), 0.0);
	}
	
	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.StockDetails#PERatio(java.lang.Double)}.
	 */
	@Test
	public void testPERatioNegative() {
        StockDetails stockALE = new StockDetails("ALE", StockType.COMMON, 0.0, 0.0, 60.0);
        BigDecimal peRatioALE = stockALE.PERatio(new Double(1));
        BigDecimal expectedPeRatioALE = BigDecimal.ZERO;
        assertEquals(expectedPeRatioALE.doubleValue(), peRatioALE.doubleValue(), 0);
	}

	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.StockDetails#buy(java.lang.Integer, java.lang.Double, java.util.Date)}.
	 */
	@Test
	public void testBuy()throws InvalidQuantityException  {
		StockDetails stockALE = new StockDetails("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		stockALE.buy(1, 10.0, new Date());
		assertEquals(10.0, stockALE.getLastTradePrice(), 0.0);
	}

	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.StockDetails#buy(java.lang.Integer, java.lang.Double, java.util.Date)}.
	 */
	@Test (expected= InvalidQuantityException.class)
	public void testBuyNegative1()throws InvalidQuantityException  {
		StockDetails stockALE = new StockDetails("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		stockALE.buy(0, 10.0, new Date());
	}
	
	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.StockDetails#buy(java.lang.Integer, java.lang.Double, java.util.Date)}.
	 */
	@Test (expected= InvalidQuantityException.class)
	public void testBuyNegative2()throws InvalidQuantityException  {
		StockDetails stockALE = new StockDetails("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		stockALE.buy(-1, 10.0, new Date());
	}
	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.StockDetails#sell(java.lang.Integer, java.lang.Double, java.util.Date)}.
	 */
	@Test
	public void testSell()throws InvalidQuantityException {
		StockDetails stockALE = new StockDetails("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		stockALE.sell(1, 10.0, new Date());
		assertEquals(10.0, stockALE.getLastTradePrice(), 0.0);
	}

	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.StockDetails#sell(java.lang.Integer, java.lang.Double, java.util.Date)}.
	 */
	@Test (expected = InvalidQuantityException.class)
	public void testSellNegative1()throws InvalidQuantityException {
		StockDetails stockALE = new StockDetails("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		stockALE.sell(0, 10.0, new Date());
	}

	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.StockDetails#sell(java.lang.Integer, java.lang.Double, java.util.Date)}.
	 */
	@Test (expected = InvalidQuantityException.class)
	public void testSellNegative2()throws InvalidQuantityException {
		StockDetails stockALE = new StockDetails("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		stockALE.sell(-3, 10.0, new Date());
	}

	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.StockDetails#getLastTradePrice()}.
	 */
	@Test
	public void testGetLastTradePrice() throws InvalidQuantityException {
		StockDetails stockALE = new StockDetails("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		stockALE.sell(1, 10.0, new Date());
		stockALE.buy(1, 11.0,new Date());
		assertEquals(11.0, stockALE.getLastTradePrice(), 0.0);
	}
	
	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.StockDetails#getLastTradePrice()}.
	 */
	@Test 
	public void testGetLastTradePriceNegative() throws InvalidQuantityException {
		StockDetails stockALE = new StockDetails("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		assertEquals(0.0, stockALE.getLastTradePrice(), 0.0);
	}

	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.StockDetails#calculateVolumeWeightedStockPrice()}.
	 */
	@Test
	public void testCalculateVolumeWeightedStockPrice() throws InvalidQuantityException {
		StockDetails stockALE = new StockDetails("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		stockALE.sell(2, 10.0, new Date() );
		stockALE.buy(2, 10.0, new Date());		
		BigDecimal volumeWeightedStockPrice = stockALE.calculateVolumeWeightedStockPrice();
		assertEquals(10.0, volumeWeightedStockPrice.doubleValue(), 0.0);
		Date now = new Date();
		// Date 20 minutes ago
		Date startTime = new Date(now.getTime() - (20 * 60 * 1000));
		stockALE.getTrades().put(startTime, new Trade(TradeType.BUY, 1, 20.0));
		// The new (outdated) trade should not affect calculation of the Volume Weighted Stock Price
		volumeWeightedStockPrice = stockALE.calculateVolumeWeightedStockPrice();
		assertEquals(10.0, volumeWeightedStockPrice.doubleValue(), 0.0);
	}
	
	/**
	 * Test method for {@link com.csg.sandbox.assignments.business.StockDetails#calculateVolumeWeightedStockPrice()}.
	 */
	@Test
	public void testCalculateVolumeWeightedStockPriceNegative() throws InvalidQuantityException {
		StockDetails stockALE = new StockDetails("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		stockALE.sell(2, 10.0, new Date() );
		stockALE.buy(2, 10.0, new Date());		
		// Date 20 minutes ago
		Date startTime = subMinDate(20 * 60 * 1000);
		stockALE.getTrades().put(startTime, new Trade(TradeType.BUY, 1, 20.0));
		stockALE.getTrades().put(startTime, new Trade(TradeType.SELL, 5, 10.0));
		
		// The new (outdated) trade should not affect calculation of the Volume Weighted Stock Price
		BigDecimal volumeWeightedStockPrice = stockALE.calculateVolumeWeightedStockPrice();
		assertEquals(10.0, volumeWeightedStockPrice.doubleValue(), 0.0);
	}
	
	/**
	 * 
	 * @param mins mins in milliseconds to subtract
	 * @return Date after added mins.
	 */
	private static Date subMinDate( long mins ){
		Calendar date = Calendar.getInstance();
		long t= date.getTimeInMillis();
		Date dateAfterAddingMins=new Date(t - (mins));
		return dateAfterAddingMins;
	}
}
