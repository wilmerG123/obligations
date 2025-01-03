package com.garnicsoft.obligations.models.entitys;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.garnicsoft.obligations.models.dtos.ObligationDTO;
import com.garnicsoft.obligations.models.enums.Status;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

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
    @OneToMany(mappedBy = "obligation") // Relación Uno a Muchos con Pago (una obligación puede tener muchos pagos)
    @JsonManagedReference
    private List<Pago> pagos; // Una obligación puede tener varios pagos, para el manejo de pagos parciales

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
        this.dateCreation = new Date();
        this.maxDate = obligation.getMaxDate();
        this.status = Status.MORA;
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

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
}
