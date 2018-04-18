package com.linqinen.mho.bean;

/**
 * Created by lin on 2016/11/25.
 */

public class GuardStone {

    String name;

    private String PinYin;
    private String FirstPinYin;

    String value_queen;
    String price_queen;
    /**精铸*/
    String precisionCasting_queen;
    String value_dragon;
    String price_dragon;
    String precisionCasting_dragon;
    String value_hero;
    String price_hero;
    String precisionCasting_hero;
    String value_legend;
    String price_legend;
    String precisionCasting_legend;

    public GuardStone(String name, String value_queen, String price_queen, String precisionCasting_queen, String value_dragon, String price_dragon, String precisionCasting_dragon, String value_hero, String price_hero, String precisionCasting_hero, String value_legend, String price_legend, String precisionCasting_legend) {
        this.name = name;
        this.value_queen = value_queen;
        this.price_queen = price_queen;
        this.precisionCasting_queen = precisionCasting_queen;
        this.value_dragon = value_dragon;
        this.price_dragon = price_dragon;
        this.precisionCasting_dragon = precisionCasting_dragon;
        this.value_hero = value_hero;
        this.price_hero = price_hero;
        this.precisionCasting_hero = precisionCasting_hero;
        this.value_legend = value_legend;
        this.price_legend = price_legend;
        this.precisionCasting_legend = precisionCasting_legend;
    }

    public String getPinYin() {
        return PinYin;
    }

    public void setPinYin(String pinYin) {
        PinYin = pinYin;
    }

    public String getFirstPinYin() {
        return FirstPinYin;
    }

    public void setFirstPinYin(String firstPinYin) {
        FirstPinYin = firstPinYin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue_queen() {
        return value_queen;
    }

    public void setValue_queen(String value_queen) {
        this.value_queen = value_queen;
    }

    public String getPrice_queen() {
        return price_queen;
    }

    public void setPrice_queen(String price_queen) {
        this.price_queen = price_queen;
    }

    public String getPrecisionCasting_queen() {
        return precisionCasting_queen;
    }

    public void setPrecisionCasting_queen(String precisionCasting_queen) {
        this.precisionCasting_queen = precisionCasting_queen;
    }

    public String getValue_dragon() {
        return value_dragon;
    }

    public void setValue_dragon(String value_dragon) {
        this.value_dragon = value_dragon;
    }

    public String getPrice_dragon() {
        return price_dragon;
    }

    public void setPrice_dragon(String price_dragon) {
        this.price_dragon = price_dragon;
    }

    public String getPrecisionCasting_dragon() {
        return precisionCasting_dragon;
    }

    public void setPrecisionCasting_dragon(String precisionCasting_dragon) {
        this.precisionCasting_dragon = precisionCasting_dragon;
    }

    public String getValue_hero() {
        return value_hero;
    }

    public void setValue_hero(String value_hero) {
        this.value_hero = value_hero;
    }

    public String getPrice_hero() {
        return price_hero;
    }

    public void setPrice_hero(String price_hero) {
        this.price_hero = price_hero;
    }

    public String getPrecisionCasting_hero() {
        return precisionCasting_hero;
    }

    public void setPrecisionCasting_hero(String precisionCasting_hero) {
        this.precisionCasting_hero = precisionCasting_hero;
    }

    public String getValue_legend() {
        return value_legend;
    }

    public void setValue_legend(String value_legend) {
        this.value_legend = value_legend;
    }

    public String getPrice_legend() {
        return price_legend;
    }

    public void setPrice_legend(String price_legend) {
        this.price_legend = price_legend;
    }

    public String getPrecisionCasting_legend() {
        return precisionCasting_legend;
    }

    public void setPrecisionCasting_legend(String precisionCasting_legend) {
        this.precisionCasting_legend = precisionCasting_legend;
    }
}
