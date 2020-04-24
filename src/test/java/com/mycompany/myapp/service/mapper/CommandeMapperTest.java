package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CommandeMapperTest {

    private CommandeMapper commandeMapper;

    @BeforeEach
    public void setUp() {
        commandeMapper = new CommandeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(commandeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(commandeMapper.fromId(null)).isNull();
    }
}
