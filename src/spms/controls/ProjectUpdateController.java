package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import spms.vo.Project;

@Component("/project/update.do")
public class ProjectUpdateController implements Controller, DataBinding{
	
	ProjectDao projectDao;
	
	public ProjectUpdateController setProjectDao (ProjectDao projectDao){
		this.projectDao = projectDao;
		return this;
	}
	
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
	    Project project = (Project)model.get("project");
	    if (project.getTitle() == null) {
	    	int no = (Integer)(model.get("no"));
	    	project = projectDao.selectOne(no);
	    	model.put("project", project);
	    	
	    	return "/project/ProjectUpdateForm.jsp";
	      
	    } else {
	      projectDao.update(project);
	      return "redirect:list.do";
	    }
	}

	 public Object[] getDataBinders() {
		    return new Object[]{
		        "no", Integer.class,
		        "project", spms.vo.Project.class
		    };
	 }

}
