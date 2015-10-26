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
import javax.servlet.http.HttpSession;

import spms.controls.Controller;
import spms.controls.LogInController;
import spms.controls.LogOutController;
import spms.controls.MemberAddController;
import spms.controls.MemberDeleteController;
import spms.controls.MemberListController;
import spms.controls.MemberUpdateController;
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
		
		HttpSession session = (HttpSession)request.getSession();
		
		if(session.getAttribute("member") != null){
			model.put("session", session);
		}
		
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
				pageController = new MemberUpdateController();
				if(request.getParameter("no") != null) {
					System.out.println("The Number to update : " + request.getParameter("no"));
					model.put("Number",request.getParameter("no"));
				}  
				
			} else if(servletPath.equals("/member/delete.do")) {
				pageController = new MemberDeleteController(); 
				if(request.getParameter("no") != null) {
					System.out.println("The Number to delete : " + request.getParameter("no"));
					model.put("Number",request.getParameter("no"));
				}
			} else if(servletPath.equals("/auth/login.do")) {  
				pageController = new LogInController();
				if(request.getParameter("email") != null){
					System.out.println(request.getParameter("email"));
					model.put("session" , request.getSession()); 
					model.put("email", request.getParameter("email"));
				} 
			} else if(servletPath.equals("/auth/logout.do")) {
				pageController = new LogOutController();
			}
			
			
			/*
				RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
				System.out.println("path : " + pageControllerPath);
				rd.include(request, response);
				 
				String viewUrl = (String) request.getAttribute("viewUrl");
			*/
			
			String viewUrl = pageController.excute(model);
			System.out.println("Return URL : " + viewUrl);
			
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
			e.printStackTrace();
			System.out.println("Exception " + e.toString());
			request.setAttribute("error", e.toString()); 
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.include(request, response);
		}
		
		
//		super.service(arg0, arg1);
	}
	
}
