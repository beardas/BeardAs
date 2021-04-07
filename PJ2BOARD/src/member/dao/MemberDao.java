package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {
	
	public Member selectId(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from member where memberid = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			Member member = null;
			if(rs.next()) {
				member = new Member(rs.getString("memberid"),
						rs.getString("name"),
						rs.getString("password"),
						rs.getString("email"),
						rs.getString("phone"),
						toDate(rs.getTimestamp("regdate")));
			}
			
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}
	
	public void insert(Connection conn, Member member) throws SQLException {
		try(PreparedStatement pstmt = conn.prepareStatement("insert into member(memberid, name, password, email, phone, regdate) values(?,?,?,?,?,?)")) {
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setTimestamp(6, new Timestamp(member.getRegDate().getTime())); // 아이디가 생성된 날짜를 시간만 따와서 설정
			pstmt.executeUpdate();
		}
	}
	
	public void update(Connection conn, Member member) throws SQLException {
		try(PreparedStatement pstmt = conn.prepareStatement("update member set password = ? where memberid = ?")) {
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getId());
			pstmt.executeUpdate();
		}
	}
	
	public void updateMember(Connection conn, Member member) throws SQLException {
		try(PreparedStatement pstmt = conn.prepareStatement("update member set name = ?, email = ?, phone = ?  where memberid = ?")) {
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getId());
			pstmt.executeUpdate();
		}
	}
	
	public void delete(Connection conn, String id) throws SQLException {
		try(PreparedStatement pstmt = conn.prepareStatement("delete from member where memberid = ?")) {
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		}
	}
}
