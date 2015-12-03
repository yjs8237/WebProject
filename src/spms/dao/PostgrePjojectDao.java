package spms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.annotation.Component;
import spms.vo.Member;
import spms.vo.Project;

@Component("ProjectDao")
public class PostgrePjojectDao implements ProjectDao{
	
//	Connection connection;
//	DataSource ds;
	
	SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory (SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
//	public void setDataSource (DataSource dataSource){
//		this.ds = dataSource;
//	}
	
	@Override
	public List<Project> selectList(HashMap paramMap) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectList("spms.dao.ProjectDao.selectList", paramMap);
		}finally {
			sqlSession.close();
		}
	
	}
	
	public int insert(Project project) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			int count = sqlSession.insert("spms.dao.ProjectDao.insert" , project);
			sqlSession.commit();
			return count;
		}finally {
			sqlSession.close();
		}
		
	}
	
	public Project selectOne(int no) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectOne("spms.dao.ProjectDao.selectOne" , no);
		} finally {
			sqlSession.close();
		}
		
	}
	
	@Override
	public int update(Project project) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			
			Project original = sqlSession.selectOne("spms.dao.ProjectDao.selectOne" , project.getNo());
			Hashtable <String, Object> paramMap = new Hashtable<String,Object>();
			
			// DB 에 있는 데이터와 변경하려는 데이터가 같지 않을때만 UPDATE Hash에 추가한다.
			if(!project.getTitle().equals(original.getTitle())){
				paramMap.put("title", project.getTitle());
			}
			if(!project.getTags().equals(original.getTags())){
				paramMap.put("tags", project.getTags());
			}
			if(project.getState() != original.getState()){
				paramMap.put("state", project.getState());
			}
			if(!project.getStartDate().equals(original.getStartDate())){
				paramMap.put("startDate", project.getStartDate());
			}
			if(!project.getEndDate().equals(original.getEndDate())){
				paramMap.put("endDate", project.getEndDate());
			}
			if(!project.getContent().equals(original.getContent())){
				paramMap.put("content", project.getContent());
			}
			
			// Update 할 데이터가 존재할 경우만 Update 한다.
			if(paramMap.size() > 0){
				paramMap.put("no", project.getNo());
				int count = sqlSession.update("spms.dao.ProjectDao.update" , paramMap);
				sqlSession.commit();
				return count;
			} else {
				return 0;
			}
			
		}finally{
			sqlSession.close();
		}

	}

	public int delete(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			int count = sqlSession.delete("spms.dao.ProjectDao.delete" , no);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
		
	}
	

}
