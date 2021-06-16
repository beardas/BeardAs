package com.RanReco.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.RanReco.dao.NoticeRepository;
import com.RanReco.service.NoticeService;
import com.RanReco.vo.NoticeSVO;
import com.RanReco.vo.NoticeVO;
import com.RanReco.vo.Paging;



@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeRepository noticeDAO;
	
	
	/**
	 * 게시글 목록 조회
	 * @param memberVO
	 * @param pageable
	 * @return Page<Notice>
	 * @throws Exception
	 */
	@Transactional
	@Override
	public Page<NoticeVO> selectNoticeList(NoticeVO notice, Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		Page <NoticeVO> resultList = null;
		
		// 게시글 제목으로 검색 키워드가 없다면 (모든 조회일 경우)
		if("".equals(retNull(notice.getTitle()))) {
			// 게시글 내용으로 검색 키워드가 없다면 (모든 조회일 경우)
			if("".equals(retNull(notice.getContent()))) {
				// 둘다 없으면 전체 조회
				resultList = noticeDAO.findAllByOrderByIdxDesc(pageable);
			} else {
				// 제목은 검색 키워드는 없지만 내용을 조회하면  내용 조회
				resultList = noticeDAO.findByContentLikeOrderByIdxDesc(pageable, "%"+notice.getContent()+"%");
			}
				// 게시글 제목으로 검색 키워드가 있다면 noticeTitle으로 like 검색
		} else {
			resultList = noticeDAO.findByTitleLikeOrderByIdxDesc(pageable, "%"+notice.getTitle()+"%");
		}
		        
		return resultList;
	}
	
	/**
	 * 게시글 조회 (선택 했을 시)
	 * @param memberVO
	 * @return MemberVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public NoticeVO selectNotice(NoticeVO notice) throws Exception {
		NoticeVO resultNotice = noticeDAO.findByIdx(notice.getIdx());
		int cnt = resultNotice.getCnt();
	      cnt+=1;
	      resultNotice.setCnt(cnt);
		return resultNotice;
	}

	
	/**
	 * 게시글 등록
	 * @param files
	 * @param memberVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void insertNotice(NoticeVO notice) throws Exception {
		noticeDAO.save(notice);
	}
		

	/**
	 * 게시글 수정
	 * @param files
	 * @param memberVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void updateNotice(NoticeVO notice) throws Exception {
		Optional<NoticeVO> resultVO = noticeDAO.findById(notice.getIdx());
		 
		if (resultVO.isPresent()) { 
			resultVO.get().setTitle(notice.getTitle());
			resultVO.get().setWriter(notice.getWriter());
			resultVO.get().setContent(notice.getContent());
			resultVO.get().setRegDate(notice.getRegDate());
			
			noticeDAO.save(notice); 
		}
		
	}
	
	/**
	 * 공지 삭제(관리자)
	 * @param memberSVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void deleteNoticeByAdmin(NoticeSVO noticeSVO) throws Exception {
		//선택한 건 만큼 반복을 돌려 삭제한다
		for(int i=0; i<noticeSVO.getIdxs().length; i++) {
			int noticeIdx = Integer.parseInt(noticeSVO.getIdxs()[i]);
			// 게시글 삭제
			noticeDAO.deleteByIdx(noticeIdx);		
			
		}
	}
	/**
	 * 공지 삭제(일반)
	 * @param memberSVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void deleteNotice(NoticeVO notice) throws Exception {
			noticeDAO.deleteByIdx(notice.getIdx());		
	}
	
	/**
	 * null값 처리를 위한 메소드
	 * @param str
	 * @return str
	 */
	private String retNull(String str) {
		
		if(str == null || "".equals(str) || "null".equals(str.toLowerCase())) {
			return "";
		}
		
		str = str.trim();
		
		return str;
	}
	
	// 첫 페이지
	int startPage = 0;
	// 총 페이지
	int totalPage = 0;
	// 마지막 페이지
	int endPage = 0;
	// 한 페이지의 멤버 수
	static int maxPageCnt = 10;
	
	/**
	 * 페이징 처리를 위한 메소드
	 */
	public Paging setPaging(int pageSize, int number) {
		
		Paging pagingVO = new Paging();
		int totalCount = (int)noticeDAO.count();
		
		startPage = ( ( ( (number % maxPageCnt) == 0) ? number-1 : number) / maxPageCnt) * maxPageCnt + 1;
		totalPage = (totalCount / pageSize) + ( ((totalCount % pageSize) == 0) ? 0 : 1);
		endPage = ( (totalPage - startPage) >= maxPageCnt ) ? (startPage + (maxPageCnt-1)) : totalPage;
		
		pagingVO.setStartPage(startPage);
		pagingVO.setTotalPage(totalPage);
		pagingVO.setEndPage(endPage);
		
		return pagingVO;
	}
	
}
