package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleContentNotFoundException;
import article.service.ArticleNotFoundException;
import article.service.LikeArticleService;
import mvc.command.CommandHandler;

public class LikeArticleHandler implements CommandHandler {

	private LikeArticleService likeService = new LikeArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		Boolean likes = false;
		
		if(likeTF(req.getParameter("true"))) {
			likes = true;
		} else if (likeTF(req.getParameter("false"))) {
			likes = false;
		} 
		
		String noVal = req.getParameter("no");
		int articleNum = Integer.parseInt(noVal);
		try {
			likeService.likeArticle(articleNum, likes);
			return "/WEB-INF/view/listArticle.jsp";
		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
			req.getServletContext().log("no log", e);
			return null;
		}
	}
	
	private Boolean likeTF(String likes) {
		if(likes.equalsIgnoreCase("true")) {
			return true;
		} else if(likes.equalsIgnoreCase("false")) {
			return false;
		} else {
			return null;
		}
	}
	
		
	
}
