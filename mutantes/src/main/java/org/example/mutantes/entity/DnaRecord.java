package org.example.mutantes.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dna_records", uniqueConstraints = {
        @UniqueConstraint(columnNames = "dnaHash")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DnaRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String dnaHash;

    @Column(nullable = false)
    private boolean mutant;
}