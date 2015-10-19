package spms.listeners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import spms.dao.MemberDao;
import spms.util.DBConnectionPool;

// 리스너 등록 어노테이션
@WebListener
public class ContextLoaderListener implements ServletContextListener{
//	Connection conn;
//	DBConnectionPool connPool;
//	BasicDataSource ds;
	
	@Override
	public void contextDestroyed(ServletContextEvent Evt) {
		// TODO Auto-generated method stub
		try {
//			connPool.closeAll();
//			ds.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent Evt) {
		// TODO Auto-generated method stub
		try{
			ServletContext sc = Evt.getServletContext();
			
			InitialContext initialContext = new InitialContext();
			// 톰캣 서버 context.xml 파일에 설정 정보를 읽어온다.
			DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/studydb");
			
//			Class.forName(sc.getInitParameter("driver"));
//			conn = DriverManager.getConnection(
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password")
//			);
			
//			connPool = new DBConnectionPool(
//					sc.getInitParameter("url"), 
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password"),
//					sc.getInitParameter("driver")
//					);
			
//			ds = new BasicDataSource();
//			ds.setDriverClassName(sc.getInitParameter("driver"));
//			ds.setUrl(sc.getInitParameter("url"));
//			ds.setUsername(sc.getInitParameter("username"));
//			ds.setPassword(sc.getInitParameter("password"));
			
			MemberDao memberDao = new MemberDao();
			memberDao.setDataSource(ds);

//			memberDao.setDBConnectionPool(connPool);
//			memberDao.setConnection(conn);
			
			sc.setAttribute("memberDao", memberDao);
			
		} catch (Exception e){
			
		} finally {
			
		}
	
		
		
	}

}
