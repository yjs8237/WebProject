package spms.controls;

import java.util.Map;

import spms.dao.MemberDao;
import spms.vo.Member;

public class MemberUpdateController implements Controller{

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		if(model.get("Number") != null){
			int no = Integer.parseInt((String)model.get("Number")) ;
			MemberDao memberDao = (MemberDao)model.get("memberDao");
			Member member = memberDao.selectOne(no);
			memberDao.update(member);
			model.put("member", member);
			return "/member/MemberUpdateForm.jsp";
		} else {
			return "redirect:list.do";
		}
	}

}
