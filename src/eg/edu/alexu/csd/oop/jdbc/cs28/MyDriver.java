package eg.edu.alexu.csd.oop.jdbc.cs28;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class MyDriver implements Driver {

	@Override
	public boolean acceptsURL(String arg0) throws SQLException {
		
		return true;
	}

	@Override
	public Connection connect(String arg0, Properties arg1) throws SQLException {
		
		return null;
	}
	
	@Override
	public DriverPropertyInfo[] getPropertyInfo(String arg0, Properties arg1) throws SQLException {
		
		
		
		return null;
	}

	
	
	
	
	
	
	
	@Override
	public int getMajorVersion() {
		// Unimplemented method
		throw new UnsupportedOperationException();	
	}

	@Override
	public int getMinorVersion() {
		// Unimplemented method
		throw new UnsupportedOperationException();	
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// Unimplemented method
		throw new UnsupportedOperationException();	
	}

	@Override
	public boolean jdbcCompliant() {
		// Unimplemented method
		throw new UnsupportedOperationException();	
	}

}