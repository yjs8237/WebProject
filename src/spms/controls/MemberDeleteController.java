package spms.controls;

import java.util.Map;

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

public class MemberDeleteController implements Controller, DataBinding{

	MemberDao memberDao;
	
	public MemberDeleteController setMemberDao (MemberDao memberDao){
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		Member member = (Member)model.get("deleteInfo");
		if(member != null){
			memberDao.delete(Integer.parseInt(member.getNo()));
			return "redirect:list.do";
		} else {
			return "redirect:list.do";
		}
		
	}

	@Override
	public Object[] getDataBinders() {
		// TODO Auto-generated method stub
		return new Object[] {
			"no" , Integer.class
		};
	}

	

}
