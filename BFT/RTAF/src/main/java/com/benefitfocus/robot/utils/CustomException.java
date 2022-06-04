package com.benefitfocus.robot.utils;

import org.openqa.selenium.By;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException {

	private By locator = null;
	
	/**
	 * CustomException class constructor with no arguments
	 */
	public CustomException() {
	}

	/**
	 * CustomException class constructor
	 * 
	 * @param message
	 *            - String argument to show the custom message
	 */
	public CustomException(String message) {
		super(message);
	}

	/**
	 * CustomException class constructor
	 * 
	 * @param cause
	 *            - Throwable cause for custom exception
	 */
	public CustomException(Throwable cause) {
		super(cause);
	}

	/**
	 * CustomException class constructor
	 * 
	 * @param message
	 *            - String argument to show the custom message
	 * @param locator
	 *            - Selenium locator by class to show along with custom message
	 */
	public CustomException(String message, By locator) {
		super(message);
		this.locator = locator;
	}

	/**
	 * CustomException class constructor
	 * 
	 * @param message
	 *            - String argument to show the custom message
	 * @param cause
	 *            - Throwable cause for custom exception
	 */
	public CustomException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * CustomException class constructor
	 * 
	 * @param message
	 *            - String argument to show the custom message
	 * @param cause
	 *            - Throwable cause for custom exception
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CustomException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Overridden toString method
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * Overridden getMessage method
	 */
	@Override
	public String getMessage() {
		if (locator != null) {
			return super.getMessage() + " - " + locator.toString();
		}
		return super.getMessage();
	}
}