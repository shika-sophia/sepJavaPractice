package webPractice.webJanken.model;

import java.util.ArrayList;
import java.util.List;

public class WebJankenData {
    private String userHand;
    private String comHand;
    private String result;
    private int winNum;
    private double winRate;
    private List<String> winList;


    public WebJankenData() {
        this.userHand = "";
        this.comHand = "";
        this.result = "";
        this.winNum = 0;
        this.winRate = 0d;
        winList = new ArrayList<String>();

    }

    //====== getter, setter ======
    public String getUserHand() {
        return userHand;
    }

    public void setUserHand(String userHand) {
        this.userHand = userHand;
    }

    public String getComHand() {
        return comHand;
    }

    public void setComHand(String comHand) {
        this.comHand = comHand;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getWinNum() {
        return winNum;
    }

    public void setWinNum(int winNum) {
        this.winNum = winNum;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public List<String> getWinList() {
        return winList;
    }

    public void setWinList(List<String> winList) {
        this.winList = winList;
    }

    public void setWinList(String win) {
        this.winList.add(win);
    }
}//class
