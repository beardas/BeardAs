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
import com.RanReco.dao.FoodFileRepository;
import com.RanReco.dao.FoodRepository;
import com.RanReco.service.FoodService;
import com.RanReco.vo.FoodFileVO;
import com.RanReco.vo.FoodVO;
import com.RanReco.vo.Paging;

@Service("foodService")
public class FoodServiceImpl implements FoodService{

	@Autowired
	private FoodRepository foodDAO;
	
	@Autowired
	private FoodFileRepository fileDAO;
	
	
	@Override
	public void insertFood(MultipartFile files, FoodVO foodVO)throws Exception {
		foodDAO.save(foodVO);
		
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
        String savePath = Constants.getUploadFoodInfoPath();
        
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
        FoodFileVO fileVO = new FoodFileVO();
        fileVO.setFoodIdx(foodVO.getIdx());
        fileVO.setOrigFileName(origFileName);
        fileVO.setFileName(fileName);
        fileVO.setFilePath(filePath);
        
        fileDAO.save(fileVO);
	}
	
	@Transactional
	@Override
	public Page<FoodVO> selectFoodList(FoodVO foodVO, Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		Page <FoodVO> resultList = null;
		
			// 게시글 내용으로 검색 키워드가 없다면 (모든 조회일 경우)
			if("".equals(retNull(foodVO.getLocation()))) {
				// 둘다 없으면 전체 조회
				resultList = foodDAO.findAllByOrderByIdxDesc(pageable);
			} else {
				// 제목은 검색 키워드는 없지만 내용을 조회하면  내용 조회
				resultList = foodDAO.findByLocationLikeOrderByIdxDesc(pageable, "%"+foodVO.getLocation()+"%");
			}
		// 조회결과로 해당 멤버의 이미지를 찾는다
				for(int i=0; i<resultList.getContent().size(); i++) {
					int foodIdx = resultList.getContent().get(i).getIdx();
					FoodFileVO file = fileDAO.findByFileIdx(foodIdx);
					if(file != null) {
						resultList.getContent().get(i).setFoodFile(file.getFileName());
					}
		        
	}
				return resultList;
	}
	
	
	@Transactional
	@Override
	public FoodVO selectFood(FoodVO foodVO) throws Exception {
		FoodVO resultVO = foodDAO.findByIdx(foodVO.getIdx());
		FoodFileVO fileVO = fileDAO.findByFoodIdx(resultVO.getIdx());
		
		resultVO.setFoodFile(fileVO.getFileName());
		return resultVO;
	}
	
	
	

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
		int totalCount = (int)foodDAO.count();
		
		startPage = ( ( ( (number % maxPageCnt) == 0) ? number-1 : number) / maxPageCnt) * maxPageCnt + 1;
		totalPage = (totalCount / pageSize) + ( ((totalCount % pageSize) == 0) ? 0 : 1);
		endPage = ( (totalPage - startPage) >= maxPageCnt ) ? (startPage + (maxPageCnt-1)) : totalPage;
		
		pagingVO.setStartPage(startPage);
		pagingVO.setTotalPage(totalPage);
		pagingVO.setEndPage(endPage);
		
		return pagingVO;
	}


	@Transactional
	@Override
	public void updateFood(MultipartFile files, FoodVO foodVO) throws Exception {
		Optional<FoodVO> resultVO = foodDAO.findById(foodVO.getIdx());
		 
		if (resultVO.isPresent()) { 
			resultVO.get().setUrl(foodVO.getUrl());
			resultVO.get().setStar(foodVO.getStar());
			resultVO.get().setAddress(foodVO.getAddress());
			resultVO.get().setLocation(foodVO.getLocation());
			resultVO.get().setStoreName(foodVO.getStoreName());
			
			foodDAO.save(foodVO); 
		}
		// 해당 MemberIdx로 File을 찾는다
				FoodFileVO fileVO = fileDAO.findByFoodIdx(foodVO.getIdx());
				// 파일이 존재한다면 삭제
				if(fileVO != null) {
					File file = new File(fileVO.getFilePath());
					file.delete();
				}
				// 파일 테이블에서 해당 멤버의 파일정보 삭제
				fileDAO.deleteByFoodIdx(foodVO.getIdx());
						
				// 업로드된 실제 파일명
				String origFileName = files.getOriginalFilename();
				// 업로드된 실제 파일의 확장자
				String origFileExt = origFileName.substring(origFileName.lastIndexOf(".") + 1);
				// DB 및 하드디스크에 저장될 파일명 생성(랜덤값)
		        String fileName = UUID.randomUUID().toString() + "." + origFileExt;
		        // 하드디스크에 파일이 저장될 경로 -> 상수로 관리한다. => D:/MemberMgmt
		        String savePath = Constants.getUploadFoodInfoPath();
		        
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
		        fileVO = new FoodFileVO();
		        fileVO.setFoodIdx(foodVO.getIdx());
		        fileVO.setOrigFileName(origFileName);
		        fileVO.setFileName(fileName);
		        fileVO.setFilePath(filePath);
		        
		        fileDAO.save(fileVO);
		
	}

}
