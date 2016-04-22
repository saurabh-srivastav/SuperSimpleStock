/**
 * This is copyright for assignment from jpmc
 */
package com.csg.sandbox.assignments.calculator;

import java.util.Map;

import com.csg.sandbox.assignments.business.StockDetails;

/**
 * This class is responsible to calculate  the GBCE All Share Index using the 
 * geometric mean of prices for all stocks traded price
 * @author Saurabh
 *
 */
public class GBCECalculator {

	/**
	 * Calculate the GBCE All Share Index for all stocks
	 * 
	 * @param stocks The Stock DB
	 * @return The GBCE All Share Index
	 */
	public static Double allShareIndex(Map<String, StockDetails> stocks) {
		Double allShareIndex = 1.0;
		for(StockDetails stock: stocks.values()) {
			allShareIndex*=stock.getLastTradePrice();
		}
		
		return Math.pow(allShareIndex, 1.0 / stocks.size());
	}
	
}
