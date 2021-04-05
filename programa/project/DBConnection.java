package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	private Connection connection = null;
	String ip = "172.17.0.2";
	String port = "3306";
	String dbName = "ficha_medica";
	String user = "root";
	String pass = "pass";
	String url = "jdbc:mariadb://" + ip + ":" + port + "/" + dbName;
	
	public DBConnection() {
		try {
			this.connection = DriverManager.getConnection(url, user, pass);
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	public void queryAndPrint(String query){
		Statement stm;
		try {
			stm = this.connection.createStatement();
			ResultSet rs = stm.executeQuery(query);
			ResultSetMetaData remt = rs.getMetaData();
			int columnsNumber = remt.getColumnCount();			
			
			while(rs.next()) {
				for(int i = 1; i <=columnsNumber;i++) {
					String columnValue = rs.getString(i);
					System.out.println(remt.getColumnName(i)+ ": " + columnValue);
				}
				System.out.println("***************************");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void closeConnection() {
		try {
			if(!this.connection.isClosed())
				this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
