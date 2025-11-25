package org.example.mutantes.controller;

import org.example.mutantes.dto.DnaRequest;
import org.example.mutantes.service.MutantService;
import org.example.mutantes.service.StatsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MutantController.class)
class MutantControllerErrorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutantService mutantService;

    @MockBean
    private StatsService statsService;

    @Test
    void testInvalidDnaReturns400() throws Exception {
        Mockito.doThrow(new IllegalArgumentException("ADN inválido"))
                .when(mutantService).isMutantAndSave(any());

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"dna\":[\"ATXG\"]}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testServerErrorReturns500() throws Exception {
        Mockito.doThrow(new RuntimeException("fallo interno"))
                .when(mutantService).isMutantAndSave(any());

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"dna\":[\"AAAA\",\"CCCC\",\"TTTT\",\"GGGG\"]}"))
                .andExpect(status().isInternalServerError());
    }
}

