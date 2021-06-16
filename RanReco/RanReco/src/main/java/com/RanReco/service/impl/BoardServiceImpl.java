package com.RanReco.service.impl;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.RanReco.common.Constants;
import com.RanReco.dao.BoardFileRepository;
import com.RanReco.dao.BoardLikeRepository;
import com.RanReco.dao.BoardRepository;
import com.RanReco.dao.LikeRepository;
import com.RanReco.dao.ReplyRepository;
import com.RanReco.service.BoardService;
import com.RanReco.vo.BoardFileVO;
import com.RanReco.vo.BoardLikeVO;
import com.RanReco.vo.BoardSVO;
import com.RanReco.vo.BoardVO;
import com.RanReco.vo.Paging;



@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository boardDAO;
	@Autowired
	private ReplyRepository replyDAO;
	@Autowired
	private LikeRepository likeDAO;
	@Autowired
	private BoardLikeRepository boardLikeDAO;
	@Autowired
	private BoardFileRepository fileDAO;
	
	// 좋아요 순 TOP3 추출
	   @Transactional
	   @Override
	   public List<BoardVO> rankBoardLike(BoardVO vo) {
	      return boardDAO.findRankByLikes(vo);
	   }
	/**
	 * 게시글 목록 조회
	 * @param memberVO
	 * @param pageable
	 * @return Page<Member>
	 * @throws Exception
	 */
	@Transactional
	@Override
	public Page<BoardVO> selectBoardList(BoardVO board, Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		Page <BoardVO> resultList = null;
		
		// 게시글 제목으로 검색 키워드가 없다면 (모든 조회일 경우)
		if("".equals(retNull(board.getTitle()))) {
			// 게시글 내용으로 검색 키워드가 없다면 (모든 조회일 경우)
			if("".equals(retNull(board.getContent()))) {
				// 둘다 없으면 전체 조회
				resultList = boardDAO.findAllByOrderByIdxDesc(pageable);
			} else {
				// 제목은 검색 키워드는 없지만 내용을 조회하면  내용 조회
				resultList = boardDAO.findByContentLikeOrderByIdxDesc(pageable, "%"+board.getContent()+"%");
			}
				// 게시글 제목으로 검색 키워드가 있다면 BoardTitle으로 like 검색
		} else {
			resultList = boardDAO.findByTitleLikeOrderByIdxDesc(pageable, "%"+board.getTitle()+"%");
		}
		for(int i=0; i<resultList.getContent().size(); i++) {
			int fileIdx = resultList.getContent().get(i).getIdx();
			BoardFileVO file = fileDAO.findByFileIdx(fileIdx);
			if(file != null) {
				resultList.getContent().get(i).setFoodFile(file.getFoodFileName());
			}
        
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
	public BoardVO selectBoard(BoardVO board) throws Exception {
		BoardVO resultVO = boardDAO.findByIdx(board.getIdx());	
		int replyVO = replyDAO.countByBoardIdx(board.getIdx()); // 댓글 수 조회
		BoardFileVO fileVO = fileDAO.findByBoardIdx(resultVO.getIdx());
		int cnt = resultVO.getCnt();
	      cnt+=1;
	      resultVO.setCnt(cnt);
	      
	    resultVO.setReplyCnt(replyVO); 
	    resultVO.setLocationFile(fileVO.getLocationFileName());
	    resultVO.setFoodFile(fileVO.getFoodFileName());
	    resultVO.setFashionFile(fileVO.getFashionFileName());
	      
		return resultVO;
	}

	
	/**
	 * 게시글 등록
	 * @param files
	 * @param memberVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void insertBoard(MultipartFile lo, MultipartFile fo, MultipartFile fa, BoardVO board) throws Exception {
		boardDAO.save(board);
		
		String origFileName_location = lo.getOriginalFilename();
		String origFileName_food = fo.getOriginalFilename();
		String origFileName_fashion = fa.getOriginalFilename();
		
		String origFileExt_location = origFileName_location.substring(origFileName_location.lastIndexOf(".") + 1);
		String origFileExt_food = origFileName_food.substring(origFileName_food.lastIndexOf(".") + 1);
		String origFileExt_fashion = origFileName_fashion.substring(origFileName_fashion.lastIndexOf(".") + 1);
		
		String fileName_location = UUID.randomUUID().toString() + "." + origFileExt_location;
		String fileName_food = UUID.randomUUID().toString() + "." + origFileExt_food;
		String fileName_fashion = UUID.randomUUID().toString() + "." + origFileExt_fashion;
		
		String savePath_location = Constants.getUploadLocationPath();
		String savePath_food = Constants.getUploadFoodPath();
		String savePath_fashion = Constants.getUploadFashionPath();
		
		if (!new File(savePath_location).exists() || !new File(savePath_food).exists() || !new File(savePath_fashion).exists()) {
            try{
                new File(savePath_location).mkdir();
                new File(savePath_food).mkdir();
                new File(savePath_fashion).mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
		}
		
		// 풀 경로 -> 파일 + 파일명
        String filePath_location = savePath_location + File.separator + fileName_location;
        String filePath_food = savePath_food + File.separator + fileName_food;
        String filePath_fashion = savePath_fashion + File.separator + fileName_fashion;
        // 업로드된 파일을 풀 경로에 복사한다.
        lo.transferTo(new File(filePath_location));
        fo.transferTo(new File(filePath_food));
        fa.transferTo(new File(filePath_fashion));

        // 업로드된 파일 정보 file 테이블에 insert
        BoardFileVO fileVO = new BoardFileVO();
        fileVO.setBoardIdx(board.getIdx());
        fileVO.setLocationFileName(fileName_location);
        fileVO.setLocationFilePath(filePath_location);
        fileVO.setFoodFileName(fileName_food);
        fileVO.setFoodFilePath(filePath_food);
        fileVO.setFashionFileName(fileName_fashion);
        fileVO.setFashionFilePath(filePath_fashion);
        
        fileDAO.save(fileVO);
		
	}
	
	@Transactional
	@Override
	public void reBoard(BoardVO board) {
		boardDAO.save(board);
	}

	/**
	 * 게시글 수정
	 * @param files
	 * @param memberVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void updateBoard(BoardVO board) throws Exception {
		Optional<BoardVO> resultVO = boardDAO.findById(board.getIdx());
		 
		if (resultVO.isPresent()) {
			resultVO.get().setTitle(board.getTitle());
			resultVO.get().setWriter(board.getWriter());
			resultVO.get().setContent(board.getContent());
			resultVO.get().setRegDate(board.getRegDate());
			
			boardDAO.save(board);
		}
		
	}
	
	/**
	 * 회원 삭제(관리자)
	 * @param memberSVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void deleteBoardByAdmin(BoardSVO boardSVO) throws Exception {
		//선택한 건 만큼 반복을 돌려 삭제한다
		for(int i=0; i<boardSVO.getIdxs().length; i++) {
			int boardIdx = Integer.parseInt(boardSVO.getIdxs()[i]);
			// 게시글 삭제
			boardDAO.deleteByIdx(boardIdx);
			boardLikeDAO.deleteByBoardIdx(boardIdx);
			replyDAO.deleteByBoardIdx(boardIdx);
			likeDAO.deleteByBoardIdx(boardIdx);
		}
	}
	
	// 게시글 삭제(자신이 쓴 게시글만 권한)
	@Transactional
	@Override
	public void deleteBoard(BoardVO board) throws Exception {
		boardDAO.deleteByIdx(board.getIdx());
	}
	
	// 좋아요 삭제(해당 게시글 삭제시 같이)
	@Transactional
	@Override
	public void deleteBoardLike(int boardIdx) {
		boardLikeDAO.deleteByBoardIdx(boardIdx);
	}
	
	// 해당 댓글 좋아요, 싫어요 리스트
	@Transactional
	@Override
	public BoardLikeVO likeList(BoardLikeVO vo) {
		return boardLikeDAO.findBoardLikesByBoardIdxByMemberIdx(vo.getBoardIdx(), vo.getMemberIdx());
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
		int totalCount = (int)boardDAO.count();
		
		startPage = ( ( ( (number % maxPageCnt) == 0) ? number-1 : number) / maxPageCnt) * maxPageCnt + 1;
		totalPage = (totalCount / pageSize) + ( ((totalCount % pageSize) == 0) ? 0 : 1);
		endPage = ( (totalPage - startPage) >= maxPageCnt ) ? (startPage + (maxPageCnt-1)) : totalPage;
		
		pagingVO.setStartPage(startPage);
		pagingVO.setTotalPage(totalPage);
		pagingVO.setEndPage(endPage);
		
		return pagingVO;
	}
	
	// 좋아요 추가
		@Transactional
		@Override
		public void boardLike(BoardLikeVO bvo) {
			bvo.setLikes("T");
			boardLikeDAO.save(bvo);
		}
		
		// 좋아요 취소
		@Transactional
		@Override
		public void boardLikeCancle(BoardLikeVO bvo) {
			boardLikeDAO.delete(bvo);
		}
		
		// 싫어요 추가
		@Transactional
		@Override
		public void boardHate(BoardLikeVO bvo) {
			bvo.setLikes("F");
			boardLikeDAO.save(bvo);
		}
		
		// 싫어요 취소
		@Transactional
		@Override
		public void boardHateCancle(BoardLikeVO bvo) {
			boardLikeDAO.delete(bvo);
		}
	
}
