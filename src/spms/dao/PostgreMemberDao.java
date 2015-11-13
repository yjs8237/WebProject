package spms.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;




import spms.annotation.Component;
import spms.util.DBConnectionPool;
import spms.vo.Member;


@Component("memberDao")
public class PostgreMemberDao implements MemberDao{
	
	Connection connection;
	DataSource ds;
	
	public void setDataSource (DataSource dataSource){
		this.ds = dataSource;
	}
	
	public List<Member> selectList() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Member> list = new ArrayList<Member>();
		
		
		
//		connection = connPool.getConnection();
		connection = ds.getConnection();
		stmt = connection.createStatement();
		
		String sql = "select * from study";
		
		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				list.add(new Member().setNo(rs.getString("mno")).setName(rs.getString("name")).setHeight(rs.getString("height")).
						setPhonenum(rs.getString("phonenum")).setEmail(rs.getString("email")));
			}
			
		} catch (Exception e){
			
		} finally {
			if(stmt!=null){stmt.cancel();}
			if(rs!=null){rs.close();}
//			if(connection != null){connPool.returnConnection(connection);}
		}
		
		return list;
	}
	
	public int insert(Member member) throws Exception{
		
		String sql = "insert into study (name , height , phonenum, email,mno) values (?,?,?,?,?)";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		connection = ds.getConnection();
		try{
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, member.getName()!= null ? member.getName() : "null");
			stmt.setString(2, member.getHeight() != null ? member.getHeight() : "null");
			stmt.setString(3, member.getPhonenum() != null ? member.getPhonenum() : "null");
			stmt.setString(4, member.getEmail() != null ? member.getEmail() : "null");
			stmt.setString(5, member.getNo() != null ? member.getNo() : "null");
			stmt.executeUpdate();
			
			return 1;
		}catch (Exception e){
			return -1;
		}finally {
			try{if(stmt!=null){stmt.close();}}catch(Exception e){}
			try{if(stmt!=null){stmt.close();}}catch(Exception e){}
			
		}
		
	}
	
	
	public Member selectOne(int no) throws Exception{
		Statement stmt = null;
		ResultSet rs = null;
		connection = ds.getConnection();
		String sql = "select * from study where mno = '" + String.valueOf(no) +"'";
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		rs.next();
		Member member = new Member();
		member.setNo(rs.getString("mno")).setEmail(rs.getString("email")).setHeight(rs.getString("height")).setName(rs.getString("name"))
		.setPhonenum(rs.getString("phonenum"));
		
		try{if(stmt!=null){stmt.close();}}catch(Exception e){}
		try{if(rs!=null){rs.close();}}catch(Exception e){}
		
		return member;
	}
	
	
	public int update(Member member) throws Exception{
		PreparedStatement Preparestmt = null;
		connection = ds.getConnection();
		try{
//			System.out.println(member.getNo());
//			System.out.println(member.getName());
//			System.out.println(member.getEmail());
//			System.out.println(member.getHeight());
//			System.out.println(member.getPhonenum());
			String sql = "UPDATE study SET name=?, height=?, phonenum=?, email=? WHERE mno = ? ";
			Preparestmt = connection.prepareStatement(sql);
			Preparestmt.setString(1, member.getName());
			Preparestmt.setString(2, member.getHeight());
			Preparestmt.setString(3, member.getPhonenum());
			Preparestmt.setString(4, member.getEmail());
			Preparestmt.setString(5, member.getNo());
			
			Preparestmt.executeUpdate();
			
			return 1;
		}catch (Exception e){
			return -1;
		}finally{
			try{if(Preparestmt!=null){Preparestmt.close();}}catch(Exception e){}
		}
	}
	
	
	public int delete (int no){
		Statement stmt = null;
		
		try{
			connection = ds.getConnection();
			stmt = connection.createStatement();
			String sql = "delete from study where mno = '" + String.valueOf(no) +"'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			return 1;	
		}catch(Exception e){
			return -1;
		}finally{
			try{if(stmt!=null){stmt.close();}}catch(Exception e){}
		}
		
	}
	
	public Member exist(String email){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			connection = ds.getConnection();
			String sql = "SELECT *  from study where email=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				Member member = new Member().setEmail(rs.getString("email")).setHeight(rs.getString("height"))
						.setName(rs.getString("name")).setNo(rs.getString("mno")).setPhonenum(rs.getString("phonenum"));
				return member;
			} else {
				return null;
			}
		}catch(Exception e){
			
		}finally {
			try{if(stmt!=null){stmt.close();}}catch(Exception e){}
			try{if(rs!=null){rs.close();}}catch(Exception e){}
		}
		return null;
	}

}
