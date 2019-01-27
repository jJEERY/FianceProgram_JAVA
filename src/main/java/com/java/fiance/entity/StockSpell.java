package com.java.fiance.entity;

/**
 * @author wuxincheng
 */
public class StockSpell {
    private String code;
    private String name;
    private String spell;

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

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    @Override
    public String toString() {
        return "StockSpell{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", spell='" + spell + '\'' +
                '}';
    }
}
