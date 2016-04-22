/**
 * This is copyright for assignment from jpmc
 */
package com.csg.sandbox.assignments.business;

import com.csg.sandbox.assignments.enums.TradeType;
/**
 * @author Saurabh
 *
 */
public class Trade {

	private TradeType type;
	private Integer quantity;
	private Double price;
	private StockDetails stockDetails;

	public TradeType getType() {
		return type;
	}

	public void setType(TradeType type) {
		this.type = type;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Trade(TradeType type, Integer quantity, Double price) {
		this.setType(type);
		this.setQuantity(quantity);
		this.setPrice(price);
	}
	
	/**
	 * @return the stockDetails
	 */
	public StockDetails getStockDetails() {
		return stockDetails;
	}

	/**
	 * @param stockDetails the stockDetails to set
	 */
	public void setStockDetails(StockDetails stockDetails) {
		this.stockDetails = stockDetails;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Trade [");
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (quantity != null) {
			builder.append("quantity=");
			builder.append(quantity);
			builder.append(", ");
		}
		if (price != null) {
			builder.append("price=");
			builder.append(price);
			builder.append(", ");
		}
		if (stockDetails != null) {
			builder.append("stockDetails=");
			builder.append(stockDetails);
		}
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((stockDetails == null) ? 0 : stockDetails.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Trade other = (Trade) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (stockDetails == null) {
			if (other.stockDetails != null)
				return false;
		} else if (!stockDetails.equals(other.stockDetails))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
