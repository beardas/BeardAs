package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

public class LogoutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// getSession(false) -> 이미 세션이 있다면 그 세션을 돌려주고, 세션이 없으면 null을 반환
		HttpSession session = req.getSession(false);
		
		if(session != null) { // 세션이 존재하면 세션 제거
			session.invalidate();
		}
		
		res.sendRedirect(req.getContextPath() + "/index.jsp"); // 메인화면으로 돌아가기
		return null;
	}
}
