package spms.servlets;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AppInitServlet extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Ready fot Servlet Init..");
		super.init(config);
		System.out.println("Discard Test");
		try{
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			Connection conn = DriverManager.getConnection(sc.getInitParameter("url") , 
					sc.getInitParameter("username") , sc.getInitParameter("password"));
			
			sc.setAttribute("conn", conn);
			
		}catch(Exception e){
			
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("AppInitServlet ¸¶¹«¸®..!!");
		super.destroy();
		
		Connection conn = (Connection)this.getServletContext().getAttribute("conn");
		
		try{
			if(conn != null || !conn.isClosed()){
				conn.close();
			}
		}catch (Exception e){
			
		}
	}
	
	

	
	
}
