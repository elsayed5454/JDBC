package eg.edu.alexu.csd.oop.jdbc.cs28;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.cs33.DatabaseImp;
import eg.edu.alexu.csd.oop.jdbc.cs28.superClasses.SuperStatement;

public class MyStatement extends SuperStatement {

	private final DatabaseImp DB = new DatabaseImp();
	private ArrayList<String> batch = new ArrayList<String>();
	private int TimeOut;
	private boolean closed;
	private Connection connection;

	public MyStatement(Connection connection, String path) throws SQLException {
		this.connection = connection;
		try {
			execute("CREATE DATABASE " + path);
		} catch (SQLException e) {
			throw new SQLException("Can't create database");
		}
		closed = false;
	}

	@Override
	public void addBatch(String sql) throws SQLException {

		if (closed) {
			throw new SQLException("Statement closed");
		}
		batch.add(sql);
	}

	@Override
	public void clearBatch() throws SQLException {

		if (closed) {
			throw new SQLException("Statement closed");
		}
		batch.clear();
	}

	@Override
	public void close() throws SQLException {

		if (closed) {
			throw new SQLException("Statement closed");
		}
		closed = true;
		batch = null;
	}

	@Override
	public boolean execute(String sql) throws SQLException {

		if (closed) {
			throw new SQLException("Statement closed");
		}

		// Retrieve first word of the sql statement and send the query to the
		// appropriate function
		String sqlKey = sql.split("[\\s]+")[0];
		boolean result = false;

		if (sqlKey.equalsIgnoreCase("create") || sqlKey.equalsIgnoreCase("drop")) {
			return DB.executeStructureQuery(sql);
		}

		// If the result set return isn't null therefore the statement executed properly
		else if (sqlKey.equalsIgnoreCase("select")) {
			if (executeQuery(sql) != null) {
				result = true;
			}
		}

		// If the returned updated rows count doesn't equal zero therefore the statement
		// executed properly
		else if (sqlKey.equalsIgnoreCase("insert") || sqlKey.equalsIgnoreCase("delete")
				|| sqlKey.equalsIgnoreCase("update")) {
			if (executeUpdate(sql) != 0) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public int[] executeBatch() throws SQLException {
		int[] updateCounts = new int[batch.size()];
		String command;

		// Looping through commands identifying each statement and calling the
		// appropriate executing function
		for (int i = 0; i < batch.size(); i++) {
			command = batch.get(i);

			switch (identifySQl(command)) {
			case "structure":
				if (execute(command) == true) {
					updateCounts[i] = SUCCESS_NO_INFO;
				} else {
					updateCounts[i] = EXECUTE_FAILED;
				}
				break;

			case "select":
				if (executeQuery(command) != null) {
					updateCounts[i] = SUCCESS_NO_INFO;
				} else {
					updateCounts[i] = EXECUTE_FAILED;
				}
				break;

			case "update":
				updateCounts[i] = executeUpdate(command);
				break;

			case "NonSQL":
				updateCounts[i] = EXECUTE_FAILED;
			}
		}
		return updateCounts;
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		if (closed) {
			throw new SQLException("Statement closed");
		}
		return new MyResultSet(DB.executeQuery(sql), DB.getCurrentQueryTableName(), this, DB.getCurrentColumnNames());
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		if (closed) {
			throw new SQLException("Statement closed");
		}
		return DB.executeUpdateQuery(sql);
	}

	@Override
	public Connection getConnection() throws SQLException {
		if (closed) {
			throw new SQLException("Statement closed");
		}
		return this.connection;
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		if (closed) {
			throw new SQLException("Statement closed");
		}
		return TimeOut;
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		if (closed) {
			throw new SQLException("Statement closed");
		}
		this.TimeOut = seconds;
	}

	// Helper method to identify the sql statement
	private String identifySQl(String sql) {

		// Retrieve first word of the sql statement
		String sqlKey = sql.split("[\\s]+")[0];

		if (sqlKey.equalsIgnoreCase("create") || sqlKey.equalsIgnoreCase("drop")) {
			return "structure";
		}

		else if (sqlKey.equalsIgnoreCase("select")) {
			return "select";
		}

		else if (sqlKey.equalsIgnoreCase("insert") || sqlKey.equalsIgnoreCase("delete")
				|| sqlKey.equalsIgnoreCase("update")) {
			return "update";
		}

		return "NonSQL";
	}

}
