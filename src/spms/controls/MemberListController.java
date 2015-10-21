package spms.controls;

import java.util.Map;

import spms.dao.MemberDao;

public class MemberListController implements Controller{

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberListController");
		MemberDao memberDao = (MemberDao)model.get("memberDao");
		System.out.println("memberdao");
		model.put("members", memberDao.selectList());
		System.out.println("ListController Finish");
		return "/member/MemberList.jsp";
	}

}
