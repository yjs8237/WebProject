package spms.controls;

import java.util.Map;

import spms.dao.MemberDao;
import spms.vo.Member;

public class MemberAddController implements Controller{
	MemberDao memberDao;
	
	public MemberAddController setMemberDao (MemberDao memberDao){
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		if(model.get("member") == null){
			return "/member/MemberForm.jsp";
		} else {
			Member member = (Member)model.get("member");
			memberDao.insert(member);
			return "redirect:list.do";
		}
	}

}
