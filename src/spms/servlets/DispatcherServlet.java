package spms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		
		String servletPath = request.getServletPath();
		
		try{
			
			String pageControllerPath = null;
			
			if(servletPath.equals("/member/list.do")){
				pageControllerPath = "/member/list";
				System.out.println("ddd");
			} else if(servletPath.equals("/member/add.do")){
				pageControllerPath = "/member/add";
				if(request.getParameter("email") != null){
					request.setAttribute("member", new Member()
					.setNo(request.getParameter("no"))
					.setEmail(request.getParameter("email"))
					.setName(request.getParameter("name")));
				}
			} else if(servletPath.equals("/member/update.do")){
				pageControllerPath = "/member/update";
				if(request.getParameter("email") != null){
					request.setAttribute("member", new Member()
					.setNo(request.getParameter("no"))
					.setEmail(request.getParameter("email"))
					.setName(request.getParameter("name")));
				}
			} else if(servletPath.equals("/member/delete.do")) {
				pageControllerPath = "/member/delete";
			} else if(servletPath.equals("/auth/login.do")) {
				pageControllerPath = "/auth/login";
			} else if(servletPath.equals("/auth/logout.do")) {
				pageControllerPath = "/auth/logout";
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
			System.out.println("path : " + pageControllerPath);
			rd.include(request, response);
			
			String viewUrl = (String) request.getAttribute("viewUrl");
			if(viewUrl.startsWith("redirect:")){
				System.out.println("redirect");
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				System.out.println(viewUrl);
				rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
			
		}catch(Exception e){
			System.out.println("Exception " + e.toString());
			request.setAttribute("error", e.toString()); 
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.include(request, response);
		}
		
		
//		super.service(arg0, arg1);
	}
	
}
