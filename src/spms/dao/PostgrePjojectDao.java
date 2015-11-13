package spms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.vo.Member;
import spms.vo.Project;

public class PostgrePjojectDao implements ProjectDao{
	
	Connection connection;
	DataSource ds;
	
	public void setDataSource (DataSource dataSource){
		this.ds = dataSource;
	}
	
	@Override
	public List<Project> selectList() throws Exception {
		// TODO Auto-generated method stub
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Member> list = new ArrayList<Member>();
		
		connection = ds.getConnection();
		stmt = connection.createStatement();
		
		String sql = "select * from projects";
		
		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				list.add(
						new Project()
						.setNo(rs.getString("pno"))
						
						);
			}
			
		} catch (Exception e){
			
		} finally {
			if(stmt!=null){stmt.cancel();}
			if(rs!=null){rs.close();}
//			if(connection != null){connPool.returnConnection(connection);}
		}
		
		return list;
	}

}
