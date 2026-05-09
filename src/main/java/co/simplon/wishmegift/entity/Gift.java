package co.simplon.wishmegift.entity;

import co.simplon.wishmegift.enums.Level;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="gift")
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long giftId;

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
    private User user;

    public Gift() {
    }

    public Gift(Long giftId, String giftName, String giftDescription, String giftLink, Level level, Integer price, Boolean isReserved, WishList wishList, User user) {
        this.giftId = giftId;
        this.giftName = giftName;
        this.giftDescription = giftDescription;
        this.giftLink = giftLink;
        this.level = level;
        this.price = price;
        this.isReserved = isReserved;
        this.wishList = wishList;
        this.user = user;
    }


    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean isReserved) {
        this.isReserved = !this.isReserved;
    }

}
