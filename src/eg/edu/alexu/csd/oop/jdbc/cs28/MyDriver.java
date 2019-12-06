package eg.edu.alexu.csd.oop.jdbc.cs28;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;

import eg.edu.alexu.csd.oop.jdbc.cs28.superClasses.SuperDriver;

public class MyDriver extends SuperDriver {

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
			File file = (File) arg1.get("path") ;
			String path =  file.getAbsolutePath();
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

}
