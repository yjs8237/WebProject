package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/auth/login")
public class LoginServelt extends HttpServlet{
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Get 요청이 들어오면 로그인 jsp 페이지로 화면을 위임한다.
		request.setAttribute("viewUrl", "LoginForm.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("LoginForm.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			ServletContext sc = this.getServletContext();
//			conn = (Connection) sc.getAttribute("conn");
			
//			MemberDao memberDao = new MemberDao();
//			memberDao.setConnection(conn);
			
			
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			Member member = memberDao.exist(request.getParameter("email")); 
			
			if(member != null){
				// Http 세션 객체를 가져온다.
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				System.out.println("로그인 성공");
				request.setAttribute("viewUrl", "redirect:../member/list.do");
				//작업 결과를 리스트 화면으로 보낸다.
//				response.sendRedirect("../member/list");
			} else {
				System.out.println("로그인 실패");
				request.setAttribute("viewUrl", "/auth/LoginFail.jsp");
				
//				RequestDispatcher rd = request.getRequestDispatcher("/auth/LoginFail.jsp");
//				rd.forward(request, response);
			}
			
			
		}catch(Exception e){
			System.out.println(e.toString());
		} finally {
			try{
				if(stmt!=null){stmt.close();}
				if(rs!=null){rs.close();}
			}catch(Exception e){
				
			}
		}
		
	}
	
	
}
