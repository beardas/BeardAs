package com.RanReco.util.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JakeJsonParser {
    private JakeJsonParser() {}
    private static class IODHI {
        private static final JakeJsonParser instance = new JakeJsonParser();
    }
    public static JakeJsonParser getInstance() {
        return IODHI.instance;
    }

    public JSONArray getRemoteJSONArray(String url) {
        StringBuffer jsonHtml = new StringBuffer();
        try {
            URL u = new URL(url);
            InputStream uis = u.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(uis, "UTF-8"));

            String line = null;
            while ((line = br.readLine()) != null) {
                jsonHtml.append(line + "\r\n");
            }
            br.close();
            uis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray jsonArr = (JSONArray) JSONValue.parse(jsonHtml.toString());
        return jsonArr;
    }

    public JSONArray getWeatherJSONArray(String url) {
        StringBuffer jsonHtml = new StringBuffer();
        JSONArray jsonArr = null;
        JSONObject jsonObj = null;
        String[] saAttribName = { "response", "body", "items" };
        try {
            URL u = new URL(url);
            InputStream uis = u.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(uis, "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                jsonHtml.append(line + "\r\n");
            }
            br.close();
            uis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
//        System.out.println(jsonHtml.toString());

        jsonObj = (JSONObject) JSONValue.parse(jsonHtml.toString());
        for (int i = 0; i < saAttribName.length; i++)
            jsonObj = (JSONObject) jsonObj.get(saAttribName[i]);
        jsonArr = (JSONArray) jsonObj.get("item");

        return jsonArr;
    }

    public Map<String, String> getJsonSubMap(JSONArray jsonArrSource) {
        Map<String, String> jsonMap = new LinkedHashMap<String, String>();

        // ????????? API ?????? ???????????? JSON??? code:value????????? ????????????
        // ??? Algorithm?????? ???????????? mapping??? ??? ??????
        // (leaf???????????? x,y?????? ????????? ??????????????? ?????? method???????????????)
        for (int i = 0; i < jsonArrSource.size(); i++) {
            JSONObject jsonObjItem = (JSONObject) jsonArrSource.get(i); // JSONArray?????? JSONObject????????? ?????????
            String code = (String) jsonObjItem.get("code"); // JSONObject?????? key, value ?????????
            String value = (String) jsonObjItem.get("value");
            jsonMap.put(value, code); // ?????????????????? code??? ???????????? ???????????? K,V??? ????????? mapping
        }
        return jsonMap;
    }

    public Map<String, Coord> getJsonLeafMap(JSONArray jsonArrSource) {
        Map<String, Coord> jsonMap = new LinkedHashMap<String, Coord>();
        for (int i = 0; i < jsonArrSource.size(); i++) {
            JSONObject jsonObjItem = (JSONObject) jsonArrSource.get(i);
            String value = (String) jsonObjItem.get("value");
            String x = (String) jsonObjItem.get("x");
            String y = (String) jsonObjItem.get("y");
            jsonMap.put(value, new Coord(x, y));
        }
        return jsonMap;
    }
}