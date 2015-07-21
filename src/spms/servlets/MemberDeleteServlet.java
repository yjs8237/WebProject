package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet{
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			ServletContext sc = this.getServletContext();
			/*
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("username"), sc.getInitParameter("password"));
			*/
			/*
			Class.forName(this.getInitParameter("driver"));
			conn = DriverManager.getConnection(this.getInitParameter("url"), this.getInitParameter("username"),
					this.getInitParameter("password") );
			*/
			conn = (Connection) sc.getAttribute("conn");
			stmt = conn.createStatement();
			String number = request.getParameter("no");
			System.out.println(number);
			String sql = "delete from study where phonenum = '" + number +"'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
			response.sendRedirect("list");
			
			/*
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<html><head><title>결과</title></head>");
			out.println("<body>");
			out.println("<p>성공</p>");
			out.println("</body></html>");
			
			// 1초후 /member url 호출
			response.addHeader("Refresh", "1;url=list");
			*/
			
		}catch(Exception e){
			System.out.println(e.toString());
		}finally {
			try {
				if(conn!=null){conn.close();}
				if(stmt!=null){stmt.close();}
				if(rs!=null){rs.close();}
			}catch(Exception e){
				System.out.println(e.toString());
			}
		}
	}
}
