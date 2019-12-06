package eg.edu.alexu.csd.oop.jdbc.cs28;

import java.sql.SQLException;
import java.sql.Statement;

import eg.edu.alexu.csd.oop.db.cs33.DatabaseImp;
import eg.edu.alexu.csd.oop.jdbc.cs28.superClasses.SuperConnection;

public class MyConnection extends SuperConnection {

	private String path;
	private boolean closed;
	private  DatabaseImp DB = new DatabaseImp();


	public MyConnection(String path) {
		this.closed = false;
		this.path = path;
	}

	@Override
	public Statement createStatement() throws SQLException {
		return new MyStatement(this, path, DB);
	}

	@Override
	public void close() throws SQLException {
		if (closed) {
			throw new SQLException();
		}
		closed = true;
	}
}
