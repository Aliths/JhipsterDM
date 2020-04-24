package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PaiementMapperTest {

    private PaiementMapper paiementMapper;

    @BeforeEach
    public void setUp() {
        paiementMapper = new PaiementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(paiementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(paiementMapper.fromId(null)).isNull();
    }
}
