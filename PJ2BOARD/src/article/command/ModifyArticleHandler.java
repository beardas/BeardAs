package article.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.ModifyArticleService;
import article.service.ModifyRequest;
import article.service.PermissionDeniedException;
import article.service.ReadArticleService;
import auth.service.User;
import mvc.command.CommandHandler;

public class ModifyArticleHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/modifyForm.jsp";

	private ReadArticleService readService = new ReadArticleService();
	private ModifyArticleService modifyService = new ModifyArticleService();
	
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
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			
			ArticleData articleData = readService.getArticle(no, false);
			User authUser = (User)req.getSession().getAttribute("user");
			// 현재 로그인한 사용자가 게시글의 작성자가 아니면 
			if(!canModify(authUser, articleData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN); // 403 응답 전송(서버 응답 실행 거부)
				return null;
			}
			ModifyRequest modReq = new ModifyRequest(authUser.getId(), no,
					articleData.getArticle().getTitle(), articleData.getContent());
			
			req.setAttribute("modReq", modReq);
			
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
		// 게시글 수정을 요청한 사용자 정보를 구한다.
		User authUser = (User) req.getSession().getAttribute("user");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		// 요청 파라미터와 함께 현재 사용자 정보를 이용해서 ModifyRequest 객체를 생성
		ModifyRequest modReq = new ModifyRequest(authUser.getId(), no,
				req.getParameter("title"),
				req.getParameter("content"));
		// ModifyRequest 객체를 request을 modReq 속성에 저장
		req.setAttribute("modReq", modReq);
		
		// 에러 정보를 담을 맵 객체를 생성하고,
		Map<String, Boolean> errors = new HashMap<>();
		// 객체가 유효한지 검사
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		// 유효성 검사에서 유효하지 않은 데이터가 존재한다면
		if (!errors.isEmpty()) {
			// 다시 폼을 보여줌
			return FORM_VIEW;
		}
		try {
			// 게시글 수정 기능을 실행한다.
			modifyService.modify(modReq);
			return "/WEB-INF/view/modifySuccess.jsp";
			// 게시글을 찾을수 없으면 404(요청한 자원이 존재하지 않음)
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
			// 권한이 없는 경우 403(서버 응답 실행 거부)
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
