package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CooperativeMapperTest {

    private CooperativeMapper cooperativeMapper;

    @BeforeEach
    public void setUp() {
        cooperativeMapper = new CooperativeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cooperativeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cooperativeMapper.fromId(null)).isNull();
    }
}
