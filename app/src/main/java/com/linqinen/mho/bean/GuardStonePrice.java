package com.linqinen.mho.bean;

/**
 * Created by lin on 2016/11/25.
 */

public class GuardStonePrice {

    String name;
    /**精铸*/
    String precisionCasting;
    /**竞拍价*/
    String auctionPrice;
    /**一口价*/
    String fixedPrice;

    private int isRefresh;

    private String PinYin;
    private String FirstPinYin;

    public GuardStonePrice() {
    }

    public GuardStonePrice(String name, String precisionCasting, String auctionPrice, String fixedPrice, int isRefresh) {
        this.name = name;
        this.precisionCasting = precisionCasting;
        this.auctionPrice = auctionPrice;
        this.fixedPrice = fixedPrice;
        this.isRefresh = isRefresh;
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

    public int getIsRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(int isRefresh) {
        this.isRefresh = isRefresh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrecisionCasting() {
        return precisionCasting;
    }

    public void setPrecisionCasting(String precisionCasting) {
        this.precisionCasting = precisionCasting;
    }

    public String getAuctionPrice() {
        return auctionPrice;
    }

    public void setAuctionPrice(String auctionPrice) {
        this.auctionPrice = auctionPrice;
    }

    public String getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(String fixedPrice) {
        this.fixedPrice = fixedPrice;
    }
}
