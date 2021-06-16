package com.RanReco.util.weather;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class DustApiExplorer {
	private String sido = "";
	private int sidoCode = 0;
	
    String DustApi(int sidoCode) throws IOException {    
        
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"); /*URL*/
        urlBuilder.append("?serviceKey=9dTUPP6qNq3xCtlVsxEdVVCebNm2PK1PvahICMm%2BvRlpG5srd7KDHvyyac3UBuz2JBFPTEKXKyg8TtpPKhWYTQ%3D%3D"); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&returnType=json"); /*xml 또는 json*/
        urlBuilder.append("&sidoName=" + URLEncoder.encode(sidoCodeReturn(sidoCode), "UTF-8")); 
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        
        return sb.toString();
    	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}
    
	public String sidoCodeReturn (int sidoCode) {
	
		switch (sidoCode) {
			case 11:
				sido = "서울";
				break;
			case 23:
				sido = "부산";
				break;
			case 27:
				sido = "대구";
				break;
			case 28:
				sido = "인천";
				break;
			case 29:
				sido = "광주";
				break;
			case 30:
				sido = "대전";
				break;
			case 31:
				sido = "울산";
				break;
			case 41:
				sido = "경기";
				break;
			case 42:
				sido = "강원";
				break;
			case 43:
				sido = "충북";
				break;
			case 44:
				sido = "충남";
				break;
			case 45:
				sido = "전북";
				break;
			case 46:
				sido = "전남";
				break;
			case 47:
				sido = "경북";
				break;
			case 48:
				sido = "경남";
				break;
			case 50:
				sido = "제주";
				break;
			default:
				break;
	}
	
		return sido;
	}
	
}