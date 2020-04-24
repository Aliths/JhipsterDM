package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Produit.
 */
@Entity
@Table(name = "produit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "prix", precision = 21, scale = 2, nullable = false)
    private BigDecimal prix;

    @NotNull
    @Min(value = 0)
    @Column(name = "dispo", nullable = false)
    private Integer dispo;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @ManyToOne
    @JsonIgnoreProperties("produits")
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "produits")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Panier> paniers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public Produit prix(BigDecimal prix) {
        this.prix = prix;
        return this;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Integer getDispo() {
        return dispo;
    }

    public Produit dispo(Integer dispo) {
        this.dispo = dispo;
        return this;
    }

    public void setDispo(Integer dispo) {
        this.dispo = dispo;
    }

    public String getNom() {
        return nom;
    }

    public Produit nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Produit restaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<Panier> getPaniers() {
        return paniers;
    }

    public Produit paniers(Set<Panier> paniers) {
        this.paniers = paniers;
        return this;
    }

    public Produit addPanier(Panier panier) {
        this.paniers.add(panier);
        panier.getProduits().add(this);
        return this;
    }

    public Produit removePanier(Panier panier) {
        this.paniers.remove(panier);
        panier.getProduits().remove(this);
        return this;
    }

    public void setPaniers(Set<Panier> paniers) {
        this.paniers = paniers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produit)) {
            return false;
        }
        return id != null && id.equals(((Produit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", prix=" + getPrix() +
            ", dispo=" + getDispo() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
