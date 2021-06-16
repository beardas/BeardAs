package com.RanReco.service.impl;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.RanReco.common.Constants;
import com.RanReco.dao.MemberFashionRepository;
import com.RanReco.dao.MemberFileRepository;
import com.RanReco.dao.MemberFoodRepository;
import com.RanReco.dao.MemberLocationRepository;
import com.RanReco.dao.MemberRepository;
import com.RanReco.service.MemberService;
import com.RanReco.vo.MemberFashionVO;
import com.RanReco.vo.MemberFileVO;
import com.RanReco.vo.MemberFoodVO;
import com.RanReco.vo.MemberLocationVO;
import com.RanReco.vo.MemberSVO;
import com.RanReco.vo.MemberVO;
import com.RanReco.vo.Paging;



@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepository memberDAO;
	
	@Autowired
	private MemberFileRepository fileDAO;

	@Autowired
	private MemberLocationRepository  memberLocationDAO;

	@Autowired
	private MemberFoodRepository  foodDAO;

	@Autowired
	private MemberFashionRepository  fashionDAO;



	
	
	/**
	 * 회원 목록 조회
	 * @param memberVO
	 * @param pageable
	 * @return Page<Member>
	 * @throws Exception
	 */
	@Transactional
	@Override
	public Page<MemberVO> selectMemberList(MemberVO member, Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		Page <MemberVO> resultList = null;
		
		// memberName으로 검색 키워드가 없다면 (모든 조회일 경우)
		if("".equals(retNull(member.getName()))) {
			resultList = memberDAO.findAllByOrderByIdxDesc(pageable);
			
		// memberName으로 검색 키워드가 있다면 memberName으로 like 검색
		}else {
			resultList = memberDAO.findByNameLikeOrderByIdxDesc(pageable, "%"+member.getName()+"%");
		}
		        
		// 조회결과로 해당 멤버의 이미지를 찾는다
		for(int i=0; i<resultList.getContent().size(); i++) {
			int memberIdx = resultList.getContent().get(i).getIdx();
			MemberFileVO file = fileDAO.findByFileIdx(memberIdx);
			if(file != null) {
				resultList.getContent().get(i).setFileName(file.getFileName());
			}
		}
		
		return resultList;
	}
	
	/**
	 * 회원 조회
	 * @param memberVO
	 * @return MemberVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public MemberVO selectMyResult(MemberVO member) throws Exception {
		MemberVO resultVO = memberDAO.findByIdx(member.getIdx());
		MemberFileVO fileVO = fileDAO.findByMemberIdx(resultVO.getIdx());
		MemberFoodVO foodVO = foodDAO.findByMemberIdx(resultVO.getIdx());
		MemberFashionVO fashionVO = fashionDAO.findByMemberIdx(resultVO.getIdx());
		MemberLocationVO memberLocationVO = memberLocationDAO.findByMemberIdx(resultVO.getIdx());
		
		
		resultVO.setFileName(fileVO.getFileName()); 
		resultVO.setFood(foodVO.getMemberFood()); 
		resultVO.setFoodFile(foodVO.getMemberFoodFile()); 
		resultVO.setLook(fashionVO.getLook()); 
		resultVO.setLookFile(fashionVO.getMemberFashionFile()); 
		resultVO.setLocation(memberLocationVO.getMemberLocation()); 
		
		return resultVO;
		
	}
	@Transactional
	@Override
	public MemberVO selectMember(MemberVO member) throws Exception {
		MemberVO resultVO = memberDAO.findByIdx(member.getIdx());
		MemberFileVO fileVO = fileDAO.findByMemberIdx(resultVO.getIdx());
		
		
		resultVO.setFileName(fileVO.getFileName()); 
		return resultVO;
		
	}
	

	/**
	 * 아이디, 패스워드로 회원 조회
	 * @param memberVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public MemberVO selectMemberByIdByPw(MemberVO member) throws Exception {
		MemberVO resultVO = memberDAO.findByIdAndPassword(member.getId(), member.getPassword());
		return resultVO;
	}
	
	/**
	 * 회원 등록
	 * @param files
	 * @param memberVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void insertMember(MultipartFile files, MemberVO member) throws Exception { 
		memberDAO.save(member);
		
		/**
		 * 파일 업로드
		 * */
		
		// 업로드된 실제 파일명
		String origFileName = files.getOriginalFilename();
		// 업로드된 실제 파일의 확장자
		String origFileExt = origFileName.substring(origFileName.lastIndexOf(".") + 1);
		// DB 및 하드디스크에 저장될 파일명 생성(랜덤값)
        String fileName = UUID.randomUUID().toString() + "." + origFileExt;
        // 하드디스크에 파일이 저장될 경로 -> 상수로 관리한다. => D:/MemberMgmt
        String savePath = Constants.getUploadProfilePath();
        
        // 파일경로가 존재하지 않으면, 파일경로를 생성한다
        if (!new File(savePath).exists()) {
            try{
                new File(savePath).mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
        
        // 풀 경로 -> 파일 + 파일명
        String filePath = savePath + File.separator + fileName;
        // 업로드된 파일을 풀 경로에 복사한다.
        files.transferTo(new File(filePath));

        // 업로드된 파일 정보 file 테이블에 insert
        MemberFileVO fileVO = new MemberFileVO();
        fileVO.setMemberIdx(member.getIdx());
        fileVO.setOrigFileName(origFileName);
        fileVO.setFileName(fileName);
        fileVO.setFilePath(filePath);
        
        
        member.setFileName(fileName);
        fileDAO.save(fileVO);
        memberDAO.save(member);
	}
	
	@Transactional
	@Override
	   public int selectNaver(MemberVO memberVO) {
	      MemberVO member = memberDAO.findByIdAndPassword(memberVO.getId(), memberVO.getPassword());
	      int number = 0;
	      if(member!=null) {
	         number=1;
	      }
	      return number;
	   }
		
	@Transactional
	@Override
	   public void insertNaver(MemberVO memberVO) {
	      memberDAO.save(memberVO);
	   }
	   

	/**
	 * 회원 수정
	 * @param files
	 * @param memberVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void updateMember(MultipartFile files, MemberVO member) throws Exception {
		Optional<MemberVO> resultVO = memberDAO.findById(member.getIdx());
		 
		if (resultVO.isPresent()) { 
			resultVO.get().setName(member.getName());
			resultVO.get().setPassword(member.getPassword());
			resultVO.get().setEmail(member.getEmail());
			resultVO.get().setPhoneNumber(member.getPhoneNumber());
			resultVO.get().setAdmin(member.getAdmin());
			
			memberDAO.save(member); 
		}
		
		// 해당 MemberIdx로 File을 찾는다
		MemberFileVO fileVO = fileDAO.findByMemberIdx(member.getIdx());
		// 파일이 존재한다면 삭제
		if(fileVO != null) {
			File file = new File(fileVO.getFilePath());
			file.delete();
		}
		// 파일 테이블에서 해당 멤버의 파일정보 삭제
		fileDAO.deleteByMemberIdx(member.getIdx());
				
		// 업로드된 실제 파일명
		String origFileName = files.getOriginalFilename();
		// 업로드된 실제 파일의 확장자
		String origFileExt = origFileName.substring(origFileName.lastIndexOf(".") + 1);
		// DB 및 하드디스크에 저장될 파일명 생성(랜덤값)
        String fileName = UUID.randomUUID().toString() + "." + origFileExt;
        // 하드디스크에 파일이 저장될 경로 -> 상수로 관리한다. => D:/MemberMgmt
        String savePath = Constants.getUploadProfilePath();
        
        // 파일경로가 존재하지 않으면, 파일경로를 생성한다
        if (!new File(savePath).exists()) {
            try{
                new File(savePath).mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
        
        // 풀 경로 -> 파일 + 파일명
        String filePath = savePath + File.separator + fileName;
        // 업로드된 파일을 풀 경로에 복사한다.
        files.transferTo(new File(filePath));

        // 업로드된 파일 정보 file 테이블에 insert
        fileVO = new MemberFileVO();
        fileVO.setMemberIdx(member.getIdx());
        fileVO.setOrigFileName(origFileName);
        fileVO.setFileName(fileName);
        fileVO.setFilePath(filePath);
        
        fileDAO.save(fileVO);
	}

	/**
	 * 회원 삭제(관리자)
	 * @param memberSVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void deleteMemberByAdmin(MemberSVO memberSVO) throws Exception {
		//선택한 건 만큼 반복을 돌려 삭제한다
		for(int i=0; i<memberSVO.getIdxs().length; i++) {
			int memberIdx = Integer.parseInt(memberSVO.getIdxs()[i]);
			// Member 삭제
			memberDAO.deleteByIdx(memberIdx);		
			
			// 해당 MemberIdx로 File을 찾는다
			MemberFileVO fileVO = fileDAO.findByMemberIdx(memberIdx);
			// 파일이 존재한다면 삭제
			if(fileVO != null) {
				File file = new File(fileVO.getFilePath());
				file.delete();
			}
			// 파일 테이블에서 해당 멤버의 파일정보 삭제
			fileDAO.deleteByMemberIdx(memberIdx);
		}
	}
	
	// 자신이 회원 탈퇴
	@Transactional
	@Override
	public void deleteMember(MemberVO member) throws Exception {
		memberDAO.deleteByIdx(member.getIdx());
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
		int totalCount = (int)memberDAO.count();
		
		startPage = ( ( ( (number % maxPageCnt) == 0) ? number-1 : number) / maxPageCnt) * maxPageCnt + 1;
		totalPage = (totalCount / pageSize) + ( ((totalCount % pageSize) == 0) ? 0 : 1);
		endPage = ( (totalPage - startPage) >= maxPageCnt ) ? (startPage + (maxPageCnt-1)) : totalPage;
		
		pagingVO.setStartPage(startPage);
		pagingVO.setTotalPage(totalPage);
		pagingVO.setEndPage(endPage);
		
		return pagingVO;
	}

	@Override
	public MemberVO selectMemberByphoneNumber(MemberVO member) throws Exception {
		MemberVO resultVO = memberDAO.findByPhoneNumber(member.getPhoneNumber());
		return resultVO;
	}

	@Override
	public MemberVO selectMemberById(MemberVO member) throws Exception {
		MemberVO resultVO = memberDAO.findById(member.getId());
		return resultVO;
	}
	
}
