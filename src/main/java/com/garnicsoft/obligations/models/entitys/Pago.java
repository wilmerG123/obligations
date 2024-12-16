package com.garnicsoft.obligations.models.entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date cancelledDate;
    private String value;
    private String description;
    private String nameObligation;
    private String namePagador;
    private String lastNamePagador;
    private String emailDeudor;
    private String phoneDeudor;

    @ManyToOne
    @JsonBackReference
    private Obligation obligation; // Muchos pagos pueden tener una sola obligación, manejo de pagos parciales a futuro

    public Pago() {
    }

    public Pago(Long id, Date cancelledDate, String value, String description, String nameObligation, String namePagador, String lastNamePagador, String emailDeudor, String phoneDeudor, Obligation obligation) {
        this.id = id;
        this.cancelledDate = cancelledDate;
        this.value = value;
        this.description = description;
        this.nameObligation = nameObligation;
        this.namePagador = namePagador;
        this.lastNamePagador = lastNamePagador;
        this.emailDeudor = emailDeudor;
        this.phoneDeudor = phoneDeudor;
        this.obligation = obligation;
    }

    public Pago(Obligation obligationToCancelled) {
        this.obligation = obligationToCancelled;
        this.cancelledDate = new Date();
        this.value = obligationToCancelled.getValue() !=null ? obligationToCancelled.getValue() : null;
        this.description = obligationToCancelled.getDescription() !=null ? obligationToCancelled.getDescription() : null;
        this.nameObligation = obligationToCancelled.getName() !=null ? obligationToCancelled.getName() : null;
        if(obligationToCancelled.getPlayer() != null) {
            this.namePagador = obligationToCancelled.getPlayer().getName() !=null ? obligationToCancelled.getPlayer().getName() : null;
            this.lastNamePagador = obligationToCancelled.getPlayer().getLastName() !=null ? obligationToCancelled.getPlayer().getLastName() : null;
            this.emailDeudor = obligationToCancelled.getPlayer().getMail() !=null ? obligationToCancelled.getPlayer().getMail() : null;
            this.phoneDeudor = obligationToCancelled.getPlayer().getPhone() !=null ? obligationToCancelled.getPlayer().getPhone() : null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCancelledDate() {
        return cancelledDate;
    }

    public void setCancelledDate(Date cancelledDate) {
        this.cancelledDate = cancelledDate;
    }

    public Obligation getObligation() {
        return obligation;
    }

    public void setObligation(Obligation obligation) {
        this.obligation = obligation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameObligation() {
        return nameObligation;
    }

    public void setNameObligation(String nameObligation) {
        this.nameObligation = nameObligation;
    }

    public String getNamePagador() {
        return namePagador;
    }

    public void setNamePagador(String namePagador) {
        this.namePagador = namePagador;
    }

    public String getLastNamePagador() {
        return lastNamePagador;
    }

    public void setLastNamePagador(String lastNamePagador) {
        this.lastNamePagador = lastNamePagador;
    }

    public String getEmailDeudor() {
        return emailDeudor;
    }

    public void setEmailDeudor(String emailDeudor) {
        this.emailDeudor = emailDeudor;
    }

    public String getPhoneDeudor() {
        return phoneDeudor;
    }

    public void setPhoneDeudor(String phoneDeudor) {
        this.phoneDeudor = phoneDeudor;
    }
}
