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

			System.out.println(" �α׾ƿ� ���� ");
			return "redirect:../auth/login.do";

		} else {
			System.out.println("�α��� �Ǿ� �ִ� Session �� �����ϴ�.");
			return "redirect:../auth/login.do";
		}

	}

}
