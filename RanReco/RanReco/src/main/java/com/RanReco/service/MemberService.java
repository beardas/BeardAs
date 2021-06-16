package com.RanReco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.RanReco.vo.MemberSVO;
import com.RanReco.vo.MemberVO;
import com.RanReco.vo.Paging;



@Service
public interface MemberService {
	/**
	 * 회원 목록 조회
	 * @param memberVO
	 * @param pageable
	 * @return Page<MemberVO>
	 * @throws Exception
	 */
	Page<MemberVO> selectMemberList(MemberVO member, Pageable pageable) throws Exception;
	
	/**
	 * 페이징 처리를 위한 메소드
	 * @param pageSize
	 * @param number
	 * @return PagingVO
	 * @throws Exception
	 */
	public Paging setPaging(int pageSize, int number) throws Exception;
	
	/**
	 * 회원 조회
	 * @param memberVO
	 * @return MemberVO
	 * @throws Exception
	 */
	MemberVO selectMember(MemberVO member) throws Exception;
	
	/**
	 * 아이디, 패스워드로 회원 조회
	 * @param memberVO
	 * @throws Exception
	 */
	MemberVO selectMemberByIdByPw(MemberVO member) throws Exception;
	
	/**
	 * 회원 등록
	 * @param files
	 * @param memberVO
	 * @throws Exception
	 */
	void insertMember(MultipartFile files, MemberVO member) throws Exception;
	
	/**
	 * 회원 수정
	 * @param files
	 * @param memberVO
	 * @throws Exception
	 */
	void updateMember(MultipartFile files, MemberVO member) throws Exception;
	
	/**
	 * 회원 삭제
	 * @param memberSVO
	 * @throws Exception
	 */
	void deleteMember(MemberVO member) throws Exception;
	void deleteMemberByAdmin(MemberSVO memberSVO) throws Exception;

	MemberVO selectMemberByphoneNumber(MemberVO member) throws Exception;

	MemberVO selectMemberById(MemberVO member)throws Exception;

	void insertNaver(MemberVO memberVO);

	int selectNaver(MemberVO memberVO);

	/**
	 * 회원 조회
	 * @param memberVO
	 * @return MemberVO
	 * @throws Exception
	 */
	MemberVO selectMyResult(MemberVO member) throws Exception;

	/**
	 * 회원 조회
	 * @param memberVO
	 * @return MemberVO
	 * @throws Exception
	 */
}

