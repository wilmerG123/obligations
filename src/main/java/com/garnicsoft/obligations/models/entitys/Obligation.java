package com.garnicsoft.obligations.models.entitys;


import com.garnicsoft.obligations.models.dtos.ObligationDTO;
import com.garnicsoft.obligations.models.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "obligation")
public class Obligation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String value;
    private Date dateCreation;
    private String maxDate;
    private Status status;
    @ManyToOne
    private Player player;

    public Obligation() {
    }


    public Obligation(Long id, String name, String description, String value, Date dateCreation, String maxDate, Status status, Player player) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
        this.dateCreation = dateCreation;
        this.maxDate = maxDate;
        this.status = status;
        this.player = player;
    }

    public Obligation(ObligationDTO obligation, Player player) {
        this.name = obligation.getName();
        this.description = obligation.getDescription();
        this.value = obligation.getValue();
        this.dateCreation = obligation.getDateCreation();
        this.maxDate = obligation.getMaxDate();
        this.status = obligation.getStatus();
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
