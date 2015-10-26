package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;

public class LogInController implements Controller{
	
	MemberDao memberDao;
	
	public LogInController setMemberDao (MemberDao memberDao){
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		if(model.get("email") != null){
			String email = (String) model.get("email");
			
			Member member = memberDao.exist(email);
			System.out.println("member = " + member);
			if(member != null){
				HttpSession session = (HttpSession) model.get("session");
				if(session == null){
					System.out.println("LogInController Session is null");
				}
				session.setAttribute("member", member);
				model.put("session", session);
				
				System.out.println("로그인 성공");
				return "redirect:../member/list.do";
			} else {
				System.out.println("E-mail 을 입력해주세요.");
				return "/auth/LoginForm.jsp";
			}
			
		} else {
			System.out.println("E-mail 을 입력해주세요.");
			return "/auth/LoginForm.jsp";
		}
		
	}

}
