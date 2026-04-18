package app.data;

import java.util.ArrayList;
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
	
	
	//insert signal object into signalDB
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
	
	public ArrayList<Signal> getAllSignals() {
		
		var sql = "SELECT * FROM signals";
		ArrayList<Signal> signalList = new ArrayList<>();
		
		try(var conn = connect();
				var stmt = conn.createStatement()) {
				
			//return ResultSet object 
				var rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					String type = rs.getString("type");
					int strength = rs.getInt("strength");
					String status = rs.getString("status");
					int id = rs.getInt("id");
					
					Signal signal = new Signal(id, type, strength, status);
					signalList.add(signal);
					}
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return signalList;
	}
	
	public boolean updateSignalStatus(int id, String status) {
		var sql = "UPDATE signals SET status = ? WHERE id = ?";
		
		try(var conn = connect();
				
			var pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, status);
				pstmt.setInt(2, id);
				
				pstmt.executeUpdate();
				
				int rowsUpdated = pstmt.executeUpdate();
				
				
				return rowsUpdated > 0;
				
		} catch(SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
		
	}
	
	public boolean deleteSignal(int id) {
		var sql = "DELETE FROM signals WHERE id = ?";
		
		try(var conn = connect(); 
			var pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, id);
			int rowsUpdated = pstmt.executeUpdate();
			return rowsUpdated > 0;
			
		} catch(SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
}
 