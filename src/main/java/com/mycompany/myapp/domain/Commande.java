package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Commande.
 */
@Entity
@Table(name = "commande")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "echeance", nullable = false)
    private Instant echeance;

    @ManyToOne
    @JsonIgnoreProperties("commandes")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("commandes")
    private Restaurant restaurant;

    @ManyToOne
    @JsonIgnoreProperties("commandes")
    private Panier panier;

    @ManyToOne
    @JsonIgnoreProperties("commandes")
    private Paiement paiement;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getEcheance() {
        return echeance;
    }

    public Commande echeance(Instant echeance) {
        this.echeance = echeance;
        return this;
    }

    public void setEcheance(Instant echeance) {
        this.echeance = echeance;
    }

    public User getUser() {
        return user;
    }

    public Commande user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Commande restaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Panier getPanier() {
        return panier;
    }

    public Commande panier(Panier panier) {
        this.panier = panier;
        return this;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public Commande paiement(Paiement paiement) {
        this.paiement = paiement;
        return this;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Commande)) {
            return false;
        }
        return id != null && id.equals(((Commande) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Commande{" +
            "id=" + getId() +
            ", echeance='" + getEcheance() + "'" +
            "}";
    }
}
