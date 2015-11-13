package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;


@Component("/member/update.do")
public class MemberUpdateController implements Controller, DataBinding{
	MemberDao memberDao;
	
	public MemberUpdateController setMemberDao (MemberDao memberDao){
		this.memberDao = memberDao;
		return this;
	}
		
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		
		if(model.get("no") != null){
			int no = (Integer)model.get("no") ;
			Member member = memberDao.selectOne(no);
			memberDao.update(member);
			model.put("member", member);
			return "/member/MemberUpdateForm.jsp";
		} else {
			return "redirect:list.do";
		}
	}

	@Override
	public Object[] getDataBinders() {
		// TODO Auto-generated method stub
		return new Object[] {
			"no" , Integer.class , "member" , spms.vo.Member.class	
		};
	}

}
