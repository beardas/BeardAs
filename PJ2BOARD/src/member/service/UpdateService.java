package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class UpdateService {
	
	private MemberDao memberDao = new MemberDao();
	
	public void updateMember(String id, String name, String email, String phone) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDao.selectId(conn, id);
			
			// 멤버dao에 입력받은(id, 이름, 암호, 이메일, 전화번호, 날짜)를 넣어준다.
			
			member.changeName(name);
			member.changeEmail(email);
			member.changePhone(phone);
			
			memberDao.updateMember(conn, member);
			conn.commit();
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	
}
