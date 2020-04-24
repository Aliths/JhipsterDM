package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Panier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Panier entity.
 */
@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {

    @Query(value = "select distinct panier from Panier panier left join fetch panier.produits",
        countQuery = "select count(distinct panier) from Panier panier")
    Page<Panier> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct panier from Panier panier left join fetch panier.produits")
    List<Panier> findAllWithEagerRelationships();

    @Query("select panier from Panier panier left join fetch panier.produits where panier.id =:id")
    Optional<Panier> findOneWithEagerRelationships(@Param("id") Long id);

}
