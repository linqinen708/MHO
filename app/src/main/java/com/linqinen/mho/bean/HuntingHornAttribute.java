package com.linqinen.mho.bean;

/**
 * Created by lin on 2017/1/12.
 */

public class HuntingHornAttribute {

    String name;
    int level;
    String attribute;
    String skill_1;
    String skill_2;
    String skill_3;
    String skill_4;
    String skill_5;

    public HuntingHornAttribute(String name, int level, String attribute, String skill_1, String skill_2, String skill_3, String skill_4, String skill_5) {
        this.name = name;
        this.level = level;
        this.attribute = attribute;
        this.skill_1 = skill_1;
        this.skill_2 = skill_2;
        this.skill_3 = skill_3;
        this.skill_4 = skill_4;
        this.skill_5 = skill_5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getSkill_1() {
        return skill_1;
    }

    public void setSkill_1(String skill_1) {
        this.skill_1 = skill_1;
    }

    public String getSkill_2() {
        return skill_2;
    }

    public void setSkill_2(String skill_2) {
        this.skill_2 = skill_2;
    }

    public String getSkill_3() {
        return skill_3;
    }

    public void setSkill_3(String skill_3) {
        this.skill_3 = skill_3;
    }

    public String getSkill_4() {
        return skill_4;
    }

    public void setSkill_4(String skill_4) {
        this.skill_4 = skill_4;
    }

    public String getSkill_5() {
        return skill_5;
    }

    public void setSkill_5(String skill_5) {
        this.skill_5 = skill_5;
    }
}
