package com.mycompany.myapp.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Commande} entity.
 */
public class CommandeDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant echeance;


    private Long userId;

    private Long restaurantId;

    private Long panierId;

    private Long paiementId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getEcheance() {
        return echeance;
    }

    public void setEcheance(Instant echeance) {
        this.echeance = echeance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getPanierId() {
        return panierId;
    }

    public void setPanierId(Long panierId) {
        this.panierId = panierId;
    }

    public Long getPaiementId() {
        return paiementId;
    }

    public void setPaiementId(Long paiementId) {
        this.paiementId = paiementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommandeDTO commandeDTO = (CommandeDTO) o;
        if (commandeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commandeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CommandeDTO{" +
            "id=" + getId() +
            ", echeance='" + getEcheance() + "'" +
            ", userId=" + getUserId() +
            ", restaurantId=" + getRestaurantId() +
            ", panierId=" + getPanierId() +
            ", paiementId=" + getPaiementId() +
            "}";
    }
}
