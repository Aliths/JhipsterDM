package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.Etat;

/**
 * A Panier.
 */
@Entity
@Table(name = "panier")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Panier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "etat", nullable = false)
    private Etat etat;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "panier_produit",
               joinColumns = @JoinColumn(name = "panier_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "produit_id", referencedColumnName = "id"))
    private Set<Produit> produits = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Etat getEtat() {
        return etat;
    }

    public Panier etat(Etat etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public Panier produits(Set<Produit> produits) {
        this.produits = produits;
        return this;
    }

    public Panier addProduit(Produit produit) {
        this.produits.add(produit);
        produit.getPaniers().add(this);
        return this;
    }

    public Panier removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.getPaniers().remove(this);
        return this;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Panier)) {
            return false;
        }
        return id != null && id.equals(((Panier) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Panier{" +
            "id=" + getId() +
            ", etat='" + getEtat() + "'" +
            "}";
    }
}
