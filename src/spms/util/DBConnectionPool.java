package spms.util;

import java.sql.Connection;
import java.util.ArrayList;

public class DBConnectionPool {
	private String url;
	private String username;
	private String password;
	ArrayList <Connection> connList = new ArrayList<Connection>();
	
	
	public DBConnectionPool(String url, String username, String password , String driver) throws Exception{
		this.url = url;
		this.password = password;
		this.username = username;
		
		Class.forName(driver);
	}
	
	public Connection getConnection() throws Exception {
		if(connList.size() > 0){
			Connection conn = connList.get(0);
			if(conn.isValid(10)){
				return conn;
			}
		} else {
			
		}
	}
	
	
}
