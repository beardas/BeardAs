package article.model;

public class ArticleContent {

	private Integer number; // 게시글 번호
	private String content; // 게시글 내용
	
	public ArticleContent(Integer number, String content) {
		this.number = number;
		this.content = content;
	}

	public Integer getNumber() {
		return number;
	}

	public String getContent() {
		return content;
	}
}
