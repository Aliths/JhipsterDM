package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Cooperative} entity.
 */
public class CooperativeDTO implements Serializable {

    private Long id;

    @Size(min = 2)
    private String nom;


    private Set<UserDTO> users = new HashSet<>();

    private Set<RestaurantDTO> restaurants = new HashSet<>();

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

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }

    public Set<RestaurantDTO> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<RestaurantDTO> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CooperativeDTO cooperativeDTO = (CooperativeDTO) o;
        if (cooperativeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cooperativeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CooperativeDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
