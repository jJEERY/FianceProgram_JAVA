package com.java.fiance.entity;


/**
 * @author wuxincheng
 */
public class StockChosen {
    private String code;

    private String name;

    private boolean chosen = false;

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

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StockChosen)) {
            return false;
        }
        StockChosen stockChosen = (StockChosen)obj;
        if (this.name.equals(stockChosen.name) && this.code.equals(stockChosen.code)
                && this.chosen == stockChosen.chosen) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "StockChosen{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", chosen=" + chosen +
                '}';
    }
}
