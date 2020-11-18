package webPractice.prime.model;

import java.io.Serializable;
import java.util.List;

public class PrimeData implements Serializable {
    private final int BOUND = 120;//倍数の表示個数
    private Integer x;
    private Integer y;
    private List<Integer> xPrime;
    private List<Integer> yPrime;
    private List<Integer> xDivisor;
    private List<Integer> yDivisor;
    private List<Integer> xMultiple;
    private List<Integer> yMultiple;
    private List<Integer> commonDivisor;
    private List<Integer> commonMulitiple;
    private String xResult;
    private String yResult;
    private String zResult;

    public PrimeData() {
        x = 0;
        y = 0;
//        xPrime = new ArrayList<Integer>();
//        yPrime = new ArrayList<Integer>();
//        xDivisor = new ArrayList<Integer>();
//        yDivisor = new ArrayList<Integer>();
//        xMultiple = new ArrayList<Integer>(BOUND);
//        yMultiple = new ArrayList<Integer>(BOUND);
//        commonDivisor = new ArrayList<Integer>();
//        commonMulitiple = new ArrayList<Integer>(BOUND);
        xResult = "";
        yResult = "";
        zResult = "";
    }

    public PrimeData(Integer x, Integer y) {
        this.x = x;
        this.y = y;
//        xPrime = new ArrayList<Integer>(x);
//        yPrime = new ArrayList<Integer>(y);
//        xDivisor = new ArrayList<Integer>(x);
//        yDivisor = new ArrayList<Integer>(y);
//        xMultiple = new ArrayList<Integer>(BOUND);
//        yMultiple = new ArrayList<Integer>(BOUND);
//        commonDivisor = new ArrayList<Integer>((int)Math.max(x, y));
//        commonMulitiple = new ArrayList<Integer>(BOUND);
        xResult = "";
        yResult = "";
        zResult = "";
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

    public List<Integer> getxPrime() {
        return xPrime;
    }

    public void setxPrime(List<Integer> xPrime) {
        this.xPrime = xPrime;
    }

    public List<Integer> getyPrime() {
        return yPrime;
    }

    public void setyPrime(List<Integer> yPrime) {
        this.yPrime = yPrime;
    }

    public List<Integer> getxDivisor() {
        return xDivisor;
    }

    public void setxDivisor(List<Integer> xDivisor) {
        this.xDivisor = xDivisor;
    }

    public List<Integer> getyDivisor() {
        return yDivisor;
    }

    public void setyDivisor(List<Integer> yDivisor) {
        this.yDivisor = yDivisor;
    }

    public List<Integer> getxMultiple() {
        return xMultiple;
    }

    public void setxMultiple(List<Integer> xMultiple) {
        this.xMultiple = xMultiple;
    }

    public List<Integer> getyMultiple() {
        return yMultiple;
    }

    public void setyMultiple(List<Integer> yMultiple) {
        this.yMultiple = yMultiple;
    }

    public List<Integer> getCommonDivisor() {
        return commonDivisor;
    }

    public void setCommonDivisor(List<Integer> commonDivisor) {
        this.commonDivisor = commonDivisor;
    }

    public List<Integer> getCommonMulitiple() {
        return commonMulitiple;
    }

    public void setCommonMulitiple(List<Integer> commonMulitiple) {
        this.commonMulitiple = commonMulitiple;
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
