package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Cooperative.
 */
@Entity
@Table(name = "cooperative")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cooperative implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(min = 2)
    @Column(name = "nom")
    private String nom;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "cooperative_user",
               joinColumns = @JoinColumn(name = "cooperative_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "cooperative_restaurant",
               joinColumns = @JoinColumn(name = "cooperative_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id"))
    private Set<Restaurant> restaurants = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Cooperative nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Cooperative users(Set<User> users) {
        this.users = users;
        return this;
    }

    public Cooperative addUser(User user) {
        this.users.add(user);
        return this;
    }

    public Cooperative removeUser(User user) {
        this.users.remove(user);
        return this;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Cooperative restaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
        return this;
    }

    public Cooperative addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
        restaurant.getCooperatives().add(this);
        return this;
    }

    public Cooperative removeRestaurant(Restaurant restaurant) {
        this.restaurants.remove(restaurant);
        restaurant.getCooperatives().remove(this);
        return this;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cooperative)) {
            return false;
        }
        return id != null && id.equals(((Cooperative) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Cooperative{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
