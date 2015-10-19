package spms.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.controls.Controller;
import spms.controls.MemberAddController;
import spms.controls.MemberListController;
import spms.vo.Member;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc = this.getServletContext();
		response.setContentType("text/html; charset=UTF-8");
		
		String servletPath = request.getServletPath();
		
		HashMap<String, Object> model = new HashMap<String, Object>();
		model.put("memberDao", sc.getAttribute("memberDao"));
		
		
		try{
			
			String pageControllerPath = null;
			Controller pageController = null;
			
			
			if(servletPath.equals("/member/list.do")){
//				pageControllerPath = "/member/list";
				pageController = new MemberListController();
				
			} else if(servletPath.equals("/member/add.do")){
//				pageControllerPath = "/member/add";
				pageController = new MemberAddController();
				
				if(request.getParameter("email") != null){
					model.put("member", new Member().setEmail(request.getParameter("email"))
							.setNo(request.getParameter("mno"))
							.setName(request.getParameter("name"))
							.setPhonenum(request.getParameter("number"))
							.setHeight(request.getParameter("height"))
							);
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
			
			
			/*
			RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
			System.out.println("path : " + pageControllerPath);
			rd.include(request, response);
			
			String viewUrl = (String) request.getAttribute("viewUrl");
			*/
			
			String viewUrl = pageController.excute(model);
			
			
			for (String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			
			if(viewUrl.startsWith("redirect:")){
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
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
