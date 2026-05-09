package co.simplon.wishmegift.dto;

import co.simplon.wishmegift.entity.WishList;
import co.simplon.wishmegift.enums.Level;

public class GiftDTO {
    private String giftName;
    private String giftDescription;
    private String giftLink;
    private Level level;
    private Integer price;
    private Boolean isReserved;
    private WishList wishList;

    public GiftDTO() {
    }

    public GiftDTO(String giftDescription, String giftLink, String giftName, Boolean isReserved, Level level, Integer price, WishList wishList) {
        this.giftDescription = giftDescription;
        this.giftLink = giftLink;
        this.giftName = giftName;
        this.isReserved = isReserved;
        this.level = level;
        this.price = price;
        this.wishList = wishList;
    }

    public String getGiftDescription() {
        return giftDescription;
    }

    public void setGiftDescription(String giftDescription) {
        this.giftDescription = giftDescription;
    }

    public String getGiftLink() {
        return giftLink;
    }

    public void setGiftLink(String giftLink) {
        this.giftLink = giftLink;
    }

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }
}
