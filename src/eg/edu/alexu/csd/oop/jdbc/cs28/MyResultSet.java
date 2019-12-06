package eg.edu.alexu.csd.oop.jdbc.cs28;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.jdbc.cs28.superClasses.SuperResultSet;

public class MyResultSet extends SuperResultSet {

	// Map that links the Column label to its index in the Result ArrayList
	Map<String, Integer> Columns = new HashMap<String, Integer>();

	// ArrayList that holds all the data
	ArrayList<Map<Integer, Object>> Result = new ArrayList<Map<Integer, Object>>();

	// Current Index in the Result ArrayList
	int currentInd = -1;

	// Size of Result ArrayList
	int size = Result.size();

	boolean closed = false;

	public MyResultSet(Object[][] arr) {
		int height = arr[0].length;
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			for (int j = 0; j < height; j++) {
				map.put(j, arr[i][j]);
			}
			Result.add(map);
		}
	}

	@Override
	public boolean absolute(int row) throws SQLException {

		if (row <= size) {
			currentInd = row - 1;
			return true;
		}
		return false;
	}

	@Override
	public void afterLast() throws SQLException {

		currentInd = size;
	}

	@Override
	public void beforeFirst() throws SQLException {

		currentInd = -1;
	}

	@Override
	public boolean first() throws SQLException {

		if (size > 0) {
			currentInd = 0;
			return true;
		}

		return false;
	}

	@Override
	public boolean last() throws SQLException {

		currentInd = size - 1;
		if (size > 0)
			return true;

		return false;
	}

	@Override
	public boolean next() throws SQLException {

		currentInd++;
		if (currentInd < size)
			return true;
		return false;
	}

	@Override
	public boolean previous() throws SQLException {

		currentInd--;
		if (currentInd < 0)
			return false;

		return true;
	}

	// Returns the Integer value of a specific column in the current row GIVEN THE
	// COLUMN INDEX STARTING FROM 1
	@Override
	public int getInt(int columnIndex) throws SQLException {

		return (int) Result.get(currentInd).get(columnIndex);
	}

	// Returns the Integer value of a specific column in the current row GIVEN THE
	// COLUMN LABEL
	@Override
	public int getInt(String columnLabel) throws SQLException {

		int columnIndex = Columns.get(columnLabel);
		return getInt(columnIndex);
	}

	@Override
	public Object getObject(int columnIndex) throws SQLException {

		return Result.get(currentInd).get(columnIndex);
	}

	// Returns the String value of a specific column in the current row GIVEN THE
	// COLUMN INDEX STARTING FROM 1
	public String getString(int columnIndex) throws SQLException {

		return (Result.get(currentInd).get(columnIndex)).toString();
	}

	// Return the String value of a specific column in the current row GIVEN THE
	// COLUMN LABEL
	@Override
	public String getString(String columnLabel) throws SQLException {

		int columnIndex = Columns.get(columnLabel);
		return getString(columnIndex);
	}

	// Returns whether the cursor is after the last row
	@Override
	public boolean isAfterLast() throws SQLException {

		if (currentInd < size)
			return false;

		return true;
	}

	// Returns whether the cursor is before the first row
	@Override
	public boolean isBeforeFirst() throws SQLException {

		if (currentInd >= 0)
			return false;

		return true;
	}

	@Override
	public void close() throws SQLException {

		Result.clear();
		Columns.clear();
		currentInd = -1;
		size = 0;
		closed = true;
	}

	@Override
	public boolean isFirst() throws SQLException {

		if (currentInd == 0)
			return true;
		return false;
	}

	@Override
	public boolean isLast() throws SQLException {

		if (currentInd == size - 1)
			return true;
		return false;
	}

	@Override
	public int findColumn(String columnLabel) throws SQLException {

		return Columns.get(columnLabel);
	}

	@Override
	public boolean isClosed() throws SQLException {

		return closed;
	}

	// ALL FOLLOWING METHODS STILL NEED TO BE IMPLEMENTED
	@Override
	public ResultSetMetaData getMetaData() throws SQLException {

		return null;
	}

	@Override
	public Statement getStatement() throws SQLException {

		return null;
	}

}
