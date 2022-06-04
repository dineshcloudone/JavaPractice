import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.robotframework.javalib.library.AnnotationLibrary;

import com.benefitfocus.robot.utils.Javadoc2Libdoc;

/**
 * Benefitfocus Robot Test Automation Framework (RTAF v1.0).<br>
 * <br>
 * <b>Introduction here..</b>
 */
public class Enrollment extends AnnotationLibrary {
		
	/**
	 * The list of keyword patterns for the AnnotationLibrary
	 */
	public static final String KEYWORD_PATTERN = "com.benefitfocus.robot".replace('.','/') + "/**/*.class";

	/**
	 * The javadoc to libdoc converter
	 */
	public static final Javadoc2Libdoc JAVADOC_2_LIBDOC = new Javadoc2Libdoc(
			Enrollment.class);

	/**
	 * The library documentation is written in HTML
	 */
	public static final String ROBOT_LIBRARY_DOC_FORMAT = "HTML";

	/**
	 * The scope of this library is global.
	 */
	public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

	/**
	 * The actual version of this library. Loaded from maven project.
	 */
	public static final String ROBOT_LIBRARY_VERSION = "1.0";

	// Hashmap for runtime variables
	public static Map<String, String> hMap = new HashMap<String, String>();

	@SuppressWarnings("unused")
	private static String loadRobotLibraryVersion() {
		try {
			return ResourceBundle.getBundle(
					Enrollment.class.getCanonicalName().replace(".",
							File.separator)).getString("version");
		} catch (RuntimeException e) {
			return "unknown";
		}
	}

	/**
	 * FooBarLibrary can be imported with optional arguments.<br>
	 * <br>
	 * <b>Insert importing documentation here.</b>
	 */
	public Enrollment() {		
		super();
		addKeywordPattern(KEYWORD_PATTERN);
		createKeywordFactory(); // => init annotations
	}
	

	// ******************************
	// AnnotationLibrary overrides
	// ******************************
	@Override
	public Object runKeyword(String keywordName, Object[] args) {
		return super.runKeyword(keywordName, toStrings(args));
	}

	@Override
	public String getKeywordDocumentation(String keywordName) {
		String keywordDocumentation = JAVADOC_2_LIBDOC
				.getKeywordDocumentation(keywordName);
		if (keywordDocumentation == null) {
			try {
				return super.getKeywordDocumentation(keywordName);
			} catch (NullPointerException e) {
				return "";
			}
		}
		return keywordDocumentation;
	}

	// ******************************
	// Internal Methods
	// ******************************

	/**
	 * Convert all arguments in the object array to string
	 */
	protected Object[] toStrings(Object[] args) {
		Object[] newArgs = new Object[args.length];
		for (int i = 0; i < newArgs.length; i++) {
			if (args[i].getClass().isArray()) {
				newArgs[i] = args[i];
			} else {
				newArgs[i] = args[i].toString();
			}
		}
		return newArgs;
	}
}
