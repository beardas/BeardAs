package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.MemberNotFoundException;
import member.service.UpdateService;
import mvc.command.CommandHandler;

public class UpdateMemberHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "WEB-INF/view/updateForm.jsp";
	private UpdateService updateService = new UpdateService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User)req.getSession().getAttribute("user");
		String name = trim(req.getParameter("name"));
		String email = trim(req.getParameter("email"));
		String phone = trim(req.getParameter("phone"));
		
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		if(name == null || name.isEmpty()) { // 이름 값이 없으면
			errors.put("name", Boolean.TRUE); // name에러 발생
		}
		
		if(email == null || email.isEmpty()) { // 이메일 값이 없으면
			errors.put("email", Boolean.TRUE); // email에러 발생
		}
		
		if(phone == null || phone.isEmpty()) { // 전화번호 값이 없으면
			errors.put("phone", Boolean.TRUE); // phone에러 발생
		}
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			// 정보변경 메소드 실행
			updateService.updateMember(authUser.getId(), name, email, phone);
			return "/WEB-INF/view/updateMemberSuccess.jsp";
		} catch (MemberNotFoundException e) {
			return null;
		}
	}
	
	private String trim(String str) { // 문자열이 null이면 null을 반환, 문자열이 값이 있으면 공백제거
		return str == null ? null : str.trim();
	}
	
}
