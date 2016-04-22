/**
 * This is copyright for assignment from jpmc
 */
package com.csg.sandbox.assignments.exception;

/**
 * @author Saurabh
 *
 */
public class InvalidQuantityException extends Exception {


	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InvalidQuantityException() {
	}

	/**
	 * @param arg0
	 */
	public InvalidQuantityException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public InvalidQuantityException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public InvalidQuantityException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public InvalidQuantityException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
