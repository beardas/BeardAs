package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteArticleService {
	
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao articleContentDao = new ArticleContentDao();
	
	public Integer write(WriteRequest wrireq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			// WriteRequest에 입력된 게시글 정보를 가져옴
			Article article = toArticle(wrireq);
			// article 테이블에 해당 정보 insert
			Article savedArticle = articleDao.insert(conn, article);
			
			if(savedArticle == null) { // 테이블에 저장된  게시글이 존재하지 않을 경우
				throw new RuntimeException("fail to insert article");
			}
			
			// 저장된 게시글의 번호와 WriteRequest의 글 내용을 가져옴
			ArticleContent content = new ArticleContent(savedArticle.getNumber(), wrireq.getContent());
			// article_content 테이블에 해당 정보 insert
			ArticleContent savedContent = articleContentDao.insert(conn, content);
			
			if(savedContent == null) { // 테이블에 저장된  게시글 내용이 존재하지 않을 경우
				throw new RuntimeException("fail to insert article_content");
			}
			
			conn.commit();
			
			return savedArticle.getNumber();
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	// Article데이터에 WriteRequest의 정보를 적용하고 작성시간과 수정시간을 현재시간으로 적용 후 반환
	private Article toArticle(WriteRequest req) {
		Date now = new Date();
		return new Article(null, req.getWriter(), req.getTitle(), now, now, 0);
	}
}
