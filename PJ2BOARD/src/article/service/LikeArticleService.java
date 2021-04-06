package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleDao;
import article.model.Article;
import jdbc.connection.ConnectionProvider;

public class LikeArticleService {

	private ArticleDao articleDao = new ArticleDao();
	
	public void likeArticle(int articleNum, boolean likes) {
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = articleDao.selectById(conn, articleNum);
			
			if (article == null) {
				throw new ArticleNotFoundException();
			}
			
			// likes가 true이면 조회수를 증가시킴
			if (likes) {
				articleDao.increaseLikeCount(conn, articleNum);
				conn.commit();
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
