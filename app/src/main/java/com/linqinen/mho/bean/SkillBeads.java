package com.linqinen.mho.bean;

/**
 * Created by lin on 2016/11/17.
 */

public class SkillBeads {
    private String name;

    private String positiveName;
    private String positiveAttribute_1;
    private String positiveAttribute_2;
    private String positiveAttribute_3;

    private String negativeName;
    private String tv_negativeAttribute_1;
    private String tv_negativeAttribute_2;
    private String tv_negativeAttribute_3;

    private String tv_materialName_1;
    private String tv_materialName_2;
    private String tv_materialName_3;

    private String PinYin;
    private String FirstPinYin;

    public SkillBeads(String name, String positiveName, String positiveAttribute_1, String positiveAttribute_2, String positiveAttribute_3, String negativeName, String tv_negativeAttribute_1, String tv_negativeAttribute_2, String tv_negativeAttribute_3, String tv_materialName_1, String tv_materialName_2, String tv_materialName_3) {
        this.name = name;
        this.positiveName = positiveName;
        this.positiveAttribute_1 = positiveAttribute_1;
        this.positiveAttribute_2 = positiveAttribute_2;
        this.positiveAttribute_3 = positiveAttribute_3;
        this.negativeName = negativeName;
        this.tv_negativeAttribute_1 = tv_negativeAttribute_1;
        this.tv_negativeAttribute_2 = tv_negativeAttribute_2;
        this.tv_negativeAttribute_3 = tv_negativeAttribute_3;
        this.tv_materialName_1 = tv_materialName_1;
        this.tv_materialName_2 = tv_materialName_2;
        this.tv_materialName_3 = tv_materialName_3;
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

    public String getPositiveName() {
        return positiveName;
    }

    public void setPositiveName(String positiveName) {
        this.positiveName = positiveName;
    }

    public String getPositiveAttribute_1() {
        return positiveAttribute_1;
    }

    public void setPositiveAttribute_1(String positiveAttribute_1) {
        this.positiveAttribute_1 = positiveAttribute_1;
    }

    public String getPositiveAttribute_2() {
        return positiveAttribute_2;
    }

    public void setPositiveAttribute_2(String positiveAttribute_2) {
        this.positiveAttribute_2 = positiveAttribute_2;
    }

    public String getPositiveAttribute_3() {
        return positiveAttribute_3;
    }

    public void setPositiveAttribute_3(String positiveAttribute_3) {
        this.positiveAttribute_3 = positiveAttribute_3;
    }

    public String getNegativeName() {
        return negativeName;
    }

    public void setNegativeName(String negativeName) {
        this.negativeName = negativeName;
    }

    public String getTv_negativeAttribute_1() {
        return tv_negativeAttribute_1;
    }

    public void setTv_negativeAttribute_1(String tv_negativeAttribute_1) {
        this.tv_negativeAttribute_1 = tv_negativeAttribute_1;
    }

    public String getTv_negativeAttribute_2() {
        return tv_negativeAttribute_2;
    }

    public void setTv_negativeAttribute_2(String tv_negativeAttribute_2) {
        this.tv_negativeAttribute_2 = tv_negativeAttribute_2;
    }

    public String getTv_negativeAttribute_3() {
        return tv_negativeAttribute_3;
    }

    public void setTv_negativeAttribute_3(String tv_negativeAttribute_3) {
        this.tv_negativeAttribute_3 = tv_negativeAttribute_3;
    }

    public String getTv_materialName_1() {
        return tv_materialName_1;
    }

    public void setTv_materialName_1(String tv_materialName_1) {
        this.tv_materialName_1 = tv_materialName_1;
    }

    public String getTv_materialName_2() {
        return tv_materialName_2;
    }

    public void setTv_materialName_2(String tv_materialName_2) {
        this.tv_materialName_2 = tv_materialName_2;
    }

    public String getTv_materialName_3() {
        return tv_materialName_3;
    }

    public void setTv_materialName_3(String tv_materialName_3) {
        this.tv_materialName_3 = tv_materialName_3;
    }
}
