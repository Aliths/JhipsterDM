package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Produit} entity.
 */
public class ProduitDTO implements Serializable {

    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    private BigDecimal prix;

    @NotNull
    @Min(value = 0)
    private Integer dispo;

    @NotNull
    private String nom;


    private Long restaurantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Integer getDispo() {
        return dispo;
    }

    public void setDispo(Integer dispo) {
        this.dispo = dispo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProduitDTO produitDTO = (ProduitDTO) o;
        if (produitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), produitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProduitDTO{" +
            "id=" + getId() +
            ", prix=" + getPrix() +
            ", dispo=" + getDispo() +
            ", nom='" + getNom() + "'" +
            ", restaurantId=" + getRestaurantId() +
            "}";
    }
}
