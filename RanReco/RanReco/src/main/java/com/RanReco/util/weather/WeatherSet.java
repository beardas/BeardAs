package com.RanReco.util.weather;

import java.util.Calendar;
import java.util.Date;

public class WeatherSet {
    private int pop;
    private int sky;
    private int t3h;
    private int pty;
    private Date baseDate = null;
    private Date fcstDate = null;

    public WeatherSet(int pop, int sky, int t3h, int pty, Date bd) {
        this.pop = pop;
        this.sky = sky;
        this.t3h = t3h;
        this.pty = pty;
        this.baseDate = bd;
        Calendar calBase = Calendar.getInstance();
        calBase.setTime(baseDate);
        calBase.add(Calendar.HOUR_OF_DAY, 4);
        fcstDate = calBase.getTime();
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int p) {
        pop = p;
    }
    
    public int getSkyValue(){
        return sky;
    }

    public String getSky() {
        String retMsg = null;
        switch (sky) {
        case 1:
            retMsg = "맑음";
            break;
        case 2:
            retMsg = "구름 조금";
            break;
        case 3:
            retMsg = "구름 많음";
            break;
        case 4:
            retMsg = "흐림";
            break;
        default:
            retMsg = "Error";
            break;
        }
        return retMsg;
    }

    public Date getBaseDate() {
        return baseDate;
    }
    
    public Date getFcstDate() {
        return fcstDate;
    }

    public void setSky(int s) {
        sky = s;
    }

	public int getT3h() {
		return t3h;
	}

	public void setT3h(int t3h) {
		this.t3h = t3h;
	}

	public int getPtyValue() {
		return pty;
	}

	public void setPty(int pty) {
		this.pty = pty;
	}
	
	public String getPty() {
        String retMsg = null;
        switch (pty) {
        case 1:
            retMsg = "비";
            break;
        case 2:
            retMsg = "비/눈";
            break;
        case 3:
            retMsg = "눈";
            break;
        case 4:
            retMsg = "소나기";
            break;
        default:
            retMsg = "";
            break;
        }
        return retMsg;
    }
    
}