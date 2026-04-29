package co.simplon.wishmegift.entity;

import jakarta.persistence.*;


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


    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false, length = 100)
    private String email;


    @OneToMany
    private Set<WishList> wishLists;

    @OneToMany
    private Set<Gift> gifts =  new HashSet<>();

    public User() {
    }

    public User(String username, String password, UUID id, String email, Set<WishList> wishLists, Set<Gift> gifts) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.email = email;
        this.wishLists = wishLists;
        this.gifts = gifts;
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
