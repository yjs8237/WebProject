package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.annotation.Component;
import spms.dao.MemberDao;
import spms.vo.Member;

@Component("/auth/logout.do")
public class LogOutController implements Controller{

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		if (model.get("session") != null) {

			HttpSession session = (HttpSession) model.get("session");
			session.invalidate();

			System.out.println(" 로그아웃 성공 ");
			return "redirect:../auth/login.do";

		} else {
			System.out.println("로그인 되어 있는 Session 이 없습니다.");
			return "redirect:../auth/login.do";
		}

	}

}
