package co.simplon.wishmegift.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID guestId;

    @ManyToMany
    private Set<WishList> wishLists = new HashSet<>();

    @OneToMany
    private Set<Gift> gift =  new HashSet<>();

    public Guest() {
    }

    public Guest(UUID guestId, Set<WishList> wishLists, Set<Gift> gift) {
        this.guestId = guestId;
        this.wishLists = wishLists;
        this.gift = gift;
    }

    public UUID getGuestId() {
        return guestId;
    }

    public void setGuestId(UUID guestId) {
        this.guestId = guestId;
    }

    public Set<WishList> getLists() {
        return wishLists;
    }

    public void setLists(Set<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    public Set<Gift> getGift() {
        return gift;
    }

    public void setGift(Set<Gift> gift) {
        this.gift = gift;
    }
}
