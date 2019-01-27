package com.java.fiance.entity;

/**
 * @author wuxincheng
 */
public class StockPredict {

    private String date;
    private String code;
    private String name;
    private double loss;

    private double high1;
    private double high2;
    private double high3;
    private double high4;
    private double high5;

    private double low1;
    private double low2;
    private double low3;
    private double low4;
    private double low5;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
    }

    public double getHigh1() {
        return high1;
    }

    public void setHigh1(double high1) {
        this.high1 = high1;
    }

    public double getHigh2() {
        return high2;
    }

    public void setHigh2(double high2) {
        this.high2 = high2;
    }

    public double getHigh3() {
        return high3;
    }

    public void setHigh3(double high3) {
        this.high3 = high3;
    }

    public double getHigh4() {
        return high4;
    }

    public void setHigh4(double high4) {
        this.high4 = high4;
    }

    public double getHigh5() {
        return high5;
    }

    public void setHigh5(double high5) {
        this.high5 = high5;
    }

    public double getLow1() {
        return low1;
    }

    public void setLow1(double low1) {
        this.low1 = low1;
    }

    public double getLow2() {
        return low2;
    }

    public void setLow2(double low2) {
        this.low2 = low2;
    }

    public double getLow3() {
        return low3;
    }

    public void setLow3(double low3) {
        this.low3 = low3;
    }

    public double getLow4() {
        return low4;
    }

    public void setLow4(double low4) {
        this.low4 = low4;
    }

    public double getLow5() {
        return low5;
    }

    public void setLow5(double low5) {
        this.low5 = low5;
    }

    @Override
    public String toString() {
        return "StockPredict{" +
                "date='" + date + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", loss=" + loss +
                ", high1=" + high1 +
                ", high2=" + high2 +
                ", high3=" + high3 +
                ", high4=" + high4 +
                ", high5=" + high5 +
                ", low1=" + low1 +
                ", low2=" + low2 +
                ", low3=" + low3 +
                ", low4=" + low4 +
                ", low5=" + low5 +
                '}';
    }
}
