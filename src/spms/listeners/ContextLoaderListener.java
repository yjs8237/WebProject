package spms.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent Evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent Evt) {
		// TODO Auto-generated method stub
		try{
			
		} catch (Exception e){
			
		} finally {
			
		}
	
		ServletContext sc = Evt.getServletContext();
		
	}

}
