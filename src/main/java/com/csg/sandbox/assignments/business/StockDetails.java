/**
 * This is copyright for assignment from jpmc
 */
package com.csg.sandbox.assignments.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.csg.sandbox.assignments.enums.StockType;
import com.csg.sandbox.assignments.enums.TradeType;
import com.csg.sandbox.assignments.exception.InvalidQuantityException;

/**
 * Manage all required stock operations
 * For a given stock calculates, 
    * Given any price as input, calculate the dividend yield
    * Given any price as input, calculate the P/E Ratio
    * Record a trade, with time-stamp, quantity of shares, buy or sell indicator and 
    * Calculate Volume Weighted Stock Price based on trades in past 15 minutes
 * 
 * @author Saurabh
 */
public class StockDetails {

	private String stockSymbol;
	private StockType type;
	private Double lastDividend;
	private Double fixedDividend;
	private Double parValue;
	private TreeMap<Date, Trade> trades;
	private final Double ZERO = 0.0;
	private final Integer PRECISION = 5;

	/**
	 * @return the stockSymbol
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * @param stockSymbol
	 *            the stockSymbol to set
	 */
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public StockType getType() {
		return type;
	}

	public void setType(StockType type) {
		this.type = type;
	}

	public Double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public Double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public Double getParValue() {
		return parValue;
	}

	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	public TreeMap<Date, Trade> getTrades() {
		return trades;
	}

	public void setTrades(TreeMap<Date, Trade> trades) {
		this.trades = trades;
	}

	/**
	 * 
	 * @param stockSymbol
	 * @param type
	 * @param lastDividend
	 * @param fixedDividend
	 * @param parValue
	 */
	public StockDetails(String stockSymbol, StockType type, Double lastDividend, Double fixedDividend, Double parValue) {
		this.setStockSymbol(stockSymbol);
		this.setType(type);
		this.setLastDividend(lastDividend);
		this.setFixedDividend(fixedDividend);
		this.setParValue(parValue);
		this.trades = new TreeMap<Date, Trade>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stock [");
		if (stockSymbol != null) {
			builder.append("stockSymbol=");
			builder.append(stockSymbol);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (lastDividend != null) {
			builder.append("lastDividend=");
			builder.append(lastDividend);
			builder.append(", ");
		}
		if (fixedDividend != null) {
			builder.append("fixedDividend=");
			builder.append(fixedDividend);
			builder.append(", ");
		}
		if (parValue != null) {
			builder.append("parValue=");
			builder.append(parValue);
			builder.append(", ");
		}
		if (trades != null) {
			builder.append("trades=");
			builder.append(trades);
		}
		builder.append("]");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fixedDividend == null) ? 0 : fixedDividend.hashCode());
		result = prime * result + ((lastDividend == null) ? 0 : lastDividend.hashCode());
		result = prime * result + ((parValue == null) ? 0 : parValue.hashCode());
		result = prime * result + ((stockSymbol == null) ? 0 : stockSymbol.hashCode());
		result = prime * result + ((trades == null) ? 0 : trades.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockDetails other = (StockDetails) obj;
		if (fixedDividend == null) {
			if (other.fixedDividend != null)
				return false;
		} else if (!fixedDividend.equals(other.fixedDividend))
			return false;
		if (lastDividend == null) {
			if (other.lastDividend != null)
				return false;
		} else if (!lastDividend.equals(other.lastDividend))
			return false;
		if (parValue == null) {
			if (other.parValue != null)
				return false;
		} else if (!parValue.equals(other.parValue))
			return false;
		if (stockSymbol == null) {
			if (other.stockSymbol != null)
				return false;
		} else if (!stockSymbol.equals(other.stockSymbol))
			return false;
		if (trades == null) {
			if (other.trades != null)
				return false;
		} else if (!trades.equals(other.trades))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	/**
	 * Calculate the dividend based on the specified price .We assumed if price is zero 
	 * then dividend should be Zero
	 * 
	 * @param price
	 *            The price to use as a base to calculate the dividend
	 * @return The dividend
	 */
	public BigDecimal dividend(Double price) {
		if(Math.signum(price)== 0 || Math.signum(price)== -1)
			return BigDecimal.ZERO;
		switch (this.getType()) {
		case COMMON:
			return new BigDecimal(this.getLastDividend()).divide(new BigDecimal (price),PRECISION, RoundingMode.HALF_UP);
		case PREFERRED:
			return new BigDecimal(this.getFixedDividend() * this.getParValue()).divide(new BigDecimal(price),PRECISION, RoundingMode.HALF_UP) ;
		default:
			return BigDecimal.ZERO;
		}
	}

	/**
	 * Calculate P/E Ratio based on the specified price.
	 * We assumed if E is < 0 the P/E should be zero 
	 * @param price
	 *            The price to use as a base to calculate the P/E ratio
	 * @return The P/E Ratio
	 */
	public BigDecimal PERatio(Double price) {
		
		if( this.getLastDividend().equals(this.ZERO))
			return BigDecimal.ZERO;
		else
			return (new BigDecimal (price).divide(new BigDecimal (this.getLastDividend()),PRECISION, RoundingMode.HALF_UP)) ;
	}

	/**
	 * Buy stock, specifying quantity and price
	 * 
	 * @param quantity
	 *            The quantity of stock to BUY
	 * @param price
	 *            The price of the stock
	 */
	public void buy(Integer quantity, Double price,Date bookingDate) throws InvalidQuantityException{
		if(Math.signum(quantity)== 0 || Math.signum(quantity)== -1){
			throw new InvalidQuantityException("Quantity can not be entered as Zero");
		}
		Trade trade = new Trade(TradeType.BUY, quantity, price);
		this.trades.put(bookingDate, trade);
	}

	/**
	 * Sell stock, specifying quantity and price
	 * 
	 * @param quantity
	 *            TYhe quantity of stock to SELL
	 * @param price
	 *            The price of the stock
	 */
	public void sell(Integer quantity, Double price,Date bookingDate) throws InvalidQuantityException {
		if(Math.signum(quantity)== 0 || Math.signum(quantity)== -1){
			throw new InvalidQuantityException("Quantity can not be entered as Zero");
		}
		Trade trade = new Trade(TradeType.SELL, quantity, price);
		this.trades.put(bookingDate, trade);
	}

	/**
	 * Return the current price of the stock based on the last trade price
	 * 
	 * @return The last trade price
	 */
	public Double getLastTradePrice() {
		if (this.trades.size() > 0) {
			// Uses the last trade price as price
			return this.trades.lastEntry().getValue().getPrice();
		} else {
			// No trades = price 0? :)
			return this.ZERO;
		}
	}

	/**
	 * Calculate the Volume Weighted Stock Price
	 * 
	 * @return The Volume Weighted Stock Price
	 */
	public BigDecimal calculateVolumeWeightedStockPrice() {
		Date current = new Date();
		// Date 15 minutes ago
		Date startTime = new Date(current.getTime() - (15 * 60 * 1000));
		// Get trades for the last 15 minutes
		SortedMap<Date, Trade> trades = this.trades.tailMap(startTime);
		// Calculate
		Double volumeWeigthedStockPrice = 0.0;
		Integer totalQuantity = 0;
		for (Trade trade : trades.values()) {
			totalQuantity += trade.getQuantity();
			volumeWeigthedStockPrice += trade.getPrice() * trade.getQuantity();
		}
		return new BigDecimal (volumeWeigthedStockPrice).divide( new BigDecimal( totalQuantity),PRECISION, RoundingMode.HALF_UP);
	}
}
