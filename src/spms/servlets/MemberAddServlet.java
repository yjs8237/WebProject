package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet{
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(request, response);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		RequestDispatcher rd = request.getRequestDispatcher("MemberForm.jsp");
		rd.forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		request.setCharacterEncoding("utf-8");
		try{
			
			ServletContext sc = this.getServletContext();

			
			/*
			Member member= new Member();
			member.setName(request.getParameter("name"));
			member.setHeight(request.getParameter("height"));
			member.setPhonenum(request.getParameter("number"));
			member.setEmail(request.getParameter("email"));
			member.setNo(request.getParameter("mno"));
			*/
			// InitAppServlet ���� DB Ŀ�ؼ� ��ü ����
//			conn = (Connection)sc.getAttribute("conn");
			
			
//			MemberDao memberDao = new MemberDao();
//			memberDao.setConnection(conn);
			
			Member member = (Member) request.getAttribute("member");
			
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			memberDao.insert(member);
			
			request.setAttribute("viewUrl", "redirect:list.do");
			
			
			// �۾������ ������� �ʰ� �ٸ� ������ ���
//			response.sendRedirect("list");
			
			
			// 1���� /member url ȣ��
//			response.addHeader("Refresh", "1;url=../member");
			
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
		
}
