package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProduitMapperTest {

    private ProduitMapper produitMapper;

    @BeforeEach
    public void setUp() {
        produitMapper = new ProduitMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(produitMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(produitMapper.fromId(null)).isNull();
    }
}
