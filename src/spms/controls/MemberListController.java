package spms.controls;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;


@Component("/member/list.do")
public class MemberListController implements Controller, DataBinding{

	MemberDao memberDao;
	public MemberListController setMemberDao (MemberDao memberDao){
		this.memberDao = memberDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
			"orderCond" , String.class	
		};
	}
	
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		
		HashMap<String,Object>paramMap = new HashMap<String,Object>();
		paramMap.put("orderCond", model.get("orderCond"));
		
		model.put("members", memberDao.selectList(paramMap));
		
		HttpSession session = (HttpSession)model.get("session");
		
		if(session!=null){
			Member member = (Member)session.getAttribute("member");
//			System.out.println(member.getName());
		}else {
			System.out.println("## Session is Null ## ");
		}
		
		return "/member/MemberList.jsp";
	}

}
