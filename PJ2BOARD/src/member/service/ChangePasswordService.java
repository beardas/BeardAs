package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class ChangePasswordService {
	
	private MemberDao memberDao = new MemberDao();
	
	public void changePassword(String id, String curPw, String newPw) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDao.selectId(conn, id);
			
			if(member == null) { // 아이디에 해당하는 멤버 데이터가 null이면 예외처리
				throw new MemberNotFoundException();
			}
			
			if(!member.matchPw(curPw)) { // 현재 비밀번호가 일치하지 않은 경우 예외처리
				throw new InvalidPasswordException();
			}
			
			member.changePw(newPw); // 받아오는 암호 값을 새로운 비밀번호로 변경
			memberDao.update(conn, member); // 새로운 비밀번호로 쿼리문 업데이트
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
