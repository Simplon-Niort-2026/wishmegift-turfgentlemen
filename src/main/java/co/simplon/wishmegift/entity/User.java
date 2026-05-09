package co.simplon.wishmegift.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public User() {
    }

    public User(Long id, String username, String password, String email, Set<Gift> gifts, Set<WishList> guestLists, Set<WishList> wishLists) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gifts = gifts;
        this.guestLists = guestLists;
        this.wishLists = wishLists;
    }

    public Set<WishList> getLists() {
        return wishLists;
    }

    public void setLists(Set<WishList> wishLists) {
        this.wishLists = wishLists;
    }
}
