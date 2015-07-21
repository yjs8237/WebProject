package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



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
			/*
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("username"), sc.getInitParameter("password"));
			*/
			/*
			Class.forName(this.getInitParameter("driver"));
			conn = DriverManager.getConnection(this.getInitParameter("url"), this.getInitParameter("username"),
					this.getInitParameter("password") );
			*/
			
			conn = (Connection)sc.getAttribute("conn");
			
			stmt = conn.createStatement();
			String number = request.getParameter("no");
			System.out.println(number);
			String sql = "select * from study where mno = '" + number +"'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			rs.next();
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter out = response.getWriter();
			
			out.println("<html><head><title>ȸ������</title></head>");
			out.println("<body><h1>List</h1>");
			out.println("<form action='update' method='post'>");
			out.println("get���� ���� num : <input type='text' name='mno' value='"+request.getParameter("no")+"' readonly><br>");
			out.println("�̸� : <input type='text' name='name' value='"+rs.getString("name")+"'readonly'><br>");
			out.println("�ڵ��� : <input type='text' name='phonenum' value='"+rs.getString("phonenum")+"'readonly'><br>");
			out.println("Ű : <input type='text' name='height' value='"+rs.getString("height")+"'readonly'><br>");
			out.println("<input type='submit' value='����'>");
			out.println("<input type='button' value='���' onclick='location.href=\"list\"'>");
			out.println("</from>");
			out.println("</body></html>");
			
		}catch(Exception e){
			System.out.println(e.toString());
		}finally {
			try {
				if(conn!=null){conn.close();}
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
		// PostGre �� ���ڵ� Ÿ�԰� ���缭 euc-kr �� ��������..
		request.setCharacterEncoding("euc-kr");
		try{
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("username"), sc.getInitParameter("password"));
			
			System.out.println(request.getParameter("name"));
			System.out.println(request.getParameter("phonenum"));
			System.out.println(request.getParameter("height"));
			System.out.println(request.getParameter("email"));
			System.out.println(request.getParameter("mno"));
			
			String sql = "UPDATE study SET name=?, height=?, phonenum=?, email=?, mno=? WHERE mno = ? ";
			Preparestmt = conn.prepareStatement(sql);
			Preparestmt.setString(1, request.getParameter("name"));
			Preparestmt.setString(2, request.getParameter("height"));
			Preparestmt.setString(3, request.getParameter("phonenum"));
			Preparestmt.setString(4, request.getParameter("email"));
			Preparestmt.setString(5, request.getParameter("mno"));
			Preparestmt.setString(6, request.getParameter("mno"));
			Preparestmt.executeUpdate();
			// �۾������ ������� �ʰ� �ٸ� ������ ���
			response.sendRedirect("list");
			
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
	}
	
	
	
}
