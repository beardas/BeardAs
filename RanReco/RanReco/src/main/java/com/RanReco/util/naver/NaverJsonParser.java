package com.RanReco.util.naver;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.RanReco.vo.MemberVO;

public class NaverJsonParser {
	JSONParser jsonParser = new JSONParser();

	public MemberVO changeJson(String string) throws Exception {

		HashMap<String, Object> map = new HashMap<>();
		JSONParser jsonParser = new JSONParser();
		MemberVO member = new MemberVO();

		JSONObject jsonObject = (JSONObject) jsonParser.parse(string);

		jsonObject = (JSONObject) jsonObject.get("response");

		map.put("userId", jsonObject.get("email"));
		map.put("userName", jsonObject.get("name"));
		map.put("userPhoneNumber", jsonObject.get("mobile"));

		member.setName(map.get("userName").toString());
		member.setEmail(map.get("userId").toString()); // id -> vo.email 넣기
		member.setId(map.get("userId").toString());// id -> vo.naver 넣기
		member.setPhoneNumber(map.get("userPhoneNumber").toString());// id -> vo.naver 넣기
		member.setProvider("Naver");

		return member;
	}
}