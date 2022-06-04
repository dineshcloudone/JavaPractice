package com.benefitfocus.robot.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

/**
 * This library supports database-related testing using the Robot Framework. It
 * allows to establish a connection to a certain database to perform tests on
 * the content of certain tables and/or views in that database. A possible
 * scenario for its usage is a Web-Application that is storing data to the
 * database based on some user actions. The actions in the Web-Application could 
 * be triggered using some tests based on Selenium and in the same test it will 
 * then be possible to check if the proper data has ended up in the database as expected.
 * 
 * <pre>
 * The following table lists some examples of drivers and connection strings
 * for some popular databases. 
 * | Database | Driver Name | Sample Connection String | Download Driver |
 * | MySql | com.mysql.jdbc.Driver | jdbc:mysql://servername/dbname | http://dev.mysql.com/downloads/connector/j/ |
 * | Oracle | oracle.jdbc.driver.OracleDriver | jdbc:oracle:thin:@servername:port:dbname | http://www.oracle.com/technology/tech/java/sqlj_jdbc/htdocs/jdbc_faq.html |
 * </pre>
 * 
 * The examples in the description of the keywords is based on a database table
 * named "MySampleTable" that has the following layout:
 * 
 * <pre>
 * MySampleTable: 
 * | COLUMN | TYPE | 
 * | Id | Number | 
 * | Name | String | 
 * | EMail | String | 
 * | Postings | Number | 
 * | State | Number | 
 * | LastPosting | Timestamp |
 * </pre>
 * 
 */
@RobotKeywords
public class DatabaseLibrary {
	public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

	private Connection connection = null;

	/**
	 * Default-constructor
	 */
	public DatabaseLibrary() {
	}

	/**
	 * Author : Arun Kasarla
	 * 
	 * Establish the connection to the database. This is mandatory before any of
	 * the other keywords can be used and should be ideally done during the
	 * suite setup phase. To avoid problems ensure to close the connection again
	 * using the disconnect-keyword.
	 * 
	 * It must be ensured that the JAR-file containing the given driver can be
	 * found from the CLASSPATH when starting robot. Furthermore it must be
	 * noted that the connection string is database-specific and must be valid
	 * of course.
	 * 
	 * <pre>
	 * Example: 
	 * | Connect To Database | com.mysql.jdbc.Driver | jdbc:mysql://my.host.name/myinstance | UserName | ThePassword |
	 * </pre>
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * 
	 */
	@RobotKeyword("Connects to the database defined by the given arguments.\n\n")
	@ArgumentNames({ "driverClassName", "connectionString", "dbUser", "dbPassword" })
	public void connectToDatabase(String driverClassName, String connectString,
			String dbUser, String dbPassword) throws SQLException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException {

		Class.forName(driverClassName).newInstance();
		setConnection(DriverManager.getConnection(connectString, dbUser,
				dbPassword));
	}

	/**
	 * Author : Arun Kasarla
	 * 
	 * Releases the existing connection to the database. In addition this
	 * keyword will log any SQLWarnings that might have been occurred on the
	 * connection.
	 * 
	 * <pre>
	 * <b>Example:</b> 
	 * | Disconnect from Database |
	 * </pre>
	 */
	@RobotKeyword("Disconnects from the database.\n\n" + "Example:\n"
			+ "| Disconnect from Database |\n")
	public void disconnectFromDatabase() throws SQLException {
		System.out.println("SQL Warnings on this connection: "
				+ getConnection().getWarnings());
		getConnection().close();
	}

	/**
	 * This keyword can be used to check for proper content inside a specific
	 * row in a database table. For this it is possible to give a
	 * comma-separated list of column names in the first parameter and a
	 * pipe-separated list of values in the second parameter. Then the name of
	 * the table and the rownum to check must be passed to this keyword. The
	 * corresponding values are then read from that row in the given table and
	 * compared to the expected values. If all values match the teststep will
	 * pass, otherwise it will fail.
	 * 
	 * <pre>
	 * <b>Example:</b>
	 * | Check Content for Row Identified by Rownum | Name,EMail | John Doe|john.doe@x-files | MySampleTable | 4 |
	 * </pre>
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	@RobotKeyword("Checks the content of a specific record in a given table identified by its row number.\n\n"
			+ "Example:\n"
			+ "| Check Content for Row Identified by Rownum | Name,EMail | John Doe|john.doe@x-files | MySampleTable | 4 |\n")
	@ArgumentNames({ "columnNames", "expectedValues", "tableName",
			"rowNumValue" })
	public void checkContentForRowIdentifiedByRownum(String columnNames,
			String expectedValues, String tableName, String rowNumValue)
			throws SQLException, Exception {

		long rowNum = Long.valueOf(rowNumValue);

		String sqlString = "select " + columnNames + " from " + tableName;

		String[] columns = columnNames.split(",");
		String[] values = expectedValues.split("\\|");

		Statement stmt = getConnection().createStatement();
		stmt.executeQuery(sqlString);
		ResultSet rs = (ResultSet) stmt.getResultSet();

		long count = 0;
		while (rs.next()) {

			count++;
			if (count == rowNum) {

				for (int i = 0; i < columns.length; i++) {
					String fieldValue = rs.getString(columns[i]);
					System.out.println(columns[i] + " -> " + fieldValue);

					if (values[i].equals("(NULL)")) {
						values[i] = "";
					}

					if (!fieldValue.equals(values[i])) {
						throw new Exception("Value found: '" + fieldValue
								+ "'. Expected: '" + values[i] + "'");
					}
				}
				break;
			}
		}

		// Rownum does not exist
		if (count != rowNum) {
			throw new Exception("Given rownum does not exist for statement: "
					+ sqlString);
		}

		rs.close();
		stmt.close();
	}

	/**
	 * Author : Arun Kasarla
	 * 
	 * This keyword can be used to check for proper content inside a specific
	 * row in a database table. For this it is possible to give a
	 * comma-separated list of column names in the first parameter and a
	 * pipe-separated list of values in the second parameter. Then the name of
	 * the table and a statement used in the where-clause to identify a concrete
	 * row. The corresponding values are then read from the row identified this
	 * way and compared to the expected values. If all values match the teststep
	 * will pass, otherwise it will fail.
	 * 
	 * If the where-clause will select more or less than exactly one row the
	 * test will fail.
	 * 
	 * <pre>
	 * <b>Example:</b> 
	 * | Check Content for Row Identified by WhereClause | Name,EMail | John Doe|john.doe@x-files | MySampleTable | Postings=14 |
	 * </pre>
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	@RobotKeyword("Checks the content of a specific record in a given table identified by a where-clause.\n\n"
			+ "Example:\n"
			+ "| Check Content for Row Identified by WhereClause | Name,EMail | John Doe|john.doe@x-files | MySampleTable | Postings=14 |\n")
	@ArgumentNames({ "columnNames", "expectedValues", "tableName",
			"whereClause" })
	public void checkContentForRowIdentifiedByWhereClause(String columnNames,
			String expectedValues, String tableName, String whereClause)
			throws SQLException, Exception {

		String sqlString = "select " + columnNames + " from " + tableName
				+ " where " + whereClause;

		String[] columns = columnNames.split(",");
		String[] values = expectedValues.split("\\|");

		Statement stmt = getConnection().createStatement();
		stmt.executeQuery(sqlString);
		ResultSet rs = (ResultSet) stmt.getResultSet();

		long count = 0;
		while (rs.next()) {
			count++;
			if (count == 1) {

				for (int i = 0; i < columns.length; i++) {
					String fieldValue = rs.getString(columns[i]);
					System.out.println(columns[i] + " -> " + fieldValue);

					if (values[i].equals("(NULL)")) {
						values[i] = "";
					}

					if (!fieldValue.equals(values[i])) {
						throw new Exception("Value found: '" + fieldValue
								+ "'. Expected: '" + values[i] + "'");
					}
				}
			}

			// Throw exception if more than one row is selected by the given
			// "where-clause"
			if (count > 1) {
				throw new Exception(
						"More than one row fetched by given where-clause for statement: "
								+ sqlString);
			}
		}

		// Throw exception if no rrow was fetched by given where-clause
		if (count == 0) {
			throw new Exception(
					"No row fetched by given where-clause for statement: "
							+ sqlString);
		}

		rs.close();
		stmt.close();
	}

	/**
	 * Author : Arun Kasarla
	 * 
	 * Reads a single value from the given table and column based on the
	 * where-clause passed to the test. If the where-clause identifies more or
	 * less than exactly one row in that table this will result in an error for
	 * this teststep. Otherwise the selected value will be returned.
	 * 
	 * <pre>
	 * <b>Example:</b>
	 * | ${VALUE}= | Read single Value from Table | MySampleTable | EMail | Name='John Doe' |
	 * </pre>
	 * 
	 */
	@RobotKeyword("Reads and returns a single value from one column and record of a given table identified by a where-clause.\n\n"
			+ "Example:\n"
			+ "| ${VALUE}= | Read single Value from Table | MySampleTable | EMail | Name='John Doe' |\n")
	@ArgumentNames({ "tableName", "columnName", "whereClause" })
	public String readSingleValueFromTable(String tableName, String columnName,
			String whereClause) throws SQLException {

		String ret = "";

		String sql = "select " + columnName + " from " + tableName + " where "
				+ whereClause;
		Statement stmt = getConnection().createStatement();
		stmt.executeQuery(sql);
		ResultSet rs = (ResultSet) stmt.getResultSet();
		rs.next();
		ret = rs.getString(columnName);
		rs.close();
		stmt.close();

		return ret;
	}

	/**
	 * Author : Arun Kasarla
	 * 
	 * Checks that the primary key columns of a given table match the columns
	 * given as a comma-separated list. Note that the given list must be ordered
	 * by the name of the columns. Upper and lower case for the columns as such
	 * is ignored by comparing the values after converting both to lower case.
	 * 
	 * NOTE: Some database expect the table names to be written all in upper
	 * case letters to be found.
	 * 
	 * <pre>
	 * <b>Example:</b>
	 * | Check Primary Key Columns For Table | MySampleTable | Id,Name |
	 * </pre>
	 * 
	 * @throws SQLException
	 * @throws Exception
	 * @throws Exception
	 */
	@RobotKeyword("Checks that the givn columns are defined as the primary key for the given table.\n\n"
			+ "Example:\n"
			+ "| Check Primary Key Columns For Table | MySampleTable | Id,Name |\n")
	@ArgumentNames({ "tableName", "columnList" })
	public void checkPrimaryKeyColumnsForTable(String tableName,
			String columnList) throws SQLException, Exception {

		String keys = "";

		DatabaseMetaData dbm = getConnection().getMetaData();
		ResultSet rs = dbm.getPrimaryKeys(null, null, tableName);

		while (rs.next()) {
			keys = rs.getString("COLUMN_NAME") + ",";
		}

		// Remove the last ","
		if (keys.length() > 0) {
			keys = keys.substring(0, keys.length() - 1);
		}

		columnList = columnList.toLowerCase();
		keys = keys.toLowerCase();

		if (!columnList.equals(keys)) {
			throw new Exception("Given column list: " + columnList
					+ " Keys found: " + keys);
		}

	}

	/**
	 * Author : Arun Kasarla
	 * 
	 * Returns a comma-separated list of the primary keys defined for the given
	 * table. The list if ordered by the name of the columns.
	 * 
	 * NOTE: Some database expect the table names to be written all in upper
	 * case letters to be found.
	 * 
	 * <pre>
	 * Example: 
	 * | ${KEYS}= | Get Primary Key Columns For Table | MySampleTable |
	 * </pre>
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	@RobotKeyword("Returns a comma-separated list of the primary key columns for the given table.\n\n"
			+ "Example:\n"
			+ "| ${KEYS}= | Get Primary Key Columns For Table | MySampleTable |\n")
	@ArgumentNames({ "tableName" })
	public String getPrimaryKeyColumnsForTable(String tableName)
			throws SQLException {

		String ret = "";

		DatabaseMetaData dbm = getConnection().getMetaData();
		ResultSet rs = dbm.getPrimaryKeys(null, null, tableName);

		while (rs.next()) {
			ret = rs.getString("COLUMN_NAME") + ",";
		}

		// Remove the last ","
		if (ret.length() > 0) {
			ret = ret.substring(0, ret.length() - 1);
		}

		return ret;
	}

	/**
	 * Author : Arun Kasarla
	 * 
	 * Executes the given SQL without any further modifications. The given SQL
	 * must be valid for the database that is used. The main purpose of this
	 * keyword is building some contents in the database used for later testing.
	 * 
	 * NOTE: Use this method with care as you might cause damage to your
	 * database, especially when using this in a productive environment.
	 * 
	 * <pre>
	 * <b>Example:</b> 
	 * | Execute SQL | CREATE TABLE MyTable (Num INTEGER) |
	 * </pre>
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	@RobotKeyword("Executes the given SQL-statement.\n\n" + "Example:\n"
			+ "| Execute SQL | CREATE TABLE MyTable (Num INTEGER) |\n")
	@ArgumentNames({ "sqlString" })
	public void executeSQL(String sqlString) throws SQLException {

		Statement stmt = getConnection().createStatement();
		System.out.println(stmt.execute(sqlString));
		
	}

	/**
	 * Author : Arun Kasarla
	 * 
	 * Executes the SQL statements contained in the given file without any
	 * further modifications. The given SQL must be valid for the database that
	 * is used. Any lines prefixed with "REM" or "#" are ignored. This keyword
	 * can for example be used to setup database tables from some SQL install
	 * script.
	 * 
	 * Single SQL statements in the file can be spread over multiple lines, but
	 * must be terminated with a semicolon ";". A new statement must always
	 * start in a new line and not in the same line where the previous statement
	 * was terminated by a ";".
	 * 
	 * In case there is a problem in executing any of the SQL statements from
	 * the file the execution is terminated and the operation is rolled back.
	 * 
	 * NOTE: Use this method with care as you might cause damage to your
	 * database, especially when using this in a productive environment.
	 * 
	 * <pre>
	 * Example: 
	 * | Execute SQL from File | myFile.sql |
	 * </pre>
	 * 
	 * @throws IOExcetion
	 * @throws SQLException
	 * @throws Exception
	 */
	@RobotKeyword("Executes the SQL-statements contained in the given file.\n\n"
			+ "Example:\n" + "| Execute SQL from File | myFile.sql |\n")
	@ArgumentNames({ "fileName" })
	public void executeSQLFromFile(String fileName) throws SQLException,
			IOException, Exception {

		getConnection().setAutoCommit(false);

		FileReader fr = new FileReader(new File(fileName));
		BufferedReader br = new BufferedReader(fr);

		String sql = "";
		String line = "";
		while ((line = br.readLine()) != null) {
			line = line.trim();

			// Ignore lines commented out in the given file
			if (line.toLowerCase().startsWith("rem")) {
				continue;
			}
			if (line.startsWith("#")) {
				continue;
			}

			// Add the line to the current SQL statement
			sql += line;

			// Check if SQL statement is complete, if yes execute
			try {
				if (sql.endsWith(";")) {
					sql = sql.substring(0, sql.length() - 1);
					System.out.println("Executing: " + sql);
					executeSQL(sql);
					sql = "";
				}
			} catch (SQLException e) {
				sql = "";
				br.close();
				getConnection().rollback();
				getConnection().setAutoCommit(true);
				throw new Exception("Error executing: " + sql
						+ " Execution from file rolled back!");
			}
		}

		getConnection().commit();
		getConnection().setAutoCommit(true);
		br.close();
	}

	/**
	 * Author : Arun Kasarla
	 * 
	 * Executes the SQL statements contained in the given file without any
	 * further modifications. The given SQL must be valid for the database that
	 * is used. Any lines prefixed with "REM" or "#" are ignored. This keyword
	 * can for example be used to setup database tables from some SQL install
	 * script.
	 * 
	 * Single SQL statements in the file can be spread over multiple lines, but
	 * must be terminated with a semicolon ";". A new statement must always
	 * start in a new line and not in the same line where the previous statement
	 * was terminated by a ";".
	 * 
	 * Any errors that might happen during execution of SQL statements are
	 * logged to the Robot Log-file, but otherwise ignored.
	 * 
	 * NOTE: Use this method with care as you might cause damage to your
	 * database, especially when using this in a productive environment.
	 * 
	 * <pre>
	 * <b>Example:</b>
	 * | Execute SQL from File | myFile.sql |
	 * </pre>
	 * 
	 * @throws IOExcetion
	 * @throws SQLException
	 * @throws Exception
	 */
	@RobotKeyword("Executes the SQL-statements contained in the given file.\n\n"
			+ "Example:\n"
			+ "| Execute SQL from File Ignore Errors| myFile.sql |\n")
	@ArgumentNames({ "fileName" })
	public void executeSQLFromFileIgnoreErrors(String fileName)
			throws SQLException, IOException, Exception {

		getConnection().setAutoCommit(false);

		FileReader fr = new FileReader(new File(fileName));
		BufferedReader br = new BufferedReader(fr);

		String sql = "";
		String line = "";
		while ((line = br.readLine()) != null) {
			line = line.trim();

			// Ignore lines commented out in the given file
			if (line.toLowerCase().startsWith("rem")) {
				continue;
			}
			if (line.startsWith("#")) {
				continue;
			}

			// Add the line to the current SQL statement
			sql += line;

			// Check if SQL statement is complete, if yes execute
			try {
				if (sql.endsWith(";")) {
					sql = sql.substring(0, sql.length() - 1);
					System.out.println("Executing: " + sql + "\n");
					executeSQL(sql);
					sql = "";
					System.out.println("\n");
				}
			} catch (SQLException e) {
				System.out.println("Error executing: " + sql + "\n"
						+ e.getMessage() + "\n\n");
				sql = "";
			}
		}

		getConnection().commit();
		getConnection().setAutoCommit(true);
		br.close();
	}

	/**
	 * Author : Arun Kasarla
	 * 
	 * This keyword checks that a given table contains a given amount of rows
	 * matching a given WHERE clause.
	 * 
	 * For the example this means that the table "MySampleTable" must contain
	 * exactly 2 rows matching the given WHERE, otherwise the teststep will
	 * fail.
	 * 
	 * <pre>
	 * <b>Example:</b> 
	 * | Verify Number Of Rows Matching Where | MySampleTable | email=x@y.net | 2 |
	 * </pre>
	 * 
	 * @throws SQLException
	 * @throws Exception
	 **/
	@RobotKeyword("Author: Arun Kasarla \n Checks that the given table contains the given number of records matching the given WHERE clause.\n\n"
			+ "Example:\n"
			+ "| Verify Number Of Rows Matching Where | MySampleTable | email=x@y.net | 2 |\n")
	@ArgumentNames({ "tableName", "where", "rowNumValue" })
	public boolean verifyNumberOfRowsMatchingWhere(String tableName, String where,
			String rowNumValue) throws SQLException, Exception {

		long rowNum = Long.valueOf(rowNumValue);

		long num = getNumberOfRows(tableName, where, (rowNum + 1));
		return (num == rowNum); 
	}

	private void setConnection(Connection connection) {
		this.connection = connection;
	}

	private Connection getConnection() {
		return connection;
	}

	private long getNumberOfRows(String tableName, String where, long limit)
			throws SQLException {

		// Let's first try with count(*), but this is not supported by all
		// databases.
		// In this case an exception will be thrown and we will read the amount
		// of records the "hard way", but luckily limited by the amount of rows
		// expected,
		// so that this might not be too bad.
		long num = -1;
		try {
			String sql = "select count(*) from " + tableName + " where "
					+ where;
			Statement stmt = getConnection().createStatement();
			stmt.executeQuery(sql);
			ResultSet rs = (ResultSet) stmt.getResultSet();
			rs.next();
			num = rs.getLong("count(*)");
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			String sql = "select * from " + tableName + " where " + where;
			Statement stmt = getConnection().createStatement();
			stmt.executeQuery(sql);
			ResultSet rs = (ResultSet) stmt.getResultSet();
			num = 0;
			while ((rs.next())) {
				num++;
			}
			rs.close();
			stmt.close();
		}
		return num;
	}

	private long getNumberOfRows(String tableName, long limit)
			throws SQLException {

		// Let's first try with count(*), but this is not supported by all
		// databases.
		// In this case an exception will be thrown and we will read the amount
		// of records the "hard way", but luckily limited by the amount of rows
		// expected,
		// so that this might not be too bad.
		long num = -1;
		try {
			String sql = "select count(*) from " + tableName;
			Statement stmt = getConnection().createStatement();
			stmt.executeQuery(sql);
			ResultSet rs = (ResultSet) stmt.getResultSet();
			rs.next();
			num = rs.getLong("count(*)");
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			String sql = "select * from " + tableName;
			Statement stmt = getConnection().createStatement();
			stmt.executeQuery(sql);
			ResultSet rs = (ResultSet) stmt.getResultSet();
			num = 0;
			while ((rs.next()) && (num < limit)) {
				num++;
			}
			rs.close();
			stmt.close();
		}
		return num;
	}

	private long getNumberOfRows(String tableName) throws SQLException {

		// Let's first try with count(*), but this is not supported by all
		// databases.
		// In this case an exception will be thrown and we will read the amount
		// of records the "hard way"
		long num = -1;
		try {
			String sql = "select count(*) from " + tableName;
			Statement stmt = getConnection().createStatement();
			stmt.executeQuery(sql);
			ResultSet rs = (ResultSet) stmt.getResultSet();
			rs.next();
			num = rs.getLong("count(*)");
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			String sql = "select * from " + tableName;
			Statement stmt = getConnection().createStatement();
			stmt.executeQuery(sql);
			ResultSet rs = (ResultSet) stmt.getResultSet();
			num = 0;
			while (rs.next()) {
				num++;
			}
			rs.close();
			stmt.close();
		}
		return num;
	}
}
