package com.RanReco.util.weather;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WeatherFetcher {
    private final int BASE_DATE = 3;
    private final int BASE_TIME = 5;
    private final int NX = 7;
    private final int NY = 9;
    private final String[] uri = {
            "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?ServiceKey=",
            "9dTUPP6qNq3xCtlVsxEdVVCebNm2PK1PvahICMm%2BvRlpG5srd7KDHvyyac3UBuz2JBFPTEKXKyg8TtpPKhWYTQ%3D%3D", //service key
            "&base_date=", "", "&base_time=", "", "&nx=", "", "&ny=", "", "&dataType=json"
    };
    
    private Calendar calBase = null;
    private int hour;
    private int lastBaseTime;

    private JakeJsonParser jjp = null;

    public WeatherFetcher() {
        jjp = JakeJsonParser.getInstance();
        calBase = Calendar.getInstance(); // 현재시간 가져옴
        calBase.set(Calendar.MINUTE, 0); // 분, 초 필요없음
        calBase.set(Calendar.SECOND, 0);
        hour = calBase.get(Calendar.HOUR_OF_DAY);
        lastBaseTime = getLastBaseTime(hour);
    }

    private int getLastBaseTime(int t) {
        if (t >= 0) {
            if (t < 2) {
                calBase.add(Calendar.DATE, -1);
                calBase.set(Calendar.HOUR_OF_DAY, 23);
                return 23;
            } else {
                calBase.set(Calendar.HOUR_OF_DAY, t - (t + 1) % 3);
                return t - (t + 1) % 3;
            }
        } else
            return -1;
    }

    private String getBaseTime() {
        if (lastBaseTime / 10 > 0) // 두자리수이면
            return lastBaseTime + "00";
        else // 한자리수이면
            return "0" + lastBaseTime + "00";
    }

    public WeatherSet fetchWeather(String nx, String ny) {
        WeatherSet ws = null;
        String sUrl = new String();
        int pop = -1, sky = -1, t3h = 0, pty = -1;
        uri[BASE_DATE] = new SimpleDateFormat("yyyyMMdd").format(calBase.getTime());
        uri[BASE_TIME] = getBaseTime();
        uri[NX] = nx;
        uri[NY] = ny;
        for (int i = 0; i < uri.length; i++)
            sUrl += uri[i];
        
        JSONArray jsonArr = jjp.getWeatherJSONArray(sUrl);

        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jobj = (JSONObject) jsonArr.get(i);
            if (((String) jobj.get("category")).equals("POP"))
                pop = Integer.valueOf((String) jobj.get("fcstValue")); // JSON에서 ""로 감싸지지않은 값은 long 형이므로 casting 필수!
            else if (((String) jobj.get("category")).equals("SKY"))
                sky = Integer.valueOf((String) jobj.get("fcstValue"));
            else if (((String) jobj.get("category")).equals("T3H"))
            	t3h = Integer.valueOf((String) jobj.get("fcstValue"));
            else if (((String) jobj.get("category")).equals("PTY"))
            	pty = Integer.valueOf((String) jobj.get("fcstValue"));
        }
        if (pop != -1 && sky != -1 && pty != -1){
            Date bd = calBase.getTime();
            ws = new WeatherSet(pop, sky, t3h, pty, bd);
        }
        return ws;
    }
}