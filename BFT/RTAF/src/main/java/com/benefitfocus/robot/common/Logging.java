package com.benefitfocus.robot.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.utils.CustomException;

//import com.java.Robot.exceptions.RobotLibraryNonFatalException;

@RobotKeywords
public class Logging {

	// ******************************
	// Keywords
	// ******************************

	// ******************************
	// Internal Methods
	// ******************************

	protected final static Map<String, String[]> VALID_LOG_LEVELS;
	protected static String logDir = null;

	static {
		VALID_LOG_LEVELS = new HashMap<String, String[]>();
		VALID_LOG_LEVELS.put("debug", new String[] { "debug", "" });
		VALID_LOG_LEVELS.put("html", new String[] { "info", ", True, False" });
		VALID_LOG_LEVELS.put("info", new String[] { "info", "" });
		VALID_LOG_LEVELS.put("trace", new String[] { "trace", "" });
		VALID_LOG_LEVELS.put("warn", new String[] { "warn", "" });
		VALID_LOG_LEVELS.put("error", new String[] { "warn", "" });
	}

	protected void trace(String msg) {
		log(msg, "trace");
	}

	protected void debug(String msg) {
		log(msg, "debug");
	}

	public void info(String msg) {
		log(msg, "info");
	}

	public void html(String msg) {
		log(msg, "html");
	}

	public void warn(String msg) {
		log(msg, "warn");
	}
	public void error(String msg) {
		log(msg, "error");
	}
	
	protected void log(String msg, String logLevel) {
		String[] methodParameters = VALID_LOG_LEVELS
				.get(logLevel.toLowerCase());
		if (methodParameters != null) {
			log0(msg, methodParameters[0], methodParameters[1]);
		} else {
			throw new CustomException("Given log level is invalid.");
		}
	}

	/**
	 * Log the given message with the Robot logger.<br>
	 * <br>
	 * There is a hard limit of 100k in the Jython source code parser. Therefore
	 * messages larger than 1k are saved on disk and the later read back into
	 * memory on the Jython side.
	 */
	protected void log0(String msg, String methodName, String methodArguments) {
		if (msg.length() > 1024) {
			// Message is too large.
			// There is a hard limit of 100k in the Jython source code parser
			try {
				// Write message to temp file
				File tempFile = File.createTempFile("RTAFLibrary-", ".log");
				tempFile.deleteOnExit();
				FileWriter writer = new FileWriter(tempFile);
				writer.write(msg);
				writer.close();

				// Read the message in Python back and log it.
				getLoggingPythonInterpreter().get().exec(
						String.format("from __future__ import with_statement\n"
								+ "\n" + "with open('%s', 'r') as msg_file:\n"
								+ "    msg = msg_file.read()\n"
								+ "    logger.%s(msg%s)", tempFile
								.getAbsolutePath().replace("\\", "\\\\"),
								methodName, methodArguments));

			} catch (IOException e) {
				// throw new
				// RobotLibraryNonFatalException("Error in handling temp file for long log message.",
				// e);
			}
		} else {
			// Message is small enough to get parsed by Jython
			getLoggingPythonInterpreter().get().exec(
					String.format("logger.%s('%s'%s)", methodName,
							msg.replace("'", "\\'").replace("\n", "\\n"),
							methodArguments));
		}
	}

	public File getLogDir() {
		if (logDir == null) {
			PyString logDirName = (PyString) getLoggingPythonInterpreter()
                    .get().eval("BuiltIn().get_variable_value('${LOG FILE}')");
			System.out.println("logDirName : "+logDirName);
			
			if (logDirName != null
					&& !(logDirName.asString().toUpperCase().equals("NONE"))) {
				return new File(logDirName.asString()).getParentFile();
			}
			logDirName = (PyString) getLoggingPythonInterpreter().get().eval(
					"BuiltIn().get_variable_value('${OUTPUTDIR}')");
			
			return new File(logDirName.asString()).getParentFile();
		} else {
			return new File(logDir);
		}
	}

	public void createFolder(String folder) {
		File appDir = new File(folder);
		if (!appDir.exists()) {
			appDir.mkdir();
		}
	}

	public ThreadLocal<PythonInterpreter> getLoggingPythonInterpreter() {
		return loggingPythonInterpreter;
	}

	public void setLoggingPythonInterpreter(
			ThreadLocal<PythonInterpreter> loggingPythonInterpreter) {
		Logging.loggingPythonInterpreter = loggingPythonInterpreter;
	}

	/**
	 * Thread local variable with loaded logger.
	 */
	private static ThreadLocal<PythonInterpreter> loggingPythonInterpreter = new ThreadLocal<PythonInterpreter>() {
		@Override
		protected PythonInterpreter initialValue() {
			PythonInterpreter pythonInterpreter = new PythonInterpreter();
			pythonInterpreter
					.exec("from robot.libraries.BuiltIn import BuiltIn; from robot.api import logger;");
			return pythonInterpreter;
		}
	};

}