package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import spms.vo.Project;

@Component("/project/add.do")
public class ProjectAddController implements Controller, DataBinding{
	
	ProjectDao projectDao;
	
	public ProjectAddController setProjectDao (ProjectDao projectDao){
		this.projectDao = projectDao;
		return this;
	}
	
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
	    Project project = (Project)model.get("project");
	    if (project.getTitle() == null) {
	      return "/project/ProjectForm.jsp";
	      
	    } else {
	      projectDao.insert(project);
	      return "redirect:list.do";
	    }
	}

	@Override
	public Object[] getDataBinders() {
		// TODO Auto-generated method stub
		return new Object[] {
				"project" , spms.vo.Project.class
		};
	}

}
