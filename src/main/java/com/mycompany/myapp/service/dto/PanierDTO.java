package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.mycompany.myapp.domain.enumeration.Etat;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Panier} entity.
 */
public class PanierDTO implements Serializable {

    private Long id;

    @NotNull
    private Etat etat;


    private Set<ProduitDTO> produits = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Set<ProduitDTO> getProduits() {
        return produits;
    }

    public void setProduits(Set<ProduitDTO> produits) {
        this.produits = produits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PanierDTO panierDTO = (PanierDTO) o;
        if (panierDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), panierDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PanierDTO{" +
            "id=" + getId() +
            ", etat='" + getEtat() + "'" +
            "}";
    }
}
