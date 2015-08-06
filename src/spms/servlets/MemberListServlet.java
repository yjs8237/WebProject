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
			
//			Class.forName(sc.getInitParameter("driver"));
//			conn = DriverManager.getConnection(sc.getInitParameter("url"),sc.getInitParameter("username"), sc.getInitParameter("password"));
			
			/*
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sam", "postgres","jsyun0415" );
			*/
			
			// InitAppServlet ���� DB Ŀ�ؼ� ��ü ����
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			/*
			stmt = conn.createStatement();
			String sql = "select * from study";
			
			rs = stmt.executeQuery(sql);
			
			response.setContentType("text/html; charset=euc-kr");
			ArrayList<Member>members = new ArrayList<Member>();
			while(rs.next()){
				// Memeber Ŭ�������� ü�̴� ����� �����ϱ� ���ؼ�, setter �޼ҵ忡�� this �ڽ��� ��ü�� �����Ѵ�.
				members.add(new Member().setNo(rs.getString("mno")).setName(rs.getString("name")).setHeight(rs.getString("height")).
						setPhonenum(rs.getString("phonenum")).setEmail(rs.getString("email"))
						);
			}
			*/
			
			// request �� ȸ����� �����͸� �����Ѵ�.
			request.setAttribute("members", memberDao.selectList());
			response.setContentType("text/html; charset=utf-8");
			// JSP �� ����� �����Ѵ�.
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(request, response);
			
			/*
			PrintWriter out = response.getWriter();
			
			out.println("<html><head><title>���</title></head>");
			out.println("<body><h1>List</h1>");
			out.println("<a href='add'>�ű�ȸ��</a><br>");
			
//			while(rs.next()){
//				System.out.println(rs.getString("name"));
//				System.out.println(rs.getString("phonenum"));
//				System.out.println(rs.getString("height"));
//			}
			
			while(rs.next()){
				out.println(rs.getString("name") + " , " + 
						"<a href='update?no=" + rs.getString("phonenum") + "'>"+rs.getString("height")+"</a>" +
						"<a href='delete?no=" + rs.getString("phonenum") + "'>[����]</a><br>");
			}
			out.println("</body></html>");
			*/
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
