package com.RanReco.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RanReco.dao.MemberLocationRepository;
import com.RanReco.service.MemberLocationService;
import com.RanReco.vo.MemberLocationVO;

@Service("memberLocationService")
public class MemberLocationServiceImpl implements MemberLocationService{

	@Autowired
	private MemberLocationRepository memberLocationDAO;
	
	
	@Override
	public void insertLocation(MemberLocationVO memberLocationVO)throws Exception {
		memberLocationDAO.save(memberLocationVO);
	}
	
	@Transactional
	@Override
	public MemberLocationVO selectLocation(int memberIdx) throws Exception {
		MemberLocationVO resultVO = memberLocationDAO.findByMemberIdx(memberIdx);
		      
		      if (resultVO != null) {
		      String loc = resultVO.getMemberLocation();
		      
		      switch (loc) {
		      case "노량진":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("동작구");
		         resultVO.setDong("노량진1동");
		         break;
		      case "문래":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("영등포구");
		         resultVO.setDong("문래2동");
		         break;
		      case "강남":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("강남구");
		         resultVO.setDong("역삼1동");
		         break;
		      case "가로수길":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("강남구");
		         resultVO.setDong("신사동");
		         break;
		      case "익선동":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("종로구");
		         resultVO.setDong("종로1.2.3.4가동");
		         break;
		      case "여의도":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("영등포구");
		         resultVO.setDong("여의동");
		         break;
		      case "홍대입구":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("마포구");
		         resultVO.setDong("서교동");
		         break;
		      case "ddp":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("중구");
		         resultVO.setDong("광희동");
		         break;
		      case "coex":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("강남구");
		         resultVO.setDong("삼성1동");
		         break;
		      case "서울숲":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("성동구");
		         resultVO.setDong("성수1가1동");
		         break;
		      case "샤로수길":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("관악구");
		         resultVO.setDong("봉천본동");
		         break;
		      case "이태원":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("용산구");
		         resultVO.setDong("이태원1동");
		         break;
		      case "대학로":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("종로구");
		         resultVO.setDong("이화동");
		         break;
		      case "센트럴시티":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("서초구");
		         resultVO.setDong("반포4동");
		         break;
		      case "경복궁":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("종로구");
		         resultVO.setDong("사직동");
		         break;
		      case "성수":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("성동구");
		         resultVO.setDong("성수2가1동");
		         break;
		      case "왕십리":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("성동구");
		         resultVO.setDong("행당1동");
		         break;
		      case "상수":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("마포구");
		         resultVO.setDong("서강동");
		         break;
		      case "압구정로데오":
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("강남구");
		         resultVO.setDong("압구정1동");
		         break;
		      default:
		         resultVO.setSido("서울특별시");
		         resultVO.setSigungu("중구");
		         resultVO.setDong("명동");
		         break;
		         }
		      
		      }
		      
		      return resultVO;
		   }
	
	

	@Transactional
	@Override
	public void deleteLocation(MemberLocationVO memberLocationVO) throws Exception {
		memberLocationDAO.deleteByMemberIdx(memberLocationVO.getMemberIdx());
	}


	@Transactional
	@Override
	public void updateLocation(MemberLocationVO memberLocationVO) throws Exception {
		Optional<MemberLocationVO> resultVO = Optional.of(memberLocationDAO.findByMemberIdx(memberLocationVO.getMemberIdx()));
		 
		if (resultVO.isPresent()) { 
			resultVO.get().setMemberLocation(memberLocationVO.getMemberLocation());
			memberLocationDAO.save(memberLocationVO); 
		}
	}

}
