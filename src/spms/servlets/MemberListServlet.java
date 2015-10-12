package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;


@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet{
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			ServletContext sc = this.getServletContext();
			
			
			// InitAppServlet 에서 DB 커넥션 객체 연결
//			conn = (Connection)sc.getAttribute("conn");
//			MemberDao memberDao = new MemberDao();
			
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			// ContextLoaderListener 클래스에서 Connection 처리
//			memberDao.setConnection(conn);
			
		
			// request 에 회원목록 데이터를 보관한다.
			request.setAttribute("members", memberDao.selectList());
			response.setContentType("text/html; charset=utf-8");
			// JSP 로 출력을 위임한다.
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(request, response);
			
		}catch(Exception e){
			System.out.println(e.toString());
			request.setAttribute("error", e.toString()); 
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.include(request, response);
		}finally {
			try {
				if(stmt!=null){stmt.close();}
				if(rs!=null){rs.close();}
			}catch(Exception e){
			
			}
		}
	}
	/*
	@Override
	public void (ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
	}
*/
}
