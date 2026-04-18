package app.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import app.model.Signal;


public class SignalDatabase {

	public Connection connect() {
		Connection conn = null;
		try {
			String url = "jdbc:sqlite:signals.db";
			conn = DriverManager.getConnection(url);
			System.out.println("Connected to SQLite!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public void createTable() {
		
		//SQL statement for creating table
		var sql = "CREATE TABLE IF NOT EXISTS signals ("
				+ "		id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "		type TEXT NOT NULL,"
				+ "		strength INTEGER NOT NULL,"
				+ "		status TEXT NOT NULL"
				+ ");";
		
		//open connection to sqlite database
		try(var conn = connect();
			
				//create statement class allowing for execution of SQL statements
				var stmt = conn.createStatement()) {
			
				//Create new table
				stmt.execute(sql);
				
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertSignal(Signal signal) {
		var sql = "INSERT INTO signals(type, strength, status) VALUES(?, ?, ?)";
		
		try(var conn = connect();
				
				//create a PreparedStatement object from Connection object with the INSERT statement
				var pstmt = conn.prepareStatement(sql)) {
				
				//use for-loop if iterating over multiple signals
				pstmt.setString(1, signal.getType());
				pstmt.setInt(2, signal.getStrength());
				pstmt.setString(3, signal.getStatus());
				
				pstmt.executeUpdate();
				
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		                                             
	}
	
}
 