package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class LoginService {
	
	private MemberDao memberDao = new MemberDao();
	
	public User login(String id, String password) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			// 해당하는 아이디의 멤버 테이블 정보를 가져온 후 조건문에 사용
			Member member = memberDao.selectId(conn, id);
			
			if(member == null) { // 아이디가 존재 하지 않는 경우
				throw new LoginFailException();
			}
			
			if(!member.matchPw(password)) { // 비밀번호가 일치하지 않을 경우
				throw new LoginFailException();
			}
			
			// 멤버 테이블의 아이디와 이름을 가져온 후 반환
			return new User(member.getId(), member.getName());
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
