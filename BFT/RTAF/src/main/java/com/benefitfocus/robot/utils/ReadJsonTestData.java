package com.benefitfocus.robot.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.python.core.PyString;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;

@RobotKeywords
public class ReadJsonTestData {

    @Autowired
    protected static Logging logger;

    @Autowired
    protected ManageBrowser browser;

    public PyString suiteTestData;

    // Global variables
    public static JSONObject objJsonObject = null;
    public static JSONObject jsonTestDataKeys = null;
    public static JSONObject testKey = null;
    public static JSONParser jsonParser1 = new JSONParser();
    public static boolean jsonObjectFound = false;
    public static String testdatafiles[] = {};

    // Hashmap to store the json content of each datafile
    public static Map<String, JSONObject> Datafiles = new HashMap<String, JSONObject>();
    public static JSONObject fields = null;

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Keyword or method 'ReadJSON' used to read the json test data files for
     * the current execution and application
     */
    @RobotKeyword
    public void readJSON() throws NullPointerException {

        String application = browser.configurations[0]; //
        // "eEnrollment";

        System.out.println("Reading the testdata json files for application : "
                + application);

        String location = System.getProperty("user.dir");
        System.out.println(" Test data location : " + location);
        /*String location = System.getProperty("user.dir").replace(
                "\\robotframework", "")
                + "\\TestInputs\\";*/
        System.out.println(" location : " + location);
        if (location.contains("robotframework")) {
            location = System.getProperty("user.dir").replace(
                    "\\robotframework", "").replace("/robotframework", "") + System.getProperty("file.separator") + "TestInputs" + System.getProperty("file.separator");
        } else if (location.contains("TeamCity")) {
            location = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "test" + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator") + "TestInputs" + System.getProperty("file.separator");
        } else if (location.contains("jenkins")) {
            location = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "test" + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator") + "TestInputs" + System.getProperty("file.separator");
        } else {
            location = System.getProperty("user.dir") + System.getProperty("file.separator") + application + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "test" + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator") + "TestInputs" + System.getProperty("file.separator");
        }

        System.out.println(" Test data location : " + location);
        //location = "Q:\\Automation\\Selenium\\Development\\Phani\\Workspace\\ROBOT\\EEnrollment\\src\\test\\resources\\TestInputs\\";

        if (location == null) {
            // error - missing folder
        } else {
            try {
                // File folder = new File(location.toURI());
                File folder = new File(location);
                File[] listOfFiles = folder.listFiles();

                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        try {
                            if (file.getName().endsWith(".json")) {
                                System.out.println("Reading the json file : "
                                        + file.getName());
                                objJsonObject = (JSONObject) jsonParser1
                                        .parse(new BufferedReader(
                                                new InputStreamReader(
                                                        new FileInputStream(
                                                                location
                                                                        + file.getName()))));
                                Datafiles.put(
                                        file.getName()
                                                .substring(
                                                        0,
                                                        file.getName().indexOf(
                                                                "."))
                                                .toLowerCase(), objJsonObject);
                            }

                        } catch (ParseException e) {
                            throw new CustomException("Error reading the "
                                    + file.getName() + " testdata json file."
                                    + e.getMessage());
                        }
                    } else if (file.isDirectory()) {
                        readFiles(file.getAbsolutePath());
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    private void readFiles(String location) throws IOException {
        File folder = new File(location);
        File[] listOfFiles = folder.listFiles();
        System.out.println("location : " + location);
        for (File file : listOfFiles) {
            if (file.isFile()) {
                try {
                    if (file.getName().endsWith(".json")) {
                        System.out.println("Reading the json file : "
                                + file.getName());
                        objJsonObject = (JSONObject) jsonParser1
                                .parse(new BufferedReader(
                                        new InputStreamReader(
                                                new FileInputStream(
                                                        location + System.getProperty("file.separator")
                                                                + file.getName()))));
                        Datafiles.put(
                                file.getName()
                                        .substring(
                                                0,
                                                file.getName().indexOf(
                                                        "."))
                                        .toLowerCase(), objJsonObject);
                    }

                } catch (ParseException e) {
                    throw new CustomException("Error reading the "
                            + file.getName() + " testdata json file."
                            + e.getMessage());
                }
            } else if (file.isDirectory()) {
                readFiles(file.getAbsolutePath());
            }
        }
    }

    /**
     * Author: Phani Srikar Ch
     * Keyword or method 'getTestData' used to get the specific json object
     * values
     * <p/>
     * <pre>
     * <b>Parameters :</b>
     * | strJsonKey - json tag in json testdata file |
     * <b>Example :</b>
     * | like --> validvistacredentials / mandatory / automationgroup1 |
     * </pre>
     */
    @RobotKeyword
    @ArgumentNames({"strJsonKey"})
    public static JSONObject getTestData(String strJsonKey) {

        try {
            objJsonObject = null;
            jsonTestDataKeys = null;
            testKey = null;

            if (strJsonKey.startsWith("td:"))
                strJsonKey = strJsonKey.substring(3);

            // Boolean to know the required key found or not
            jsonObjectFound = false;
            // Array list holding the json files key set
            ArrayList<String> keys = new ArrayList<String>(Datafiles.keySet());

            String TestSuiteJsonFileName =
                    JavaListener.hRobotTestDetails.get("currentSuiteName");

            System.out.println("TestSuiteJsonFileName : " + TestSuiteJsonFileName);

            JSONObject testName = returnJSONObjectFromTestName(TestSuiteJsonFileName);
            if (testName != null) {
                System.out.println(testName.toJSONString());
                if ((!testName.containsKey("resource")) || (testName.get("resource").equals(""))) {
                    testName = getKeyValueFromTestJson(Datafiles.get(TestSuiteJsonFileName.toLowerCase()), "default");
                }
            } else {
                System.out
                        .println("Test Data not found for the current test and no default test data is available. "
                                + TestSuiteJsonFileName);
                return null;
            }
            if (testName == null) {
                System.out
                        .println("Test Data not found for the current test in the test suite specific Json file. "
                                + TestSuiteJsonFileName);
                return null;
            }
            System.out.println("Key to Find : " + strJsonKey);
            jsonTestDataKeys = getKeyValueFromTestJson(testName, strJsonKey);
            if (!jsonObjectFound) {
                if (testName.containsKey("resource")) {
                    JSONObject sp = getKeyValueFromTestJson(testName,
                            "resource");

                    System.out.println("sp : " + sp.get("resource"));
                    JSONObject resourceName = returnJSONObjectFromResourceName(sp
                            .get("resource").toString().toLowerCase());
                    if (!(resourceName == null))
                        jsonTestDataKeys = getKeyValueFromTestJson(resourceName,
                                strJsonKey);
                    else
                        return null;
                } else {
                    System.out.println("sponsor key not found in json");
                }
            } else {
                System.out.println("Key found in Test suite file :"
                        + TestSuiteJsonFileName);
            }

        } catch (Exception e) {
            System.out
                    .println("Exception occured while retrieving the value for json key : "
                            + strJsonKey);
            e.printStackTrace();
            return null;
        }
        return jsonTestDataKeys;
    }

    /**
     * Keyword or method 'readObject' called recursively by getTestData method
     * to search for entire json file for required jsonkey
     *
     * @param Parent     parent jSONobject having the required jsonKey
     * @param strJsonKey String argument type eg: validvistacredentials / mandatory /
     *                   automationgroup1 etc
     */
    private static void readObject(JSONObject Parent, String strJsonKey) {

        for (Iterator iterator = Parent.keySet().iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            // System.out.println(jsonObject.get(key));
            if (Parent.containsKey(strJsonKey)) {
                jsonObjectFound = true;
                if ((!Parent.get(key).getClass().getName()
                        .equalsIgnoreCase("JAVA.LANG.STRING"))
                        && (!Parent.get(strJsonKey).getClass().getName()
                        .equalsIgnoreCase("JAVA.LANG.STRING"))) {
                    jsonTestDataKeys = (JSONObject) Parent.get(strJsonKey);
                } else {
                    // System.out.println("Value = "+ Parent.get(strJsonKey));
                    jsonTestDataKeys = Parent;
                }
                break;
            } else if (!Parent.get(key).getClass().getName()
                    .equalsIgnoreCase("JAVA.LANG.STRING")) {
                readObject((JSONObject) Parent.get(key), strJsonKey);
            }
        }
    }

    private static JSONObject returnJSONObjectFromTestName(String strSuiteName) {
        System.out.println("strSuiteName  :" + strSuiteName);
        testKey = getKeyValueFromTestJson(Datafiles.get(strSuiteName.toLowerCase()), JavaListener.hRobotTestDetails.get("currentTestName"));
        //getRobotGlobalVariables("TEST NAME")
        //JSONObject testKey = getKeyValueFromTestJson(Datafiles.get(strSuiteName.toLowerCase()), "8");
        if (testKey == null) {
            System.out.println("No Test data found for the current test case.");
            testKey = getKeyValueFromTestJson(Datafiles.get(strSuiteName.toLowerCase()), "default");
            //System.out.println(getKeyValueFromTestJson(Datafiles.get(strSuiteName.toLowerCase()), "default").get("default"));
            //System.out.println(testKey.toString());
        } else {
            System.out.println(testKey.toString());
        }
        return testKey;
    }

    private static JSONObject returnJSONObjectFromResourceName(
            String strResourceName) {
        // System.out.println("strSponsorName : "+strResourceName);
        // System.out.println(Datafiles.get(strResourceName));
        if (Datafiles.containsKey(strResourceName)) {
            return Datafiles.get(strResourceName);
        } else {
            System.out.println("No json file with sponsor name : "
                    + strResourceName);
            return null;
        }
    }

    private static JSONObject getKeyValueFromTestJson(
            JSONObject testJsonObject, String strKey) {

        System.out.println(" strKey to find - " + strKey + "\n");
        // System.out.println(testJsonObject.toJSONString());

        try {
            jsonObjectFound = false;
            // Loop through the keyset in json object
            for (Iterator iterator = testJsonObject.keySet().iterator(); iterator
                    .hasNext(); ) {
                String key = (String) iterator.next();
                // System.out.println("Key : " + key);

                if (key.equalsIgnoreCase(strKey)) {
                    // System.out.println("Not possbile to return parent json element. Provide the valid subelements under parent element "+
                    // strKey);
                    // System.out.println("value : " + testJsonObject.get(key));
                    if (!testJsonObject.get(key).getClass().getName()
                            .equalsIgnoreCase("JAVA.LANG.STRING")) {
                        jsonTestDataKeys = (JSONObject) testJsonObject.get(key);
                    }
                    jsonObjectFound = true;
                }

                // System.out.println(jsonObjectFound);
                if (jsonObjectFound) {
                    // System.out.println("objTestCaseKeys : " +
                    // testJsonObject.toJSONString());
                    break;
                } else if (!testJsonObject.get(key).getClass().getName()
                        .equalsIgnoreCase("JAVA.LANG.STRING")) {
                    readObject((JSONObject) testJsonObject.get(key), strKey);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(" jsonTestDataKeys"+jsonTestDataKeys);
        return jsonTestDataKeys;
    }

	/*public static void main(String args[]) {

		ReadJsonTestData rd = new ReadJsonTestData();
		rd.readJSON();
		 System.out.println(rd.getTestData("singlefield").get("singlefield"));
		// System.out.println(rd.getTestData("dental").get("homePhone"));
		// System.out.println(rd.getTestData("workinformation").get("phone"));
		//System.out.println(rd.getTestData("Testing").get("Testing"));
	}*/

    public static String getRobotGlobalVariables(String testName) {
        PyString gblVariable = (PyString) logger.getLoggingPythonInterpreter()
                .get().eval("BuiltIn().get_variable_value('${" + testName + "}')");
        return gblVariable.asString();
    }
}
