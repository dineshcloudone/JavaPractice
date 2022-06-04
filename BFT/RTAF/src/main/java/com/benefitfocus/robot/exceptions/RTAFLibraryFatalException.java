package com.benefitfocus.robot.exceptions;

/**
 * A raised exception of this type ends all test executions.
 */
@SuppressWarnings("serial")
public class RTAFLibraryFatalException extends RuntimeException {

	/**
	 * Mark this exception as fatal
	 */
	public static final boolean ROBOT_EXIT_ON_FAILURE = true;

	public RTAFLibraryFatalException() {
		super();
	}

	public RTAFLibraryFatalException(String string) {
		super(string);
	}

	public RTAFLibraryFatalException(Throwable t) {
		super(t);
	}

	public RTAFLibraryFatalException(String string, Throwable t) {
		super(string, t);
	}
}
