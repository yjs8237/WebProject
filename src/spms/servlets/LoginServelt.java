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

		// Get ��û�� ������ �α��� jsp �������� ȭ���� �����Ѵ�.
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
				
				// Http ���� ��ü�� �����´�.
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				
				//�۾� ����� ����Ʈ ȭ������ ������.
				response.sendRedirect("../member/list");
			} else {
				System.out.println("�α��� ����");
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
