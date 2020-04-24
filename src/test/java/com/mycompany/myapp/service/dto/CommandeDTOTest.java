package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class CommandeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommandeDTO.class);
        CommandeDTO commandeDTO1 = new CommandeDTO();
        commandeDTO1.setId(1L);
        CommandeDTO commandeDTO2 = new CommandeDTO();
        assertThat(commandeDTO1).isNotEqualTo(commandeDTO2);
        commandeDTO2.setId(commandeDTO1.getId());
        assertThat(commandeDTO1).isEqualTo(commandeDTO2);
        commandeDTO2.setId(2L);
        assertThat(commandeDTO1).isNotEqualTo(commandeDTO2);
        commandeDTO1.setId(null);
        assertThat(commandeDTO1).isNotEqualTo(commandeDTO2);
    }
}
