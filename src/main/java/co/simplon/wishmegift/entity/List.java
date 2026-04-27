package co.simplon.wishmegift.entity;

import co.simplon.wishmegift.enums.Theme;
import jakarta.persistence.*;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="list")
public class List {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    private String listName;

    @Enumerated(EnumType.STRING)
    private Theme theme;

    private String description;

    private Date eventDate;

    @ManyToOne
    private User user;

    @ManyToMany
    private Set<Guest> guests = new HashSet<>();

    @OneToMany
    private Set<Gift> gifts = new HashSet<>();

    public List() {
    }

    public List(UUID id, String listName, Theme theme, String description, Date eventDate, User user, Set<Guest> guests, Set<Gift> gifts) {
        this.id = id;
        this.listName = listName;
        this.theme = theme;
        this.description = description;
        this.eventDate = eventDate;
        this.user = user;
        this.guests = guests;
        this.gifts = gifts;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Guest> getGuests() {
        return guests;
    }

    public void setGuests(Set<Guest> guests) {
        this.guests = guests;
    }

    public Set<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(Set<Gift> gifts) {
        this.gifts = gifts;
    }
}
