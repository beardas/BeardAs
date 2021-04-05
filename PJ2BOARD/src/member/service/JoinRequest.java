package member.service;

import java.util.Map;

public class JoinRequest {
	
	private String id;
	private String name;
	private String password;
	private String password2; // 비밀번호 확인
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword2() {
		return password2;
	}
	
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	public boolean confirmPw() { // 비밀번호와 비밀번호 확인이 일치하는지 검사(일치하면 true)
		return password.equals(password2) && password != null;
	}
	
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, id, "id");
		checkEmpty(errors, name, "name");
		checkEmpty(errors, password, "password");
		checkEmpty(errors, password2, "password2");
		if (!errors.containsKey("password2")) {
			if(!confirmPw()) {
				errors.put("pwNotMatch", Boolean.TRUE);
			}
		}
	}
	
	// 해당하는 입력값이 null이거나 공백일 경우 에러 발생
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}
}
