package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Restaurant} entity.
 */
public class RestaurantDTO implements Serializable {

    private Long id;

    @NotNull
    private String nom;

    @NotNull
    private String type;


    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RestaurantDTO restaurantDTO = (RestaurantDTO) o;
        if (restaurantDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), restaurantDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RestaurantDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", type='" + getType() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}
