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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		out.println("<html>");
		out.println("<head><title>Register</title></head>");
		out.println("<body><h1>ȸ�����</h1>");
		out.println("<form action='add' method='post'>");
		out.println("MNO : <input type='text' name='mno'><br>");
		out.println("�̸� : <input type='text' name='name'><br>");
		out.println("��ȣ : <input type='text' name='number'><br>");
		out.println("Ű : <input type='text' name='height'><br>");
		out.println("E-Mail : <input type='text' name='email'><br>");
		out.println("<input type='submit' value='�߰�'>");
		out.println("<input type='reset' value='���'>");
		out.println("</from>");
		out.println("</body></html>");	
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		request.setCharacterEncoding("utf-8");
		try{
			
			ServletContext sc = this.getServletContext();

			/*
			 * Context
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("username"), sc.getInitParameter("password"));
			
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sam", "postgres","jsyun0415" );
			*/
			
			
			// InitAppServlet ���� DB Ŀ�ؼ� ��ü ����
			conn = (Connection)sc.getAttribute("conn");
			System.out.println(request.getParameter("name"));
			System.out.println(request.getParameter("number"));
			System.out.println(request.getParameter("height"));
			System.out.println(request.getParameter("email"));
			System.out.println(request.getParameter("mno"));
			
			
			String sql = "insert into study (name , height , phonenum, email,mno) values (?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, request.getParameter("name"));
			stmt.setString(2, request.getParameter("height"));
			stmt.setString(3, request.getParameter("number"));
			stmt.setString(4, request.getParameter("email"));
			stmt.setString(5, request.getParameter("mno"));
			stmt.executeUpdate();
			// �۾������ ������� �ʰ� �ٸ� ������ ���
			response.sendRedirect("list");
			
			/*
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<html><head><title>���</title></head>");
			out.println("<body>");
			out.println("<p>����</p>");
			out.println("</body></html>");
			
			// 1���� /member url ȣ��
			response.addHeader("Refresh", "1;url=../member");
			*/
		}catch(Exception e){
			System.out.println(e.toString());
		}finally {
			try {
				System.out.println("do Finally");
				if(conn!=null){conn.close();}
				if(stmt!=null){stmt.close();}
				if(rs!=null){rs.close();}
			}catch(Exception e){
				
			}
		}
	}
		
}
