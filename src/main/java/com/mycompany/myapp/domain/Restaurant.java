package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Restaurant.
 */
@Entity
@Table(name = "restaurant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne
    @JsonIgnoreProperties("restaurants")
    private User user;

    @ManyToMany(mappedBy = "restaurants")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Cooperative> cooperatives = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Restaurant nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public Restaurant type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public Restaurant user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Cooperative> getCooperatives() {
        return cooperatives;
    }

    public Restaurant cooperatives(Set<Cooperative> cooperatives) {
        this.cooperatives = cooperatives;
        return this;
    }

    public Restaurant addCooperative(Cooperative cooperative) {
        this.cooperatives.add(cooperative);
        cooperative.getRestaurants().add(this);
        return this;
    }

    public Restaurant removeCooperative(Cooperative cooperative) {
        this.cooperatives.remove(cooperative);
        cooperative.getRestaurants().remove(this);
        return this;
    }

    public void setCooperatives(Set<Cooperative> cooperatives) {
        this.cooperatives = cooperatives;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Restaurant)) {
            return false;
        }
        return id != null && id.equals(((Restaurant) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
