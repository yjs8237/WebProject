package spms.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;




import spms.annotation.Component;
import spms.util.DBConnectionPool;
import spms.vo.Member;
import spms.vo.Project;


@Component("memberDao")
public class PostgreMemberDao implements MemberDao{
	
	
	SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory (SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public List<Member> selectList(HashMap paramMap) throws Exception {
		
		if(sqlSessionFactory == null){
			System.out.println("sqlSessionFactory is null");
		}
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectList("spms.dao.MemberDao.selectList", paramMap);
		}finally {
			sqlSession.close();
		}
	}
	
	public int insert(Member member) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			int count = sqlSession.insert("spms.dao.MemberDao.insert" , member);
			sqlSession.commit();
			return count;
		}finally {
			sqlSession.close();
		}
		
	}
	
	
	public Member selectOne(int no) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectOne("spms.dao.MemberDao.selectOne" , no);
		} finally {
			sqlSession.close();
		}
	}
	
	
	public int update(Member member) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			
			Member original = sqlSession.selectOne("spms.dao.MemberDao.selectOne" , member.getNo());
			Hashtable <String, Object> paramMap = new Hashtable<String,Object>();
			
			// DB 에 있는 데이터와 변경하려는 데이터가 같지 않을때만 UPDATE Hash에 추가한다.
			if(!member.getPhonenum().equals(original.getPhonenum())){
				paramMap.put("phonenum", member.getPhonenum());
			}
			if(!member.getName().equals(original.getName())){
				paramMap.put("name", member.getName());
			}
			if(!member.getHeight().equals(original.getHeight())){
				paramMap.put("height", member.getHeight());
			}
			if(!member.getEmail().equals(original.getEmail())){
				paramMap.put("email", member.getEmail());
			}
			
			
			// Update 할 데이터가 존재할 경우만 Update 한다.
			if(paramMap.size() > 0){
				paramMap.put("no", member.getNo());
				int count = sqlSession.update("spms.dao.MemberDao.update" , paramMap);
				sqlSession.commit();
				return count;
			} else {
				return 0;
			}
			
		}finally{
			sqlSession.close();
		}
	}
	
	
	public int delete (int no)throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			int count = sqlSession.delete("spms.dao.MemberDao.delete" , no);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	public Member exist(String email)throws Exception{
		
		if(email == null){
			return null;
		}
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Hashtable <String, Object> paramMap = new Hashtable<String,Object>();
		paramMap.put("email", email);
		try{
			return sqlSession.selectOne("spms.dao.MemberDao.exist" , paramMap);
		}finally {
			
		}
	}

}
