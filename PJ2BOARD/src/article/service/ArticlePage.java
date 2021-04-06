package article.service;

import java.util.List;

import article.model.Article;

public class ArticlePage {
	
	private int total;	// 전체 게시글의 개수를 보관한다.
	private int currentPage;	// 사용자가 요청한 페이지 번호를 보관한다.
	private List<Article> content;	// 화면에 출력할 게시글 목록을 보관한다.
	private int totalPages;	// 전체 페이지 개수를 보관한다.
	private int startPage;	// 화면 하단에 보여줄 페이징 처리 부분의 페이지 이동 링크의 시작 번호를 저장한다.
	private int endPage;	// 화면 하단에 보여줄 페이징 처리 부분의 페이지 이동 링크의 끝 번호를 저장한다.
	
	public ArticlePage(int total, int currentPage, int size, List<Article> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if(total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if (modVal == 0) startPage -= 5;
			
			endPage = startPage + 4;
			if (endPage > totalPages) endPage = totalPages;
 		}
	}

	public int getTotal() {
		return total;
	}
	
	public boolean hasNoArticles() {
		return total == 0;
	}
	
	public boolean hasArticles() {
		return total > 0;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Article> getContent() {
		return content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
}
