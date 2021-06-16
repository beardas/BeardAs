package com.RanReco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.RanReco.vo.NoticeSVO;
import com.RanReco.vo.NoticeVO;
import com.RanReco.vo.Paging;




@Service
public interface NoticeService {
	/**
	 * 게시글 목록 조회
	 * @param notice
	 * @param pageable
	 * @return Page<Notice>
	 * @throws Exception
	 */
	Page<NoticeVO> selectNoticeList(NoticeVO notice, Pageable pageable) throws Exception;
	
	/**
	 * 페이징 처리를 위한 메소드
	 * @param pageSize
	 * @param number
	 * @return PagingVO
	 * @throws Exception
	 */
	public Paging setPaging(int pageSize, int number) throws Exception;
	
	/**
	 * 게시글 조회
	 * @param notice
	 * @return notice
	 * @throws Exception
	 */
	NoticeVO selectNotice(NoticeVO notice) throws Exception;
	
	
	/**
	 * 게시글 등록
	 * @param files
	 * @param notice
	 * @throws Exception
	 */
	void insertNotice(NoticeVO notice) throws Exception;
	
	/**
	 * 게시글 수정
	 * @param files
	 * @param notice
	 * @throws Exception
	 */
	void updateNotice(NoticeVO notice) throws Exception;
	
	/**
	 * 게시글 삭제(관리자)
	 * @param noticeSVO
	 * @throws Exception
	 */
	void deleteNoticeByAdmin(NoticeSVO noticeSVO) throws Exception;
	
	void deleteNotice(NoticeVO notice) throws Exception;
	
	
}

