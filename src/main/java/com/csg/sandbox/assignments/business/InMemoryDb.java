/**
 * This is copyright for assignment from jpmc
 */
package com.csg.sandbox.assignments.business;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.csg.sandbox.assignments.enums.StockType;

/**
 * This class is responsible to load Sample data from global beverage exchange.
 * 
 * @author Saurabh
 *
 */
@Configuration
@ComponentScan
public class InMemoryDb {

	/**
	 * 
	 * @return unmodifiableMap with in memory data of global beverage Corporation exchange
	 */
	@Bean (name="ExchangeDb")
	Map<String, StockDetails> InMemDb() {
		HashMap<String, StockDetails> dbMap = new HashMap<String, StockDetails>();
		dbMap.put("TEA", new StockDetails("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
		dbMap.put("POP", new StockDetails("POP", StockType.COMMON, 8.0, 0.0, 100.0));
		dbMap.put("ALE", new StockDetails("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
		dbMap.put("GIN", new StockDetails("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
		dbMap.put("JOE", new StockDetails("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
		Map <String, StockDetails> unmodifiableMap = Collections.unmodifiableMap(dbMap);
		return unmodifiableMap;
	}

}
