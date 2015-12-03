package spms.listeners;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.apache.ibatis.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
			appContext = new ApplicationContext();
			
			String resource = "spms/dao/mybatis-config.xml";
			InputStream inputstream = Resources.getResourceAsStream(resource);
			
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
			
			appContext.addBean("sqlSessionFactory", sqlSessionFactory);
			
			ServletContext sc = Evt.getServletContext();
			
			String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
			
			appContext.prepareObjectsByProperties(propertiesPath);
			appContext.prepareObjectsByAnnotation("");
			appContext.injectDependency();
			
			
			/*
			ServletContext sc = Evt.getServletContext();
			String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
			System.out.println("App 최초 구동");
			System.out.println("propertiesPath -> " + propertiesPath);
			appContext = new ApplicationContext(propertiesPath);
			*/
			
			
		} catch (Exception e){
			
		} finally {
			
		}
	
		
		
	}

}
