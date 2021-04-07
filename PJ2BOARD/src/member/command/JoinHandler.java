package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicatedIdException;
import member.service.JoinRequest;
import member.service.JoinService;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/joinForm.jsp";
	private JoinService joinService = new JoinService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			// 405 (요청 방법(get, post)이 서버에 의해 알려졌으나, 사용 불가능한 상태임)
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}	
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		JoinRequest joinReq = new JoinRequest();
		joinReq.setId(req.getParameter("id"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPassword(req.getParameter("password"));
		joinReq.setPassword2(req.getParameter("password2"));
		joinReq.setEmail(req.getParameter("email"));
		joinReq.setPhone(req.getParameter("phone"));
		
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		joinReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {	// 회원가입 메소드 실행 -> joinSuccess.jsp로 이동
			joinService.join(joinReq);
			return "WEB-INF/view/joinSuccess.jsp";
		} catch (DuplicatedIdException e) {
			// 아이디 중복일 경우 duplicateId 에러 발생
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
	
}