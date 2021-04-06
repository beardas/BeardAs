package article.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.DeleteArticleService;
import article.service.DeleteRequest;
import article.service.ReadArticleService;
import auth.service.User;
import member.service.InvalidPasswordException;
import mvc.command.CommandHandler;

public class DeleteArticleHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/deleteForm.jsp";
	
	private DeleteArticleService deleteService = new DeleteArticleService();
	private ReadArticleService readService = new ReadArticleService();
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {						// 405 응답 코드 전송(허용되지 않는 메소드 응답)
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			User user = (User) req.getSession().getAttribute("user");
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			String pw = req.getParameter("password");
			
			ArticleData articleData = readService.getArticle(no, false);
			// 현재 로그인한 사용자가 게시글의 작성자가 아니면 
			if(!canModify(user, articleData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN); // 403 응답 전송(서버 응답 실행 거부)
				return null;
			}
			
			DeleteRequest delReq = new DeleteRequest(user.getId(), no, pw);
			
			
			req.setAttribute("delReq", delReq);
			
			return FORM_VIEW;
			
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 응답 코드 전송(요청한 자원이 존재하지 않음)
			return null;
		} 
	}
	
	private boolean canModify(User user, ArticleData articleData) {
		String writerId = articleData.getArticle().getWriter().getId();
		return user.getId().equals(writerId);
	}
				
	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		// 게시글 삭제를 요청한 사용자의 비밀번호, 유저 정보, 게시글 번호를 가져옴
		String password = trim(req.getParameter("password"));
		User user = (User) req.getSession().getAttribute("user");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		DeleteRequest delReq = new DeleteRequest(user.getId(), no, password);
		// DeleteRequest 객체를 request을 delReq 속성에 저장
		req.setAttribute("delReq", delReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if (password == null || password.isEmpty()) {
			errors.put("password", Boolean.TRUE);
		}
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			// 게시글 수정 기능을 실행한다.
			deleteService.delete(delReq);
			return "/WEB-INF/view/deleteSuccess.jsp";
		} catch (ArticleNotFoundException e) {
			// 게시글을 찾을수 없으면 404(요청한 자원이 존재하지 않음)
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (InvalidPasswordException e) {
			errors.put("PwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
	
	private String trim(String str) {
		return str == null ? null : str.trim();
	}
}
