package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PanierMapperTest {

    private PanierMapper panierMapper;

    @BeforeEach
    public void setUp() {
        panierMapper = new PanierMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(panierMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(panierMapper.fromId(null)).isNull();
    }
}
