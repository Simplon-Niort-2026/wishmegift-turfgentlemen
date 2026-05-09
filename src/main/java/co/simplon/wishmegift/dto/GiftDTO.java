package co.simplon.wishmegift.dto;

import co.simplon.wishmegift.entity.WishList;
import co.simplon.wishmegift.enums.Level;

public class GiftDTO {

    private Long id;
    private String giftName;
    private String giftDescription;
    private String giftLink;
    private Level level;
    private Integer price;
    private Boolean isReserved;
    private WishList wishList;

    public GiftDTO() {
    }

    public GiftDTO(Long id, String giftName, String giftDescription, String giftLink, Level level, Integer price, Boolean isReserved, WishList wishList) {
        this.id = id;
        this.giftName = giftName;
        this.giftDescription = giftDescription;
        this.giftLink = giftLink;
        this.level = level;
        this.price = price;
        this.isReserved = isReserved;
        this.wishList = wishList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
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

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }
}
