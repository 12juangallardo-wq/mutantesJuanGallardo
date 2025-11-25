package org.example.mutantes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.mutantes.dto.DnaRequest;
import org.example.mutantes.service.MutantService;
import org.example.mutantes.service.StatsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("removal")
@WebMvcTest(MutantController.class)
class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutantService mutantService;

    @MockBean
    private StatsService statsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /mutant - mutante → 200 OK")
    void testMutantReturns200() throws Exception {
        Mockito.when(mutantService.isMutantAndSave(any())).thenReturn(true);

        DnaRequest req = new DnaRequest();
        req.setDna(new String[]{"AAAA", "CCCC", "TTTT", "GGGG"});

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /mutant - humano → 403 Forbidden")
    void testHumanReturns403() throws Exception {
        Mockito.when(mutantService.isMutantAndSave(any())).thenReturn(false);

        DnaRequest req = new DnaRequest();
        req.setDna(new String[]{"ATGC", "CAGT", "TTAT", "AGAC"});

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("GET /stats - retorna JSON válido")
    void testStats() throws Exception {
        Mockito.when(statsService.getStats())
                .thenReturn(new org.example.mutantes.dto.StatsResponse(40, 100, 0.4));

        mockMvc.perform(get("/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutant_dna").value(40))
                .andExpect(jsonPath("$.count_human_dna").value(100))
                .andExpect(jsonPath("$.ratio").value(0.4));
    }
}