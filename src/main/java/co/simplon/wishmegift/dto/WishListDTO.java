package co.simplon.wishmegift.dto;

import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.enums.Theme;

import java.util.Date;

public class WishListDTO {

    private Long id;
    private String listName;
    private String description;
    private Theme theme;
    private Date eventDate;
    private User owner;

    public WishListDTO() {}

    public WishListDTO(Long id, String listName, User owner, Date eventDate, Theme theme, String description) {
        this.id = id;
        this.listName = listName;
        this.owner = owner;
        this.eventDate = eventDate;
        this.theme = theme;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
