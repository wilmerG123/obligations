package com.garnicsoft.obligations.models.dtos;

import com.garnicsoft.obligations.models.entitys.Category;
import com.garnicsoft.obligations.models.entitys.Player;
import com.garnicsoft.obligations.models.enums.Status;

import java.util.Date;
import java.util.List;

public class ObligationDTO {

    private String name;
    private String description;
    private String value;
    private Date dateCreation;
    private String maxDate;
    private Status status;
    private List<Player> players;
    private List<Category> categories;

    public ObligationDTO() {
    }

    public ObligationDTO(String name, String description, String value, Date dateCreation, String maxDate, Status status, List<Player> players, List<Category> categories) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.dateCreation = dateCreation;
        this.maxDate = maxDate;
        this.status = status;
        this.players = players;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
