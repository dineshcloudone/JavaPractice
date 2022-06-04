package com.benefitfocus.robot.exceptions;

/**
 * A raised exception of this type marks the step as failed, but does not end
 * all test executions.
 */
@SuppressWarnings("serial")
public class RTAFLibraryNonFatalException extends RuntimeException {

	/**
	 * Mark this exception as non fatal
	 */
	public static final boolean ROBOT_EXIT_ON_FAILURE = false;

	public RTAFLibraryNonFatalException() {
		super();
	}

	public RTAFLibraryNonFatalException(String string) {
		super(string);
	}

	public RTAFLibraryNonFatalException(Throwable t) {
		super(t);
	}

	public RTAFLibraryNonFatalException(String string, Throwable t) {
		super(string, t);
	}
}
