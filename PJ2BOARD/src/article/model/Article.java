package article.model;

import java.util.Date;

public class Article {
	
	private Integer number; // 게시글 번호
	private Writer writer;	// 작성자
	private String title;	// 글 제목
	private Date regDate;	// 작성 일자
	private Date modDate;	// 수정 일자
	private int readCount;	// 조회수
	private int likeCount;  // 좋아요
	
	public Article(Integer number, Writer writer, String title, Date regDate, Date modDate, int readCount, int likeCount) {
		this.number = number;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.modDate = modDate;
		this.readCount = readCount;
		this.likeCount = likeCount;
	}

	public Integer getNumber() {
		return number;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public Date getRegDate() {
		return regDate;
	}

	public Date getModDate() {
		return modDate;
	}

	public int getReadCount() {
		return readCount;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
}
