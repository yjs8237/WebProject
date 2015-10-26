package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;

public class MemberListController implements Controller{

	MemberDao memberDao;
	public MemberListController setMemberDao (MemberDao memberDao){
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		
		
		model.put("members", memberDao.selectList());
		
		HttpSession session = (HttpSession)model.get("session");
		if(session!=null){
			Member member = (Member)session.getAttribute("member");
			System.out.println(member.getName());
		}else {
			System.out.println("## Session is Null ## ");
		}
		
		return "/member/MemberList.jsp";
	}

}
