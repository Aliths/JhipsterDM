package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.mycompany.myapp.domain.enumeration.MethodPaiement;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Paiement} entity.
 */
public class PaiementDTO implements Serializable {

    private Long id;

    @NotNull
    private MethodPaiement methode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MethodPaiement getMethode() {
        return methode;
    }

    public void setMethode(MethodPaiement methode) {
        this.methode = methode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PaiementDTO paiementDTO = (PaiementDTO) o;
        if (paiementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paiementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaiementDTO{" +
            "id=" + getId() +
            ", methode='" + getMethode() + "'" +
            "}";
    }
}
