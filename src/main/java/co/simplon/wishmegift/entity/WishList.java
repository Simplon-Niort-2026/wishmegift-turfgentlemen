package co.simplon.wishmegift.entity;

import co.simplon.wishmegift.enums.Theme;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="list")
public class WishList {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String listName;

    @Enumerated(EnumType.STRING)
    private Theme theme;

    private String description;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date eventDate;

    @JsonIgnore
    @ManyToOne
    private User owner;

    @ManyToMany
    private Set<User> guests = new HashSet<>();

    @OneToMany
    private Set<Gift> gifts = new HashSet<>();

    public WishList() {
    }

    public WishList(Long id, String listName, Theme theme, String description, Date eventDate, User owner, Set<User> guests, Set<Gift> gifts) {
        this.id = id;
        this.listName = listName;
        this.theme = theme;
        this.description = description;
        this.eventDate = eventDate;
        this.owner = owner;
        this.guests = guests;
        this.gifts = gifts;
    }

}
