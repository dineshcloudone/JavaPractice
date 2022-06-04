package com.benefitfocus.robot.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map.Entry;
import java.util.*;

import org.json.simple.JSONObject;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywordOverload;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;

@RobotKeywords
public class Utilities {

    @Autowired
    protected ManageBrowser browser;

    @Autowired
    protected Logging logger;

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Keyword or method 'generateRandomNumber' used to generate random number
     * <p/>
     * <pre>
     * <b>Parameters :</b>
     * | value - input value to generate the random number |
     * <b>Examples : </b>
     * | RND - Randomly generated 5 digit number |
     * | RND:6 - Randomly generated number, with specified no. of digits |
     * | RNDadmin - Randomly generated 5 digit number is appended at the end of specified text 'admin' |
     * | RND:3:admin Randomly generated number, with specified no. of digits is appended at the end of specified text |
     * </pre>
     */
    @RobotKeyword
    @ArgumentNames({"value"})
    public String generateRandomNumber(String value) {

        String tempValue = value;
        String tempValueType = "";

        if (tempValue.startsWith("RND")) {
            tempValueType = tempValue.substring(0, 3);
            tempValue = value.substring(3);
        }

        if (tempValueType.equalsIgnoreCase("RND")) {

            int n = 5;

            if (tempValue.startsWith(":")) {
                tempValue = tempValue.substring(1);
                n = tempValue.indexOf(":");
                if (n != -1) {
                    String temp = tempValue.substring(n + 1);
                    n = Integer.parseInt(tempValue.substring(0, n));
                    tempValue = temp;
                } else {
                    n = Integer.parseInt(tempValue);
                    tempValue = "";
                }
            }

            for (int i = 0; i < n; i++) {
                String random = (int) (Math.random() * 9) + "";
                tempValue = tempValue + random;
            }
        }
        System.out.println("Random number generated : " + tempValue);
        return tempValue;
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Keyword or method 'getDate' used to generate specific date and specific
     * format
     * <p/>
     * <pre>
     * <b>Parameters :</b>
     * | value - input value to generate the required date |
     * <b>Examples : </b>
     * | d:currentdate Current Date d:currentdate:M:3 Current date + 3months |
     * | d:currentdate:M:-3 Current date - 3 months |
     * | d:effectivedate:d:3 1st date of currentmonth + 3 days |
     * | d:monthend:y:3 Current month end date + 3 years |
     * | d:monthend:d:3:format:EEE, MMM d, ''yy Current month end date + 3 days in specified format |
     * </pre>
     */
    @RobotKeyword
    @ArgumentNames({"value"})
    public String getDate(String value) {

        if (value.startsWith("d:"))
            value = value.substring(2);

        String dateFormat = "MM/dd/yyyy";

        int index = value.indexOf(":format:");

        if (index != -1) {
            dateFormat = value.substring(index + 8);
            value = value.substring(0, index);
        }

        System.out.println("Date Format : " + dateFormat);
        System.out.println("Date specified : " + value);

        String[] tempValues = value.split(":");

        String reqDate = "";

        DateFormat sdf = new SimpleDateFormat(dateFormat);
        Date today = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        if (tempValues[0].equalsIgnoreCase("currentdate")) {

            reqDate = sdf.format(today);
            System.out.println("Current Date  = " + reqDate);

        } else if (tempValues[0].equalsIgnoreCase("effectivedate")) {

            cal.set(Calendar.DAY_OF_MONTH, 1);

            reqDate = sdf.format((Date) cal.getTime());
            System.out.println("Effective Date = " + reqDate);

        } else if (tempValues[0].equalsIgnoreCase("monthend")) {

            cal.set(Calendar.DAY_OF_MONTH,
                    cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            reqDate = sdf.format((Date) cal.getTime());
            System.out.println("Month End Date = " + reqDate);

            if (tempValues.length == 3) {

                int changeby = Integer.parseInt(tempValues[2]);

                if (tempValues[1].equals("M"))
                    cal.add(Calendar.MONTH, changeby);

                else if (tempValues[1].equals("d"))
                    cal.add(Calendar.DATE, changeby);

                else if (tempValues[1].equals("y"))
                    cal.add(Calendar.YEAR, changeby);

                cal.set(Calendar.DAY_OF_MONTH,
                        cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                reqDate = sdf.format(cal.getTime());
                System.out.println("Required date : " + reqDate);

            }
            return reqDate;
        }

        if (tempValues.length == 1)
            return reqDate;

        int changeby = Integer.parseInt(tempValues[2]);

        if (tempValues.length == 3) {

            if (tempValues[1].equals("M"))
                cal.add(Calendar.MONTH, changeby);

            else if (tempValues[1].equals("d"))
                cal.add(Calendar.DATE, changeby);

            else if (tempValues[1].equals("y"))
                cal.add(Calendar.YEAR, changeby);

            reqDate = sdf.format(cal.getTime());
            System.out.println(" Required date : " + reqDate);
        }
        return reqDate;
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Keyword or method 'getValue' used get the values from run time hashmap
     * variables
     * <p/>
     * <pre>
     * <b>Parameters :</b>
     * | value - input value to generate the random number |
     * <b>Examples : </b>
     * | HMV<Variable name> eg: HMVlastname Retrieves the value for the variable stored already in runtime HashMap hMap |
     * | td:<jsonKey> eg: td:automationgroup1 Retrieves the value for the jsonKey available in json file - only jsonKey without child tags |
     * </pre>
     */
    @RobotKeyword
    @ArgumentNames({"value"})
    public String getValue(String value) {

        String tempValue = value;

        if (tempValue.startsWith("HMV")) {
            tempValue = value.substring(3);
            if (browser.hMap.containsKey(tempValue)) {
                tempValue = browser.hMap.get(tempValue);
                if (tempValue == null) {
                    System.out.println("There is no object with the name:"
                            + tempValue + " in the HashMap");
                    return value;
                }
            }

        } else if (tempValue.startsWith("td:")) {
            tempValue = tempValue.substring(3);
            JSONObject tempObj = ReadJsonTestData.getTestData(tempValue);

            if (tempObj.containsKey(tempValue)) {
                System.out.println("tempObj.get(tempValue).toString() "
                        + tempObj.get(tempValue).toString());
                return tempObj.get(tempValue).toString();
            } else {
                logger.warn(tempValue + " NOT found in the testdata.");
                System.out.println(tempValue + " NOT found in the testdata.");
                //return null;
            }
        } else if (tempValue.startsWith("d:")) {
            tempValue = value.substring(2);
            tempValue = getDate(tempValue);
        } else if (tempValue.startsWith("RND")) {
            tempValue = generateRandomNumber(tempValue);
        } else if ((tempValue.startsWith("currentdate")) || (tempValue.startsWith("effectivedate"))) {
            tempValue = getDate(tempValue);
        } else {
            if (JavaListener.hRobotTestDetails.containsKey(tempValue))
                tempValue = JavaListener.hRobotTestDetails.get(tempValue);
        }

        logger.info("Getvalue returns : " + tempValue);
        return tempValue;
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Method to sleep for specified amount of time in MilliSeconds
     * <pre>
     * <b>Example :</b>
     * | sleep 5000 |
     * </pre>
     */
    @RobotKeyword("Overloaded")
    @ArgumentNames({"n"})
    public void sleep(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
        }
    }

    @RobotKeywordOverload
    public void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }

    public String parseException(String strExceptionMessage) {
        //System.out.println(strExceptionMessage.substring(strExceptionMessage.indexOf("click:")+5,strExceptionMessage.indexOf("Command")));

        String strStartString = "Other element would receive the click:";
        String strEndString = "Command";

        String loc = strExceptionMessage.substring(strExceptionMessage.indexOf(strStartString) + strStartString.length(), strExceptionMessage.indexOf(strEndString)).trim();
        System.out.println("loc is " + loc);
        String tag = loc.substring(1, loc.indexOf(" "));
        System.out.println("tag is " + tag);
        String subloc = loc.substring(loc.indexOf(" "), loc.indexOf(">"));
        System.out.println("subloc is " + subloc);

        System.out.println("type of attribute : " + subloc.substring(0, subloc.indexOf("=")));
        System.out.println("attribute value : " + subloc.substring(subloc.indexOf("=") + 1).replace("\"", ""));
        if (loc.contains("container-fluid")) {
            subloc = "class=\"navbar-fixed-top-wrapper\"";
        } else if (subloc.contains("spinner-overlay")) {
            subloc = "id=\"page-spinner\"";
        } else if (loc.equals("<span></span>")) {
            tag = "div";
            subloc = "class=\"environment-message\"";
        } else if (loc.contains("please-wait")) {
            subloc = "id=\"please-wait\"";
        }

        String strLoc = "//" + tag.trim() + "[@" + subloc.trim() + "]";
        System.out.println(strLoc);

        return strLoc;
    }

    /**
     * Method to get Test Data from File dynamically
     * <p/>
     * <pre>
     * <b>Example :</b>
     * | sleep 5000 |
     * </pre>
     */
    @RobotKeyword
    @ArgumentNames({"value"})
    public String getHashMapDataFromFile(String keyvalue) {

        String[] hashMapData = new String[2];
        try {

            System.out.println("===========Value retrieved form text field"
                    + keyvalue);
            hashMapData = keyvalue.trim().split(":");

            System.out.println("===========Key :" + keyvalue);
            System.out.println("===========Value for the given key:" + hashMapData[1]);

        } catch (Exception e) {

            System.out
                    .println("Exception occured while reading test data from file:");
            System.out.println(e);
        }
        return hashMapData[1];
    }

    /**
     * Method to get data from HashMap using key
     * <p/>
     * <pre>
     * <b>Example :</b>
     * | lastname of member |
     * </pre>
     */
    @RobotKeyword
    @ArgumentNames({"strKey"})
    public String getHashMapValueUsingKey(String strKey) {

        StringBuffer sb = new StringBuffer();
        String val = null;
        try {

            System.out.println("Appending the below value :");

            sb.append(System.lineSeparator());
            sb.append("\n Appending the below value : \n");
            System.out.println("=========== value to get from HashMap====="
                    + strKey);

            val = browser.hMap.get(strKey);

            System.out.println("=========== value from HashMap=====" + val);

            sb.append(System.lineSeparator());
            sb.append(strKey + ":"
                    + val); //return sb.toString();

        } catch (Exception e) {

            System.out
                    .println("Exception occured while reading hashmap value using the given key");
            e.printStackTrace();

        }
        return sb.toString();
    }
}
