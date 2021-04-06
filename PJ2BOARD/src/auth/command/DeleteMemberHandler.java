package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.DeleteMemberService;
import auth.service.InvalideIdException;
import auth.service.LoginFailException;
import mvc.command.CommandHandler;

public class DeleteMemberHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/deleteMemberForm.jsp";
	private DeleteMemberService deleteMemberService = new DeleteMemberService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	public String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	public String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String id = trim(req.getParameter("id"));
		String pw = trim(req.getParameter("password"));
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		if(id == null || id.isEmpty()) { // 아이디 값이 없으면
			errors.put("id", Boolean.TRUE); // id에러 발생
		}
		
		if(pw == null || pw.isEmpty()) { // 비밀번호 값이 없으면
			errors.put("password", Boolean.TRUE); // password에러 발생
		}
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			deleteMemberService.delete(id, pw); // 회원탈퇴 메서드 실행
			return "/WEB-INF/view/deleteMemberSuccess.jsp";
		} catch (InvalideIdException e) {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
	
	private String trim(String str) { // 문자열이 null이면 null을 반환, 문자열이 값이 있으면 공백제거
		return str == null ? null : str.trim();
	}
}
