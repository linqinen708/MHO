package com.linqinen.mho.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lin on 2016/11/11.
 */

public class SuitSkillAttribute implements Parcelable {
    private String name;

    private String armorLevel;

    private String PinYin;
    private String FirstPinYin;



    private String skillName_1;
    private String skillName_2;
    private String skillName_3;
    private String skillName_4;
    private String skillName_5;

    private String head_hole;
    private String headSkill_1;
    private String headSkill_2;
    private String headSkill_3;
    private String headSkill_4;
    private String headSkill_5;
    private int isPossessHead;

    private String hand_hole;
    private String handSkill_1;
    private String handSkill_2;
    private String handSkill_3;
    private String handSkill_4;
    private String handSkill_5;
    private int isPossessHand;

    private String clothes_hole;
    private String clothesSkill_1;
    private String clothesSkill_2;
    private String clothesSkill_3;
    private String clothesSkill_4;
    private String clothesSkill_5;
    private int isPossessClothes;

    private String waist_hole;
    private String waistSkill_1;
    private String waistSkill_2;
    private String waistSkill_3;
    private String waistSkill_4;
    private String waistSkill_5;
    private int isPossessWaist;

    private String foot_hole;
    private String footSkill_1;
    private String footSkill_2;
    private String footSkill_3;
    private String footSkill_4;
    private String footSkill_5;
    private int isPossessFoot;

    private String materialName_1;
    private String materialName_2;
    private String materialName_3;
    private String materialName_4;
    private String materialName_5;
    private String materialName_6;

    private String headMaterial_1;
    private String headMaterial_2;
    private String headMaterial_3;
    private String headMaterial_4;
    private String headMaterial_5;
    private String headMaterial_6;

    private String handMaterial_1;
    private String handMaterial_2;
    private String handMaterial_3;
    private String handMaterial_4;
    private String handMaterial_5;
    private String handMaterial_6;

    private String clothesMaterial_1;
    private String clothesMaterial_2;
    private String clothesMaterial_3;
    private String clothesMaterial_4;
    private String clothesMaterial_5;
    private String clothesMaterial_6;

    private String waistMaterial_1;
    private String waistMaterial_2;
    private String waistMaterial_3;
    private String waistMaterial_4;
    private String waistMaterial_5;
    private String waistMaterial_6;

    private String footMaterial_1;
    private String footMaterial_2;
    private String footMaterial_3;
    private String footMaterial_4;
    private String footMaterial_5;
    private String footMaterial_6;

    public SuitSkillAttribute(){

    }

    public SuitSkillAttribute(String name, String armorLevel, String skillName_1, String skillName_2, String skillName_3, String skillName_4, String skillName_5, String head_hole, String headSkill_1, String headSkill_2, String headSkill_3, String headSkill_4, String headSkill_5, int isPossessHead, String hand_hole, String handSkill_1, String handSkill_2, String handSkill_3, String handSkill_4, String handSkill_5, int isPossessHand, String clothes_hole, String clothesSkill_1, String clothesSkill_2, String clothesSkill_3, String clothesSkill_4, String clothesSkill_5, int isPossessClothes, String waist_hole, String waistSkill_1, String waistSkill_2, String waistSkill_3, String waistSkill_4, String waistSkill_5, int isPossessWaist, String foot_hole, String footSkill_1, String footSkill_2, String footSkill_3, String footSkill_4, String footSkill_5, int isPossessFoot, String materialName_1, String materialName_2, String materialName_3, String materialName_4, String materialName_5, String materialName_6, String headMaterial_1, String headMaterial_2, String headMaterial_3, String headMaterial_4, String headMaterial_5, String headMaterial_6, String handMaterial_1, String handMaterial_2, String handMaterial_3, String handMaterial_4, String handMaterial_5, String handMaterial_6, String clothesMaterial_1, String clothesMaterial_2, String clothesMaterial_3, String clothesMaterial_4, String clothesMaterial_5, String clothesMaterial_6, String waistMaterial_1, String waistMaterial_2, String waistMaterial_3, String waistMaterial_4, String waistMaterial_5, String waistMaterial_6, String footMaterial_1, String footMaterial_2, String footMaterial_3, String footMaterial_4, String footMaterial_5, String footMaterial_6) {
        this.name = name;
        this.armorLevel = armorLevel;
        this.skillName_1 = skillName_1;
        this.skillName_2 = skillName_2;
        this.skillName_3 = skillName_3;
        this.skillName_4 = skillName_4;
        this.skillName_5 = skillName_5;
        this.head_hole = head_hole;
        this.headSkill_1 = headSkill_1;
        this.headSkill_2 = headSkill_2;
        this.headSkill_3 = headSkill_3;
        this.headSkill_4 = headSkill_4;
        this.headSkill_5 = headSkill_5;
        this.isPossessHead = isPossessHead;
        this.hand_hole = hand_hole;
        this.handSkill_1 = handSkill_1;
        this.handSkill_2 = handSkill_2;
        this.handSkill_3 = handSkill_3;
        this.handSkill_4 = handSkill_4;
        this.handSkill_5 = handSkill_5;
        this.isPossessHand = isPossessHand;
        this.clothes_hole = clothes_hole;
        this.clothesSkill_1 = clothesSkill_1;
        this.clothesSkill_2 = clothesSkill_2;
        this.clothesSkill_3 = clothesSkill_3;
        this.clothesSkill_4 = clothesSkill_4;
        this.clothesSkill_5 = clothesSkill_5;
        this.isPossessClothes = isPossessClothes;
        this.waist_hole = waist_hole;
        this.waistSkill_1 = waistSkill_1;
        this.waistSkill_2 = waistSkill_2;
        this.waistSkill_3 = waistSkill_3;
        this.waistSkill_4 = waistSkill_4;
        this.waistSkill_5 = waistSkill_5;
        this.isPossessWaist = isPossessWaist;
        this.foot_hole = foot_hole;
        this.footSkill_1 = footSkill_1;
        this.footSkill_2 = footSkill_2;
        this.footSkill_3 = footSkill_3;
        this.footSkill_4 = footSkill_4;
        this.footSkill_5 = footSkill_5;
        this.isPossessFoot = isPossessFoot;
        this.materialName_1 = materialName_1;
        this.materialName_2 = materialName_2;
        this.materialName_3 = materialName_3;
        this.materialName_4 = materialName_4;
        this.materialName_5 = materialName_5;
        this.materialName_6 = materialName_6;
        this.headMaterial_1 = headMaterial_1;
        this.headMaterial_2 = headMaterial_2;
        this.headMaterial_3 = headMaterial_3;
        this.headMaterial_4 = headMaterial_4;
        this.headMaterial_5 = headMaterial_5;
        this.headMaterial_6 = headMaterial_6;
        this.handMaterial_1 = handMaterial_1;
        this.handMaterial_2 = handMaterial_2;
        this.handMaterial_3 = handMaterial_3;
        this.handMaterial_4 = handMaterial_4;
        this.handMaterial_5 = handMaterial_5;
        this.handMaterial_6 = handMaterial_6;
        this.clothesMaterial_1 = clothesMaterial_1;
        this.clothesMaterial_2 = clothesMaterial_2;
        this.clothesMaterial_3 = clothesMaterial_3;
        this.clothesMaterial_4 = clothesMaterial_4;
        this.clothesMaterial_5 = clothesMaterial_5;
        this.clothesMaterial_6 = clothesMaterial_6;
        this.waistMaterial_1 = waistMaterial_1;
        this.waistMaterial_2 = waistMaterial_2;
        this.waistMaterial_3 = waistMaterial_3;
        this.waistMaterial_4 = waistMaterial_4;
        this.waistMaterial_5 = waistMaterial_5;
        this.waistMaterial_6 = waistMaterial_6;
        this.footMaterial_1 = footMaterial_1;
        this.footMaterial_2 = footMaterial_2;
        this.footMaterial_3 = footMaterial_3;
        this.footMaterial_4 = footMaterial_4;
        this.footMaterial_5 = footMaterial_5;
        this.footMaterial_6 = footMaterial_6;
    }

    @Override
    public String toString() {
        return "SuitSkillAttribute{" +
                "name='" + name + '\'' +
                ", armorLevel='" + armorLevel + '\'' +
                ", skillName_1='" + skillName_1 + '\'' +
                ", skillName_2='" + skillName_2 + '\'' +
                ", skillName_3='" + skillName_3 + '\'' +
                ", skillName_4='" + skillName_4 + '\'' +
                ", skillName_5='" + skillName_5 + '\'' +
                ", head_hole='" + head_hole + '\'' +
                ", headSkill_1='" + headSkill_1 + '\'' +
                ", headSkill_2='" + headSkill_2 + '\'' +
                ", headSkill_3='" + headSkill_3 + '\'' +
                ", headSkill_4='" + headSkill_4 + '\'' +
                ", headSkill_5='" + headSkill_5 + '\'' +
                ", isPossessHead=" + isPossessHead +
                ", hand_hole='" + hand_hole + '\'' +
                ", handSkill_1='" + handSkill_1 + '\'' +
                ", handSkill_2='" + handSkill_2 + '\'' +
                ", handSkill_3='" + handSkill_3 + '\'' +
                ", handSkill_4='" + handSkill_4 + '\'' +
                ", handSkill_5='" + handSkill_5 + '\'' +
                ", isPossessHand=" + isPossessHand +
                ", clothes_hole='" + clothes_hole + '\'' +
                ", clothesSkill_1='" + clothesSkill_1 + '\'' +
                ", clothesSkill_2='" + clothesSkill_2 + '\'' +
                ", clothesSkill_3='" + clothesSkill_3 + '\'' +
                ", clothesSkill_4='" + clothesSkill_4 + '\'' +
                ", clothesSkill_5='" + clothesSkill_5 + '\'' +
                ", isPossessClothes=" + isPossessClothes +
                ", waist_hole='" + waist_hole + '\'' +
                ", waistSkill_1='" + waistSkill_1 + '\'' +
                ", waistSkill_2='" + waistSkill_2 + '\'' +
                ", waistSkill_3='" + waistSkill_3 + '\'' +
                ", waistSkill_4='" + waistSkill_4 + '\'' +
                ", waistSkill_5='" + waistSkill_5 + '\'' +
                ", isPossessWaist=" + isPossessWaist +
                ", foot_hole='" + foot_hole + '\'' +
                ", footSkill_1='" + footSkill_1 + '\'' +
                ", footSkill_2='" + footSkill_2 + '\'' +
                ", footSkill_3='" + footSkill_3 + '\'' +
                ", footSkill_4='" + footSkill_4 + '\'' +
                ", footSkill_5='" + footSkill_5 + '\'' +
                ", isPossessFoot=" + isPossessFoot +
                ", materialName_1='" + materialName_1 + '\'' +
                ", materialName_2='" + materialName_2 + '\'' +
                ", materialName_3='" + materialName_3 + '\'' +
                ", materialName_4='" + materialName_4 + '\'' +
                ", materialName_5='" + materialName_5 + '\'' +
                ", materialName_6='" + materialName_6 + '\'' +
                ", headMaterial_1='" + headMaterial_1 + '\'' +
                ", headMaterial_2='" + headMaterial_2 + '\'' +
                ", headMaterial_3='" + headMaterial_3 + '\'' +
                ", headMaterial_4='" + headMaterial_4 + '\'' +
                ", headMaterial_5='" + headMaterial_5 + '\'' +
                ", headMaterial_6='" + headMaterial_6 + '\'' +
                ", handMaterial_1='" + handMaterial_1 + '\'' +
                ", handMaterial_2='" + handMaterial_2 + '\'' +
                ", handMaterial_3='" + handMaterial_3 + '\'' +
                ", handMaterial_4='" + handMaterial_4 + '\'' +
                ", handMaterial_5='" + handMaterial_5 + '\'' +
                ", handMaterial_6='" + handMaterial_6 + '\'' +
                ", clothesMaterial_1='" + clothesMaterial_1 + '\'' +
                ", clothesMaterial_2='" + clothesMaterial_2 + '\'' +
                ", clothesMaterial_3='" + clothesMaterial_3 + '\'' +
                ", clothesMaterial_4='" + clothesMaterial_4 + '\'' +
                ", clothesMaterial_5='" + clothesMaterial_5 + '\'' +
                ", clothesMaterial_6='" + clothesMaterial_6 + '\'' +
                ", waistMaterial_1='" + waistMaterial_1 + '\'' +
                ", waistMaterial_2='" + waistMaterial_2 + '\'' +
                ", waistMaterial_3='" + waistMaterial_3 + '\'' +
                ", waistMaterial_4='" + waistMaterial_4 + '\'' +
                ", waistMaterial_5='" + waistMaterial_5 + '\'' +
                ", waistMaterial_6='" + waistMaterial_6 + '\'' +
                ", footMaterial_1='" + footMaterial_1 + '\'' +
                ", footMaterial_2='" + footMaterial_2 + '\'' +
                ", footMaterial_3='" + footMaterial_3 + '\'' +
                ", footMaterial_4='" + footMaterial_4 + '\'' +
                ", footMaterial_5='" + footMaterial_5 + '\'' +
                ", footMaterial_6='" + footMaterial_6 + '\'' +
                '}';
    }

    public int getIsPossessHead() {
        return isPossessHead;
    }

    public void setIsPossessHead(int isPossessHead) {
        this.isPossessHead = isPossessHead;
    }

    public int getIsPossessHand() {
        return isPossessHand;
    }

    public void setIsPossessHand(int isPossessHand) {
        this.isPossessHand = isPossessHand;
    }

    public int getIsPossessClothes() {
        return isPossessClothes;
    }

    public void setIsPossessClothes(int isPossessClothes) {
        this.isPossessClothes = isPossessClothes;
    }

    public int getIsPossessWaist() {
        return isPossessWaist;
    }

    public void setIsPossessWaist(int isPossessWaist) {
        this.isPossessWaist = isPossessWaist;
    }

    public int getIsPossessFoot() {
        return isPossessFoot;
    }

    public void setIsPossessFoot(int isPossessFoot) {
        this.isPossessFoot = isPossessFoot;
    }

    public String getArmorLevel() {
        return armorLevel;
    }

    public void setArmorLevel(String armorLevel) {
        this.armorLevel = armorLevel;
    }

    public String getMaterialName_6() {
        return materialName_6;
    }

    public void setMaterialName_6(String materialName_6) {
        this.materialName_6 = materialName_6;
    }

    public String getHeadMaterial_6() {
        return headMaterial_6;
    }

    public void setHeadMaterial_6(String headMaterial_6) {
        this.headMaterial_6 = headMaterial_6;
    }

    public String getHandMaterial_6() {
        return handMaterial_6;
    }

    public void setHandMaterial_6(String handMaterial_6) {
        this.handMaterial_6 = handMaterial_6;
    }

    public String getClothesMaterial_6() {
        return clothesMaterial_6;
    }

    public void setClothesMaterial_6(String clothesMaterial_6) {
        this.clothesMaterial_6 = clothesMaterial_6;
    }

    public String getWaistMaterial_6() {
        return waistMaterial_6;
    }

    public void setWaistMaterial_6(String waistMaterial_6) {
        this.waistMaterial_6 = waistMaterial_6;
    }

    public String getFootMaterial_6() {
        return footMaterial_6;
    }

    public void setFootMaterial_6(String footMaterial_6) {
        this.footMaterial_6 = footMaterial_6;
    }

    public String getMaterialName_1() {
        return materialName_1;
    }

    public void setMaterialName_1(String materialName_1) {
        this.materialName_1 = materialName_1;
    }

    public String getMaterialName_2() {
        return materialName_2;
    }

    public void setMaterialName_2(String materialName_2) {
        this.materialName_2 = materialName_2;
    }

    public String getMaterialName_3() {
        return materialName_3;
    }

    public void setMaterialName_3(String materialName_3) {
        this.materialName_3 = materialName_3;
    }

    public String getMaterialName_4() {
        return materialName_4;
    }

    public void setMaterialName_4(String materialName_4) {
        this.materialName_4 = materialName_4;
    }

    public String getMaterialName_5() {
        return materialName_5;
    }

    public void setMaterialName_5(String materialName_5) {
        this.materialName_5 = materialName_5;
    }

    public String getHeadMaterial_1() {
        return headMaterial_1;
    }

    public void setHeadMaterial_1(String headMaterial_1) {
        this.headMaterial_1 = headMaterial_1;
    }

    public String getHeadMaterial_2() {
        return headMaterial_2;
    }

    public void setHeadMaterial_2(String headMaterial_2) {
        this.headMaterial_2 = headMaterial_2;
    }

    public String getHeadMaterial_3() {
        return headMaterial_3;
    }

    public void setHeadMaterial_3(String headMaterial_3) {
        this.headMaterial_3 = headMaterial_3;
    }

    public String getHeadMaterial_4() {
        return headMaterial_4;
    }

    public void setHeadMaterial_4(String headMaterial_4) {
        this.headMaterial_4 = headMaterial_4;
    }

    public String getHeadMaterial_5() {
        return headMaterial_5;
    }

    public void setHeadMaterial_5(String headMaterial_5) {
        this.headMaterial_5 = headMaterial_5;
    }

    public String getHandMaterial_1() {
        return handMaterial_1;
    }

    public void setHandMaterial_1(String handMaterial_1) {
        this.handMaterial_1 = handMaterial_1;
    }

    public String getHandMaterial_2() {
        return handMaterial_2;
    }

    public void setHandMaterial_2(String handMaterial_2) {
        this.handMaterial_2 = handMaterial_2;
    }

    public String getHandMaterial_3() {
        return handMaterial_3;
    }

    public void setHandMaterial_3(String handMaterial_3) {
        this.handMaterial_3 = handMaterial_3;
    }

    public String getHandMaterial_4() {
        return handMaterial_4;
    }

    public void setHandMaterial_4(String handMaterial_4) {
        this.handMaterial_4 = handMaterial_4;
    }

    public String getHandMaterial_5() {
        return handMaterial_5;
    }

    public void setHandMaterial_5(String handMaterial_5) {
        this.handMaterial_5 = handMaterial_5;
    }

    public String getClothesMaterial_1() {
        return clothesMaterial_1;
    }

    public void setClothesMaterial_1(String clothesMaterial_1) {
        this.clothesMaterial_1 = clothesMaterial_1;
    }

    public String getClothesMaterial_2() {
        return clothesMaterial_2;
    }

    public void setClothesMaterial_2(String clothesMaterial_2) {
        this.clothesMaterial_2 = clothesMaterial_2;
    }

    public String getClothesMaterial_3() {
        return clothesMaterial_3;
    }

    public void setClothesMaterial_3(String clothesMaterial_3) {
        this.clothesMaterial_3 = clothesMaterial_3;
    }

    public String getClothesMaterial_4() {
        return clothesMaterial_4;
    }

    public void setClothesMaterial_4(String clothesMaterial_4) {
        this.clothesMaterial_4 = clothesMaterial_4;
    }

    public String getClothesMaterial_5() {
        return clothesMaterial_5;
    }

    public void setClothesMaterial_5(String clothesMaterial_5) {
        this.clothesMaterial_5 = clothesMaterial_5;
    }

    public String getWaistMaterial_1() {
        return waistMaterial_1;
    }

    public void setWaistMaterial_1(String waistMaterial_1) {
        this.waistMaterial_1 = waistMaterial_1;
    }

    public String getWaistMaterial_2() {
        return waistMaterial_2;
    }

    public void setWaistMaterial_2(String waistMaterial_2) {
        this.waistMaterial_2 = waistMaterial_2;
    }

    public String getWaistMaterial_3() {
        return waistMaterial_3;
    }

    public void setWaistMaterial_3(String waistMaterial_3) {
        this.waistMaterial_3 = waistMaterial_3;
    }

    public String getWaistMaterial_4() {
        return waistMaterial_4;
    }

    public void setWaistMaterial_4(String waistMaterial_4) {
        this.waistMaterial_4 = waistMaterial_4;
    }

    public String getWaistMaterial_5() {
        return waistMaterial_5;
    }

    public void setWaistMaterial_5(String waistMaterial_5) {
        this.waistMaterial_5 = waistMaterial_5;
    }

    public String getFootMaterial_1() {
        return footMaterial_1;
    }

    public void setFootMaterial_1(String footMaterial_1) {
        this.footMaterial_1 = footMaterial_1;
    }

    public String getFootMaterial_2() {
        return footMaterial_2;
    }

    public void setFootMaterial_2(String footMaterial_2) {
        this.footMaterial_2 = footMaterial_2;
    }

    public String getFootMaterial_3() {
        return footMaterial_3;
    }

    public void setFootMaterial_3(String footMaterial_3) {
        this.footMaterial_3 = footMaterial_3;
    }

    public String getFootMaterial_4() {
        return footMaterial_4;
    }

    public void setFootMaterial_4(String footMaterial_4) {
        this.footMaterial_4 = footMaterial_4;
    }

    public String getFootMaterial_5() {
        return footMaterial_5;
    }

    public void setFootMaterial_5(String footMaterial_5) {
        this.footMaterial_5 = footMaterial_5;
    }

    public String getHead_hole() {
        return head_hole;
    }

    public void setHead_hole(String head_hole) {
        this.head_hole = head_hole;
    }

    public String getHand_hole() {
        return hand_hole;
    }

    public void setHand_hole(String hand_hole) {
        this.hand_hole = hand_hole;
    }

    public String getClothes_hole() {
        return clothes_hole;
    }

    public void setClothes_hole(String clothes_hole) {
        this.clothes_hole = clothes_hole;
    }

    public String getWaist_hole() {
        return waist_hole;
    }

    public void setWaist_hole(String waist_hole) {
        this.waist_hole = waist_hole;
    }

    public String getFoot_hole() {
        return foot_hole;
    }

    public void setFoot_hole(String foot_hole) {
        this.foot_hole = foot_hole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkillName_1() {
        return skillName_1;
    }

    public void setSkillName_1(String skillName_1) {
        this.skillName_1 = skillName_1;
    }

    public String getSkillName_2() {
        return skillName_2;
    }

    public void setSkillName_2(String skillName_2) {
        this.skillName_2 = skillName_2;
    }

    public String getSkillName_3() {
        return skillName_3;
    }

    public void setSkillName_3(String skillName_3) {
        this.skillName_3 = skillName_3;
    }

    public String getSkillName_4() {
        return skillName_4;
    }

    public void setSkillName_4(String skillName_4) {
        this.skillName_4 = skillName_4;
    }

    public String getSkillName_5() {
        return skillName_5;
    }

    public void setSkillName_5(String skillName_5) {
        this.skillName_5 = skillName_5;
    }

    public String getHeadSkill_1() {
        return headSkill_1;
    }

    public void setHeadSkill_1(String headSkill_1) {
        this.headSkill_1 = headSkill_1;
    }

    public String getHeadSkill_2() {
        return headSkill_2;
    }

    public void setHeadSkill_2(String headSkill_2) {
        this.headSkill_2 = headSkill_2;
    }

    public String getHeadSkill_3() {
        return headSkill_3;
    }

    public void setHeadSkill_3(String headSkill_3) {
        this.headSkill_3 = headSkill_3;
    }

    public String getHeadSkill_4() {
        return headSkill_4;
    }

    public void setHeadSkill_4(String headSkill_4) {
        this.headSkill_4 = headSkill_4;
    }

    public String getHeadSkill_5() {
        return headSkill_5;
    }

    public void setHeadSkill_5(String headSkill_5) {
        this.headSkill_5 = headSkill_5;
    }

    public String getHandSkill_1() {
        return handSkill_1;
    }

    public void setHandSkill_1(String handSkill_1) {
        this.handSkill_1 = handSkill_1;
    }

    public String getHandSkill_2() {
        return handSkill_2;
    }

    public void setHandSkill_2(String handSkill_2) {
        this.handSkill_2 = handSkill_2;
    }

    public String getHandSkill_3() {
        return handSkill_3;
    }

    public void setHandSkill_3(String handSkill_3) {
        this.handSkill_3 = handSkill_3;
    }

    public String getHandSkill_4() {
        return handSkill_4;
    }

    public void setHandSkill_4(String handSkill_4) {
        this.handSkill_4 = handSkill_4;
    }

    public String getHandSkill_5() {
        return handSkill_5;
    }

    public void setHandSkill_5(String handSkill_5) {
        this.handSkill_5 = handSkill_5;
    }

    public String getClothesSkill_1() {
        return clothesSkill_1;
    }

    public void setClothesSkill_1(String clothesSkill_1) {
        this.clothesSkill_1 = clothesSkill_1;
    }

    public String getClothesSkill_2() {
        return clothesSkill_2;
    }

    public void setClothesSkill_2(String clothesSkill_2) {
        this.clothesSkill_2 = clothesSkill_2;
    }

    public String getClothesSkill_3() {
        return clothesSkill_3;
    }

    public void setClothesSkill_3(String clothesSkill_3) {
        this.clothesSkill_3 = clothesSkill_3;
    }

    public String getClothesSkill_4() {
        return clothesSkill_4;
    }

    public void setClothesSkill_4(String clothesSkill_4) {
        this.clothesSkill_4 = clothesSkill_4;
    }

    public String getClothesSkill_5() {
        return clothesSkill_5;
    }

    public void setClothesSkill_5(String clothesSkill_5) {
        this.clothesSkill_5 = clothesSkill_5;
    }

    public String getWaistSkill_1() {
        return waistSkill_1;
    }

    public void setWaistSkill_1(String waistSkill_1) {
        this.waistSkill_1 = waistSkill_1;
    }

    public String getWaistSkill_2() {
        return waistSkill_2;
    }

    public void setWaistSkill_2(String waistSkill_2) {
        this.waistSkill_2 = waistSkill_2;
    }

    public String getWaistSkill_3() {
        return waistSkill_3;
    }

    public void setWaistSkill_3(String waistSkill_3) {
        this.waistSkill_3 = waistSkill_3;
    }

    public String getWaistSkill_4() {
        return waistSkill_4;
    }

    public void setWaistSkill_4(String waistSkill_4) {
        this.waistSkill_4 = waistSkill_4;
    }

    public String getWaistSkill_5() {
        return waistSkill_5;
    }

    public void setWaistSkill_5(String waistSkill_5) {
        this.waistSkill_5 = waistSkill_5;
    }

    public String getFootSkill_1() {
        return footSkill_1;
    }

    public void setFootSkill_1(String footSkill_1) {
        this.footSkill_1 = footSkill_1;
    }

    public String getFootSkill_2() {
        return footSkill_2;
    }

    public void setFootSkill_2(String footSkill_2) {
        this.footSkill_2 = footSkill_2;
    }

    public String getFootSkill_3() {
        return footSkill_3;
    }

    public void setFootSkill_3(String footSkill_3) {
        this.footSkill_3 = footSkill_3;
    }

    public String getFootSkill_4() {
        return footSkill_4;
    }

    public void setFootSkill_4(String footSkill_4) {
        this.footSkill_4 = footSkill_4;
    }

    public String getFootSkill_5() {
        return footSkill_5;
    }

    public void setFootSkill_5(String footSkill_5) {
        this.footSkill_5 = footSkill_5;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.armorLevel);
        dest.writeString(this.PinYin);
        dest.writeString(this.FirstPinYin);
        dest.writeString(this.skillName_1);
        dest.writeString(this.skillName_2);
        dest.writeString(this.skillName_3);
        dest.writeString(this.skillName_4);
        dest.writeString(this.skillName_5);
        dest.writeString(this.head_hole);
        dest.writeString(this.headSkill_1);
        dest.writeString(this.headSkill_2);
        dest.writeString(this.headSkill_3);
        dest.writeString(this.headSkill_4);
        dest.writeString(this.headSkill_5);
        dest.writeString(this.hand_hole);
        dest.writeString(this.handSkill_1);
        dest.writeString(this.handSkill_2);
        dest.writeString(this.handSkill_3);
        dest.writeString(this.handSkill_4);
        dest.writeString(this.handSkill_5);
        dest.writeString(this.clothes_hole);
        dest.writeString(this.clothesSkill_1);
        dest.writeString(this.clothesSkill_2);
        dest.writeString(this.clothesSkill_3);
        dest.writeString(this.clothesSkill_4);
        dest.writeString(this.clothesSkill_5);
        dest.writeString(this.waist_hole);
        dest.writeString(this.waistSkill_1);
        dest.writeString(this.waistSkill_2);
        dest.writeString(this.waistSkill_3);
        dest.writeString(this.waistSkill_4);
        dest.writeString(this.waistSkill_5);
        dest.writeString(this.foot_hole);
        dest.writeString(this.footSkill_1);
        dest.writeString(this.footSkill_2);
        dest.writeString(this.footSkill_3);
        dest.writeString(this.footSkill_4);
        dest.writeString(this.footSkill_5);
        dest.writeString(this.materialName_1);
        dest.writeString(this.materialName_2);
        dest.writeString(this.materialName_3);
        dest.writeString(this.materialName_4);
        dest.writeString(this.materialName_5);
        dest.writeString(this.materialName_6);
        dest.writeString(this.headMaterial_1);
        dest.writeString(this.headMaterial_2);
        dest.writeString(this.headMaterial_3);
        dest.writeString(this.headMaterial_4);
        dest.writeString(this.headMaterial_5);
        dest.writeString(this.headMaterial_6);
        dest.writeString(this.handMaterial_1);
        dest.writeString(this.handMaterial_2);
        dest.writeString(this.handMaterial_3);
        dest.writeString(this.handMaterial_4);
        dest.writeString(this.handMaterial_5);
        dest.writeString(this.handMaterial_6);
        dest.writeString(this.clothesMaterial_1);
        dest.writeString(this.clothesMaterial_2);
        dest.writeString(this.clothesMaterial_3);
        dest.writeString(this.clothesMaterial_4);
        dest.writeString(this.clothesMaterial_5);
        dest.writeString(this.clothesMaterial_6);
        dest.writeString(this.waistMaterial_1);
        dest.writeString(this.waistMaterial_2);
        dest.writeString(this.waistMaterial_3);
        dest.writeString(this.waistMaterial_4);
        dest.writeString(this.waistMaterial_5);
        dest.writeString(this.waistMaterial_6);
        dest.writeString(this.footMaterial_1);
        dest.writeString(this.footMaterial_2);
        dest.writeString(this.footMaterial_3);
        dest.writeString(this.footMaterial_4);
        dest.writeString(this.footMaterial_5);
        dest.writeString(this.footMaterial_6);
    }

    protected SuitSkillAttribute(Parcel in) {
        this.name = in.readString();
        this.armorLevel = in.readString();
        this.PinYin = in.readString();
        this.FirstPinYin = in.readString();
        this.skillName_1 = in.readString();
        this.skillName_2 = in.readString();
        this.skillName_3 = in.readString();
        this.skillName_4 = in.readString();
        this.skillName_5 = in.readString();
        this.head_hole = in.readString();
        this.headSkill_1 = in.readString();
        this.headSkill_2 = in.readString();
        this.headSkill_3 = in.readString();
        this.headSkill_4 = in.readString();
        this.headSkill_5 = in.readString();
        this.hand_hole = in.readString();
        this.handSkill_1 = in.readString();
        this.handSkill_2 = in.readString();
        this.handSkill_3 = in.readString();
        this.handSkill_4 = in.readString();
        this.handSkill_5 = in.readString();
        this.clothes_hole = in.readString();
        this.clothesSkill_1 = in.readString();
        this.clothesSkill_2 = in.readString();
        this.clothesSkill_3 = in.readString();
        this.clothesSkill_4 = in.readString();
        this.clothesSkill_5 = in.readString();
        this.waist_hole = in.readString();
        this.waistSkill_1 = in.readString();
        this.waistSkill_2 = in.readString();
        this.waistSkill_3 = in.readString();
        this.waistSkill_4 = in.readString();
        this.waistSkill_5 = in.readString();
        this.foot_hole = in.readString();
        this.footSkill_1 = in.readString();
        this.footSkill_2 = in.readString();
        this.footSkill_3 = in.readString();
        this.footSkill_4 = in.readString();
        this.footSkill_5 = in.readString();
        this.materialName_1 = in.readString();
        this.materialName_2 = in.readString();
        this.materialName_3 = in.readString();
        this.materialName_4 = in.readString();
        this.materialName_5 = in.readString();
        this.materialName_6 = in.readString();
        this.headMaterial_1 = in.readString();
        this.headMaterial_2 = in.readString();
        this.headMaterial_3 = in.readString();
        this.headMaterial_4 = in.readString();
        this.headMaterial_5 = in.readString();
        this.headMaterial_6 = in.readString();
        this.handMaterial_1 = in.readString();
        this.handMaterial_2 = in.readString();
        this.handMaterial_3 = in.readString();
        this.handMaterial_4 = in.readString();
        this.handMaterial_5 = in.readString();
        this.handMaterial_6 = in.readString();
        this.clothesMaterial_1 = in.readString();
        this.clothesMaterial_2 = in.readString();
        this.clothesMaterial_3 = in.readString();
        this.clothesMaterial_4 = in.readString();
        this.clothesMaterial_5 = in.readString();
        this.clothesMaterial_6 = in.readString();
        this.waistMaterial_1 = in.readString();
        this.waistMaterial_2 = in.readString();
        this.waistMaterial_3 = in.readString();
        this.waistMaterial_4 = in.readString();
        this.waistMaterial_5 = in.readString();
        this.waistMaterial_6 = in.readString();
        this.footMaterial_1 = in.readString();
        this.footMaterial_2 = in.readString();
        this.footMaterial_3 = in.readString();
        this.footMaterial_4 = in.readString();
        this.footMaterial_5 = in.readString();
        this.footMaterial_6 = in.readString();
    }

    public static final Parcelable.Creator<SuitSkillAttribute> CREATOR = new Parcelable.Creator<SuitSkillAttribute>() {
        @Override
        public SuitSkillAttribute createFromParcel(Parcel source) {
            return new SuitSkillAttribute(source);
        }

        @Override
        public SuitSkillAttribute[] newArray(int size) {
            return new SuitSkillAttribute[size];
        }
    };
}
