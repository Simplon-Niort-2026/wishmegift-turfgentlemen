package co.simplon.wishmegift.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50, nullable = false)
    private String username;

    @Pattern(regexp ="^(?=.*[a-zA-Z0-9\\-])(?=.*[!@#$%?^&*]).{12,}$")
    @Column(nullable = false)
    private String password;

    @Pattern(regexp = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$")
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @OneToMany(mappedBy = "owner")
    private Set<WishList> wishLists;

    @OneToMany(mappedBy = "user")
    private Set<Gift> gifts = new HashSet<>();


    @ManyToMany(mappedBy = "guests")
    private Set<WishList> guestLists;

    public Set<WishList> getWishLists() {
        return wishLists;
    }

    public void setWishLists(Set<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    public Set<WishList> getGuestLists() {
        return guestLists;
    }

    public void setGuestLists(Set<WishList> guestLists) {
        this.guestLists = guestLists;
    }

    public Set<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(Set<Gift> gifts) {
        this.gifts = gifts;
    }

    public User() {
    }

    public User(UUID id, String username, String password, String email, Set<Gift> gifts, Set<WishList> guestLists, Set<WishList> wishLists) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gifts = gifts;
        this.guestLists = guestLists;
        this.wishLists = wishLists;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<WishList> getLists() {
        return wishLists;
    }

    public void setLists(Set<WishList> wishLists) {
        this.wishLists = wishLists;
    }
}
