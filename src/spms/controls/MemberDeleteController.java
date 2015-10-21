package spms.controls;

import java.util.Map;

import spms.dao.MemberDao;

public class MemberDeleteController implements Controller{

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		if(model.get("Number") != null){
			MemberDao memberDao = (MemberDao) model.get("memberDao");
			memberDao.delete(Integer.parseInt((String)model.get("Number")));
			return "redirect:list.do";
		} else {
			return "redirect:list.do";
		}
		
	}

}
