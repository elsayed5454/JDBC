package eg.edu.alexu.csd.oop.jdbc.cs28;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

public class MyDriver implements Driver {

	@Override
	public boolean acceptsURL(String arg0) throws SQLException {
		
		String[] split = arg0.split(":") ; 
		if (split.length != 3  || !split[0].equals("jdbc")  ||  !split[1].equals("xmldb")  ||  !split[2].startsWith("//")) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public Connection connect(String arg0, Properties arg1) throws SQLException {
		
		if (acceptsURL(arg0) && arg1.containsKey("path") && arg1.get("path") != null ) {
			String path =  (String) arg1.get("path");
			return new MyConnection(path) ;			
		}

		return null;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String arg0, Properties arg1) throws SQLException {

		Set<Object> list = arg1.keySet();
		
		if (acceptsURL(arg0) && list != null) {
			return (DriverPropertyInfo[]) list.toArray() ;
		}
		
		return new DriverPropertyInfo[0];
	}

	// Unimplemented methods
	@Override
	public int getMajorVersion() {
				throw new UnsupportedOperationException();
	}

	@Override
	public int getMinorVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean jdbcCompliant() {
		throw new UnsupportedOperationException();
	}

}
