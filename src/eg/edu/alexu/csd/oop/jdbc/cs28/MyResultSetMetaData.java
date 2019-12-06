package eg.edu.alexu.csd.oop.jdbc.cs28;

import java.sql.SQLException;

import eg.edu.alexu.csd.oop.jdbc.cs28.superClasses.SuperResultSetMetaData;

public class MyResultSetMetaData extends SuperResultSetMetaData {

	@Override
	public int getColumnCount() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getColumnLabel(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnName(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getColumnType(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	// Unimplemented methods
	@Override
	public String getTableName(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
