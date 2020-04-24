package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PanierDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PanierDTO.class);
        PanierDTO panierDTO1 = new PanierDTO();
        panierDTO1.setId(1L);
        PanierDTO panierDTO2 = new PanierDTO();
        assertThat(panierDTO1).isNotEqualTo(panierDTO2);
        panierDTO2.setId(panierDTO1.getId());
        assertThat(panierDTO1).isEqualTo(panierDTO2);
        panierDTO2.setId(2L);
        assertThat(panierDTO1).isNotEqualTo(panierDTO2);
        panierDTO1.setId(null);
        assertThat(panierDTO1).isNotEqualTo(panierDTO2);
    }
}
