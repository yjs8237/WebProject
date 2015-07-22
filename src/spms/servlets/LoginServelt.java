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
		RequestDispatcher rd = request.getRequestDispatcher("/auth/LoginForm.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			stmt = conn.prepareStatement("SELECT *  from study where email=?");
			stmt.setString(1, request.getParameter("email"));
			rs = stmt.executeQuery();
			if(rs.next()){
				
				Member member = new Member().setEmail(rs.getString("email")).setHeight(rs.getString("height"))
						.setName(rs.getString("name")).setNo(rs.getString("mno")).setPhonenum(rs.getString("phonenum"));
				
				// Http 세션 객체를 가져온다.
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				
				//작업 결과를 리스트 화면으로 보낸다.
				response.sendRedirect("../member/list");
			} else {
				System.out.println("로그인 실패");
				RequestDispatcher rd = request.getRequestDispatcher("/auth/LoginFail.jsp");
				rd.forward(request, response);
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
