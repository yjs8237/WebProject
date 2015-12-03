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

import spms.bind.DataBinding;
import spms.bind.ServletrequestDataBinder;
import spms.context.ApplicationContext;
import spms.controls.Controller;
import spms.controls.LogInController;
import spms.controls.LogOutController;
import spms.controls.MemberAddController;
import spms.controls.MemberDeleteController;
import spms.controls.MemberListController;
import spms.controls.MemberUpdateController;
import spms.listeners.ContextLoaderListener;
import spms.vo.Member;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		// TODO Auto-generated method stub
//		ServletContext sc = this.getServletContext();
		ApplicationContext ctx = ContextLoaderListener.getApplicationContext();
		
		response.setContentType("text/html; charset=UTF-8"); 
		
		String servletPath = request.getServletPath();
		  
		HashMap<String, Object> model = new HashMap<String, Object>();
		model.put("session", request.getSession());
		
		System.out.println("servletPath -> " + servletPath);
		Controller pageController = (Controller) ctx.getBean(servletPath);
		
		try{
			if(pageController instanceof DataBinding){
				prepareRequestData(request , model, (DataBinding)pageController);
			}
			
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
			e.printStackTrace();
			System.out.println("Exception " + e.toString());
			request.setAttribute("error", e.toString()); 
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.include(request, response);
		}  
		 
	}   
	
	private void prepareRequestData(HttpServletRequest request , HashMap<String, Object> model, DataBinding dataBinding) throws Exception{
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = ""; 
		Class<?> dataType = null; 
		Object dataObj = null; 
		 
		for (int i = 0; i < dataBinders.length; i+=2) { 
			dataName = dataBinders[i].toString();
			dataType = (Class<?>)dataBinders[i+1];
			dataObj = ServletrequestDataBinder.bind(request, dataType , dataName);
			System.out.println("dataName : " + dataName +" , dataObj : " + dataObj);
			model.put(dataName, dataObj);
		}
	}
	
	
}
