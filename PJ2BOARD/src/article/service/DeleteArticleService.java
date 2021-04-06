package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;
import member.service.InvalidPasswordException;

public class DeleteArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	private MemberDao memberDao = new MemberDao();
	
	public void delete(DeleteRequest delReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			// 게시글 번호에 해당하는 Article 객체를 구한다.
			Article article = articleDao.selectById(conn, 
					delReq.getArticleNumber());
			
			// 해당하는 유저의 비밀번호를 가져옴
			Member password = memberDao.selectId(conn, delReq.getUserId());
			
			// 해당 번호를 가진 게시글이 존재하지 않으면 익셉션을 발생시킨다.
			if (article == null) {
				throw new ArticleNotFoundException();
			}
			// 수정하려는 사용자가 해당 게시글을 수정할 수 있는지 검사
			if (!canModify(delReq.getUserId(), article)) {
				throw new PermissionDeniedException();
			}
			
			// 유저의 비밀번호와 입력된 비밀번호가 같지 않으면 예외처리(비밀번호 일치 검사)
			if(!matchPw(delReq.getPassword(), password)) {
				throw new InvalidPasswordException();
			}
			// ArticleDao와 ArticleContentDao의 delete() 메서드를 이용해서 게시물을 삭제한다.
			articleDao.delete(conn, 
					delReq.getArticleNumber());
			contentDao.delete(conn, 
					delReq.getArticleNumber());
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	// canModify 게시글을 삭제할 수 있는지 검사하는 기능을 구현한다.
	// 삭제하려는 사용자 ID가 게시글 작성자 ID와 동일하면 true를 리턴한다.
	private boolean canModify(String modfyingUserId, Article article) {
		return article.getWriter().getId().equals(modfyingUserId);
	}
	
	private boolean matchPw(String matchingPw, Member password) {
		return password.getPassword().equals(matchingPw);
	}
	
	

}
