package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class JoinService {
	
	private MemberDao memberDao = new MemberDao();
	
	public void join(JoinRequest joinReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			
			Member member = memberDao.selectId(conn, joinReq.getId());
			
			// 입력받은 아이디가 null 값이 아닐경우(중복된 아이디) 롤백 실행
			if(member != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicatedIdException();
			}
			
			// 멤버dao에 입력받은(id, 이름, 암호, 날짜)를 넣어준다.
			memberDao.insert(conn, new Member(joinReq.getId(),
					joinReq.getName(),
					joinReq.getPassword(),
					joinReq.getEmail(),
					joinReq.getPhone(),
					new Date()));
			conn.commit();
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	
}
