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

import spms.context.ApplicationContext;
import spms.controls.LogInController;
import spms.controls.LogOutController;
import spms.controls.MemberAddController;
import spms.controls.MemberDeleteController;
import spms.controls.MemberListController;
import spms.controls.MemberUpdateController;
import spms.dao.MemberDao;
import spms.dao.PostgreMemberDao;
import spms.util.DBConnectionPool;

// 리스너 등록 어노테이션
@WebListener
public class ContextLoaderListener implements ServletContextListener{
//	Connection conn;
//	DBConnectionPool connPool;
//	BasicDataSource ds;
	
	private static ApplicationContext appContext;
	
	
	public static ApplicationContext getApplicationContext () {
		return appContext;
	}
	
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
			
			String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
			System.out.println("App 최초 구동");
			System.out.println("propertiesPath -> " + propertiesPath);
			appContext = new ApplicationContext(propertiesPath);
			System.out.println("appContext is null ? " + appContext == null);
			
			/*
			InitialContext initialContext = new InitialContext();
			// 톰캣 서버 context.xml 파일에 설정 정보를 읽어온다.
			DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/studydb");
			
//			MemberDao memberDao = new MemberDao();
			PostgreMemberDao memberDao = new PostgreMemberDao();
			memberDao.setDataSource(ds);
			
//			sc.setAttribute("memberDao", memberDao);
			sc.setAttribute("/auth/login.do", new LogInController().setMemberDao(memberDao));
			sc.setAttribute("/auth/logout.do", new LogOutController());
			sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(memberDao));
			sc.setAttribute("/member/add.do", new MemberAddController().setMemberDao(memberDao));
			sc.setAttribute("/member/update.do", new MemberUpdateController().setMemberDao(memberDao));
			sc.setAttribute("/member/delete.do", new MemberDeleteController().setMemberDao(memberDao));
			*/
			
			
			
		} catch (Exception e){
			
		} finally {
			
		}
	
		
		
	}

}
