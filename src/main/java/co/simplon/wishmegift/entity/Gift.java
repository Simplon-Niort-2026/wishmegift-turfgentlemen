package co.simplon.wishmegift.entity;

import co.simplon.wishmegift.enums.Level;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="gift")
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID giftId;

    @Column(length = 50, nullable = false)
    private String giftName;

    @Column(nullable = false)
    private String giftDescription;

    private String giftLink;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;

    private Integer price;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isReserved = false;

    @ManyToOne
    private WishList wishList;

    @ManyToOne
    private Guest guest;

    public Gift() {
    }

    public Gift(UUID giftId, String giftName, String giftDescription, String giftLink, Level level, Integer price, Boolean isReserved, WishList wishList, Guest guest) {
        this.giftId = giftId;
        this.giftName = giftName;
        this.giftDescription = giftDescription;
        this.giftLink = giftLink;
        this.level = level;
        this.price = price;
        this.isReserved = isReserved;
        this.wishList = wishList;
        this.guest = guest;
    }

    public UUID getGiftId() {
        return giftId;
    }

    public void setGiftId(UUID giftId) {
        this.giftId = giftId;
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

    public WishList getList() {
        return wishList;
    }

    public void setList(WishList wishList) {
        this.wishList = wishList;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
