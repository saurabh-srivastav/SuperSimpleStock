#---- Assignment SuperSimpleStock --------------------------

#Requirements

* Provide working source code that will:-
  * For a given stock, 
    * Given any price as input, calculate the dividend yield
    * Given any price as input, calculate the P/E Ratio
    * Record a trade, with timestamp, quantity of shares, buy or sell indicator and 
    * Calculate Volume Weighted Stock Price based on trades in past 15 minutes
  * Calculate the GBCE All Share Index using the geometric mean of prices for all stocks traded price

#Constraints & Notes

* Written in one of these languages: Java, C#, C++, Python
* No database or GUI is required, all data need only be held in memory
* No prior knowledge of stock markets or trading is required – all formulas are provided below.

#How to use:
This is a maven project:
* mvn package -- to generate the executable jar (SuperSimpleStock-0.0.1-SNAPSHOT.jar).

To run the program just run inside target folder of source folder  project from command prompt:
* java -jar SuperSimpleStock-0.0.1-SNAPSHOT.jar

#Classes
* com.csg.sandbox.assignments.invoker.SuperSimpleStock -- Invoker of App  using the main classes StockDetails and GBCECalculator to calculate expected details of stocks and trade.
* com.csg.sandbox.assignments.calculator -- Class used to calculate the All Share Index
* com.csg.sandbox.assignments.business.StockDetails -- Class used to manage the operations against the stocks
* com.csg.sandbox.assignments.business.Trade -- Just a pojo representing each trade
*com.csg.sandbox.assignments.business.InMemoryDb -- This class is responsible to load Sample data from global beverage exchange.
* com.csg.sandbox.assignments.exception.InvalidQuantityException -- Manage invalid quantity values of trade.

#Packages
* com.csg.sandbox.assignments -- The main project package
* com.csg.sandbox.assignments.enums -- Package containing some enums needed 
* com.csg.sandbox.assignment.testsuite -- Test suit to execute all test cases
