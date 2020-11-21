package webPractice.prime.model;

import java.io.Serializable;

public class PrimeData implements Serializable {
    private final int BOUND = 120;//倍数の表示個数
    private Integer x;
    private Integer y;
    private String xResult;
    private String yResult;
    private String zResult;

    {
        xResult = "";
        yResult = "";
        zResult = "";

    }

    public PrimeData() {
        x = 0;
        y = 0;
    }

    public PrimeData(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    //====== getter, setter ======
    public int getBOUND() {
        return BOUND;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getxResult() {
        return xResult;
    }

    public void setxResult(String xResult) {
        this.xResult = xResult;
    }

    public String getyResult() {
        return yResult;
    }

    public void setyResult(String yResult) {
        this.yResult = yResult;
    }

    public String getzResult() {
        return zResult;
    }

    public void setzResult(String zResult) {
        this.zResult = zResult;
    }

}//class
