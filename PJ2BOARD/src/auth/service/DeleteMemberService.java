package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;
import member.service.InvalidPasswordException;

public class DeleteMemberService {
	
	private MemberDao memberDao = new MemberDao();
	
	public void delete(String id, String pw) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			// 해당하는 아이디의 멤버 테이블 정보를 가져온 후 조건문에 사용
			Member member = memberDao.selectId(conn, id);
			Member password = memberDao.selectId(conn, id);
			
			if(member == null) { // 탈퇴하려는 아이디가 존재 하지 않는 경우
				throw new InvalideIdException();
			}
			
			if(!member.matchPw(pw)) { // 비밀번호가 일치하지 않을 경우
				throw new InvalideIdException();
			}
			
			// 유저의 비밀번호와 입력된 비밀번호가 같지 않으면 예외처리(비밀번호 일치 검사)
			if(!matchPw(pw, password)) {
				throw new InvalidPasswordException();
			}
			// ArticleDao와 ArticleContentDao의 delete() 메서드를 이용해서 게시물을 삭제한다.
			memberDao.delete(conn, member.getId());
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
		
	private boolean matchPw(String matchingPw, Member password) {
			return password.getPassword().equals(matchingPw);
	}
		
		

}

