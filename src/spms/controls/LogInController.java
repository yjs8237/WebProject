package spms.controls;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;



@Component("/auth/login.do")
public class LogInController implements Controller, DataBinding{
	
	MemberDao memberDao;
	
	public LogInController setMemberDao (MemberDao memberDao){
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		if(model.get("loginInfo") != null){
			Member member = (Member) model.get("loginInfo");
			if(member == null){
				System.out.println("member is null");
			} 
			if(member.getEmail() == null){
				System.out.println("email is null");
			}
			
			member = memberDao.exist(member.getEmail());
			
			if(member != null){
				HttpSession session = (HttpSession) model.get("session");
				if(session == null){
					System.out.println("LogInController Session is null");
				}
				session.setAttribute("member", member);
//				model.put("session", session);
				
				System.out.println("로그인 성공");
				return "redirect:../member/list.do";
			} else {
				System.out.println("로그인 시도 !!");
				return "/auth/LoginForm.jsp";
			}
			
		} else {
			System.out.println("E-mail 을 입력해주세요.");
			return "/auth/LoginForm.jsp";
		}
		
	}

	@Override
	public Object[] getDataBinders() {
		// TODO Auto-generated method stub
		return new Object[] {
			"loginInfo" , spms.vo.Member.class
		};
	}


}
