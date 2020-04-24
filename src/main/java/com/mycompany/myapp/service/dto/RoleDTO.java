package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.mycompany.myapp.domain.enumeration.Roles;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Role} entity.
 */
public class RoleDTO implements Serializable {

    private Long id;

    @NotNull
    private Roles type;


    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getType() {
        return type;
    }

    public void setType(Roles type) {
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

        RoleDTO roleDTO = (RoleDTO) o;
        if (roleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), roleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}
