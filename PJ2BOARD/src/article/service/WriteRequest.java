package article.service;

import java.util.Map;

import article.model.Writer;

public class WriteRequest {
	
	private Writer writer;
	private String title;
	private String content;
	
	public WriteRequest(Writer writer, String title, String content) {
		this.writer = writer;
		this.title = title;
		this.content = content;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
	// 글 제목이 null이거나 비어있을경우 title에러 발생
	public void validate(Map<String, Boolean> errors) {
		if(title == null || title.isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}
