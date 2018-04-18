package com.linqinen.mho.bean;

/**
 * Created by lin on 2016/11/25.
 */

public class MyGuardStone {


    private String PinYin;
    private String FirstPinYin;

    private String skill_1;
    private String maxPrecisionCasting_1;
    private String floatingPrecisionCasting_1;

    private String skill_2;
    private String maxPrecisionCasting_2;
    private String floatingPrecisionCasting_2;

    private String skill_3;
    private String maxPrecisionCasting_3;
    private String floatingPrecisionCasting_3;

    public MyGuardStone(String skill_1, String maxPrecisionCasting_1, String floatingPrecisionCasting_1, String skill_2, String maxPrecisionCasting_2, String floatingPrecisionCasting_2, String skill_3, String maxPrecisionCasting_3, String floatingPrecisionCasting_3) {
        this.skill_1 = skill_1;
        this.maxPrecisionCasting_1 = maxPrecisionCasting_1;
        this.floatingPrecisionCasting_1 = floatingPrecisionCasting_1;
        this.skill_2 = skill_2;
        this.maxPrecisionCasting_2 = maxPrecisionCasting_2;
        this.floatingPrecisionCasting_2 = floatingPrecisionCasting_2;
        this.skill_3 = skill_3;
        this.maxPrecisionCasting_3 = maxPrecisionCasting_3;
        this.floatingPrecisionCasting_3 = floatingPrecisionCasting_3;
    }

    @Override
    public String toString() {
        return "MyGuardStone{" +
                "skill_1='" + skill_1 + '\'' +
                ", maxPrecisionCasting_1='" + maxPrecisionCasting_1 + '\'' +
                ", floatingPrecisionCasting_1='" + floatingPrecisionCasting_1 + '\'' +
                ", skill_2='" + skill_2 + '\'' +
                ", maxPrecisionCasting_2='" + maxPrecisionCasting_2 + '\'' +
                ", floatingPrecisionCasting_2='" + floatingPrecisionCasting_2 + '\'' +
                ", skill_3='" + skill_3 + '\'' +
                ", maxPrecisionCasting_3='" + maxPrecisionCasting_3 + '\'' +
                ", floatingPrecisionCasting_3='" + floatingPrecisionCasting_3 + '\'' +
                '}';
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

    public String getSkill_1() {
        return skill_1;
    }

    public void setSkill_1(String skill_1) {
        this.skill_1 = skill_1;
    }

    public String getMaxPrecisionCasting_1() {
        return maxPrecisionCasting_1;
    }

    public void setMaxPrecisionCasting_1(String maxPrecisionCasting_1) {
        this.maxPrecisionCasting_1 = maxPrecisionCasting_1;
    }

    public String getFloatingPrecisionCasting_1() {
        return floatingPrecisionCasting_1;
    }

    public void setFloatingPrecisionCasting_1(String floatingPrecisionCasting_1) {
        this.floatingPrecisionCasting_1 = floatingPrecisionCasting_1;
    }

    public String getSkill_2() {
        return skill_2;
    }

    public void setSkill_2(String skill_2) {
        this.skill_2 = skill_2;
    }

    public String getMaxPrecisionCasting_2() {
        return maxPrecisionCasting_2;
    }

    public void setMaxPrecisionCasting_2(String maxPrecisionCasting_2) {
        this.maxPrecisionCasting_2 = maxPrecisionCasting_2;
    }

    public String getFloatingPrecisionCasting_2() {
        return floatingPrecisionCasting_2;
    }

    public void setFloatingPrecisionCasting_2(String floatingPrecisionCasting_2) {
        this.floatingPrecisionCasting_2 = floatingPrecisionCasting_2;
    }

    public String getSkill_3() {
        return skill_3;
    }

    public void setSkill_3(String skill_3) {
        this.skill_3 = skill_3;
    }

    public String getMaxPrecisionCasting_3() {
        return maxPrecisionCasting_3;
    }

    public void setMaxPrecisionCasting_3(String maxPrecisionCasting_3) {
        this.maxPrecisionCasting_3 = maxPrecisionCasting_3;
    }

    public String getFloatingPrecisionCasting_3() {
        return floatingPrecisionCasting_3;
    }

    public void setFloatingPrecisionCasting_3(String floatingPrecisionCasting_3) {
        this.floatingPrecisionCasting_3 = floatingPrecisionCasting_3;
    }
}
