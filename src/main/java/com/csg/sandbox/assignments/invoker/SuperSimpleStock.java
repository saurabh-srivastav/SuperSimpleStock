/**
 * This is copyright for assignment from jpmc
 */
package com.csg.sandbox.assignments.invoker;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.csg.sandbox.assignments.business.InMemoryDb;
import com.csg.sandbox.assignments.business.StockDetails;
import com.csg.sandbox.assignments.calculator.GBCECalculator;

/**
 * For a given stock, 
    * Given any price as input, calculate the dividend yield
    * Given any price as input, calculate the P/E Ratio
    * Record a trade, with time-stamp, quantity of shares, buy or sell indicator and 
    * Calculate Volume Weighted Stock Price based on trades in past 15 minutes
  * Calculate the GBCE All Share Index using the geometric mean of prices for all stocks traded price
 * @author Saurabh
 *
 */

public class SuperSimpleStock {

	/**
	 * Intantiate logger
	 */
	private static Log logger = LogFactory.getLog(SuperSimpleStock.class);

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			ApplicationContext context = new AnnotationConfigApplicationContext(InMemoryDb.class);
			@SuppressWarnings("unchecked")
			final Map<String, StockDetails> dbMap = context.getBean("ExchangeDb", Map.class);
			
			for (StockDetails stock : dbMap.values()) {
				final Double tickerPrice = 8.7;
				//Total no of trade for eligible for 15 mins calculation
				final int noOfEligibleTrade= 5;
				Date bookingDate;
				// print devidend for tickerPrice
				logger.debug(stock.getStockSymbol() + String.format(" dividend:::: %s", stock.dividend(tickerPrice)));
				// print P/E ratio for tickerPrice
				logger.debug(stock.getStockSymbol() + String.format(" P/E Ratio:::: %s", stock.PERatio(tickerPrice)));
				// Record some trades
				logger.debug(" Recording 10 Trades :::: ");
				for (int index = 1; index <= 10; index++) {
					//BUY
					double randomValue = getRandomNumber();
					
					if(index<= noOfEligibleTrade){
						bookingDate = new Date();
					}else{
						 final long SIXTY_MINUTE_IN_MILLIS = 60*60000;//millisecs
						 bookingDate = addMinDate(SIXTY_MINUTE_IN_MILLIS);
					}
					stock.buy(index, randomValue, bookingDate);
					logger.debug(stock.getStockSymbol() + String.format(" bought %s shares at $ %s", index, randomValue));

					//SELL
					randomValue = getRandomNumber();
					stock.sell(index, randomValue , bookingDate );
					logger.debug(stock.getStockSymbol() + String.format(" sold %s shares at $ %s", index, randomValue));
					Thread.sleep(1000);
				}
				// Printing Volume Weighted Stock Price based on trades in past 15 minutes
				logger.debug(stock.getStockSymbol() + String.format(" price: $%s", stock.getLastTradePrice()));
				logger.debug(String.format(" volumeWeightedStockPrice:: $%s is for stock symbol ::%s", stock.calculateVolumeWeightedStockPrice(),stock.getStockSymbol()));
			}
			Double GBCEallShareIndex = GBCECalculator.allShareIndex(dbMap);
			// Printing GBCE All Share Index
			logger.debug(String.format("GBCE All Share Index::: %s ", GBCEallShareIndex));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Generating random number in range
	 * @return
	 */
	private static double getRandomNumber() {
		Random r = new Random();
		Integer rangeMin = 1;
		Integer rangeMax = 10;
		return (rangeMin + (rangeMax - rangeMin) * r.nextDouble());
	}
	
	/**
	 * 
	 * @param mins mins in milliseconds to add
	 * @return Date after added mins.
	 */
	private static Date addMinDate( long mins ){
		Calendar date = Calendar.getInstance();
		long t= date.getTimeInMillis();
		Date dateAfterAddingMins=new Date(t + (mins));
		return dateAfterAddingMins;
	}
}
