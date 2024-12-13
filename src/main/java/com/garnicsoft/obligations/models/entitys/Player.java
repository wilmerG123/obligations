package com.garnicsoft.obligations.models.entitys;

import jakarta.persistence.*;


@Entity
@Table(name = "players")
public class Player extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "category_id") // Columna que actúa como clave foráne
    private Category category;

    public Player(Long id) {
        super();
        this.id =id;
    }

    public Player() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}