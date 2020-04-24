package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ProduitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProduitDTO.class);
        ProduitDTO produitDTO1 = new ProduitDTO();
        produitDTO1.setId(1L);
        ProduitDTO produitDTO2 = new ProduitDTO();
        assertThat(produitDTO1).isNotEqualTo(produitDTO2);
        produitDTO2.setId(produitDTO1.getId());
        assertThat(produitDTO1).isEqualTo(produitDTO2);
        produitDTO2.setId(2L);
        assertThat(produitDTO1).isNotEqualTo(produitDTO2);
        produitDTO1.setId(null);
        assertThat(produitDTO1).isNotEqualTo(produitDTO2);
    }
}
