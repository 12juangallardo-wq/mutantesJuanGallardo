package org.example.mutantes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request que representa la secuencia de ADN a analizar")
public class DnaRequest {

    @Schema(
            description = "Arreglo NxN de ADN con caracteres válidos: A, T, C, G",
            example = "[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]"
    )
    private String[] dna;
}
