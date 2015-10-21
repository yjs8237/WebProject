package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;

public class LogInController implements Controller{

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		if(model.get("email") != null){
			String email = (String) model.get("email");
			MemberDao memberDao = (MemberDao) model.get("memberDao");
			Member member = memberDao.exist(email);
			System.out.println("member = " + member);
			if(member != null){
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("member", member);
				System.out.println("로그인 성공");
			}
			return "redirect:../member/list.do";
		} else {
			System.out.println("E-mail 을 입력해주세요.");
			
			return "/auth/LoginForm.jsp";
		}
		
	}

}
