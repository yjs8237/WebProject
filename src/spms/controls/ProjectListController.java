package spms.controls;

import java.util.HashMap;
import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;

@Component("/project/list.do")
public class ProjectListController implements Controller, DataBinding{
	
	ProjectDao projectDao;
	
	public ProjectListController setProjectDao(ProjectDao projectDao){
		this.projectDao = projectDao;
		return this;
	}
	
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,Object>paramMap = new HashMap<String,Object>();
		
		paramMap.put("orderCond", model.get("orderCond"));
		
		model.put("projects", projectDao.selectList(paramMap));
		return "/project/ProjectList.jsp";
	}

	@Override
	public Object[] getDataBinders() {
		// TODO Auto-generated method stub
		return new Object[] {
			"orderCond" , String.class
		};
	}
	
	
}
