package member.model;

import java.util.Date;

public class Member {
	
	private String id;
	private String name;
	private String password;
	private String email;
	private String phone;
	private Date regDate;
	
	public Member(String id, String name, String password, String email, String phone, Date regDate) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.regDate = regDate;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public Date getRegDate() {
		return regDate;
	}

	public boolean matchPw(String pwd) {
		return password.equals(pwd);
	}
	
	public void changePw(String newPwd) {
		this.password = newPwd;
	}
	
	public void changeName(String newName) {
		this.name = newName;
	}
	
	public void changeEmail(String newEmail) {
		this.email = newEmail;
	}
	
	public void changePhone(String newPhone) {
		this.phone = newPhone;
	}
}
