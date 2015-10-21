package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;


@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet{
	private Connection conn;
	private PreparedStatement Preparestmt;
	private Statement stmt;
	private ResultSet rs;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			ServletContext sc = this.getServletContext();
			
			String number = request.getParameter("no");
			
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			Member member = memberDao.selectOne(Integer.parseInt(number));
			
			System.out.println("update view");
			
			/*
			request.setAttribute("member", member);
			request.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");
			*/
			/*
			RequestDispatcher rd = request.getRequestDispatcher("MemberUpdateForm.jsp");
			rd.forward(request, response);
			*/
		}catch(Exception e){
			System.out.println(e.toString());
			request.setAttribute("error", e.toString());
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.include(request, response);
		} finally {
			try {
				if(stmt!=null){stmt.close();}
				if(rs!=null){rs.close();}
			}catch(Exception e) {
				System.out.println(e.toString());
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc = this.getServletContext();
		// PostGre 의 인코딩 타입과 맞춰서 euc-kr 로 셋팅했음..
//		request.setCharacterEncoding("euc-kr");
		try{
			
//			conn = (Connection)sc.getAttribute("conn");
			
			System.out.println(request.getParameter("name"));
			System.out.println(request.getParameter("phonenum"));
			System.out.println(request.getParameter("height"));
			System.out.println(request.getParameter("email"));
			System.out.println(request.getParameter("mno"));
			
			
			Member member = new Member();
			
			member.setNo(request.getParameter("mno")).setName(request.getParameter("name")).setPhonenum(request.getParameter("phonenum"))
			.setHeight(request.getParameter("height")).setEmail(request.getParameter("email"));
			
			/*
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			*/
			
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			memberDao.update(member);
			
			
			request.setAttribute("viewUrl", "redirect:list.do");
			// 작업결과를 출력하지 않고 다른 페이지 출력
//			response.sendRedirect("list");
			
		}catch(Exception e){
			System.out.println( e.toString());
			request.setAttribute("error", e.toString());
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.include(request, response);
		}
		
	}
	
	
	
}
